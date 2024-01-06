package com.security.app.service.impl;

import com.security.app.dto.ApplicationDTO;
import com.security.app.dto.CarDTO;
import com.security.app.dto.answer.CarAnswerDTO;
import com.security.app.enumeration.Category;
import com.security.app.enumeration.MachineDrive;
import com.security.app.enumeration.Status;
import com.security.app.exception.model.UsernameExistException;
import com.security.app.model.Application;
import com.security.app.model.Car;
import com.security.app.model.User;
import com.security.app.repository.ApplicationRepository;
import com.security.app.repository.CarRepository;
import com.security.app.repository.UserRepository;
import com.security.app.service.CarService;
import com.security.app.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.security.app.constant.FileConstant.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final UserService userService;
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    @Override
    public List<CarAnswerDTO> getCarsByCategory(String category) {
        List<Car> cars = carRepository.findByCategory(category);
        return cars.stream().filter(car -> car.getStatus().equals(Status.FREE.name())).map(this::createCarAnswerDTO).toList();
    }

    private CarAnswerDTO createCarAnswerDTO(Car car) {
        CarAnswerDTO carAnswerDTO = new CarAnswerDTO();
        carAnswerDTO.setId(car.getId());
        carAnswerDTO.setCategory(car.getCategory());
        carAnswerDTO.setBrand(car.getBrand());
        carAnswerDTO.setEngineCapacity(car.getEngineCapacity());
        carAnswerDTO.setVolumeCapacity(car.getVolumeCapacity());
        carAnswerDTO.setMachineDrive(car.getMachineDrive());
        carAnswerDTO.setColor(car.getColor());
        carAnswerDTO.setConsumption(car.getConsumption());
        carAnswerDTO.setNumberOfSeats(car.getNumberOfSeats());
        carAnswerDTO.setPrice(car.getPrice());
        carAnswerDTO.setImage(car.getImage());
        return carAnswerDTO;
    }

    private Car saveImage(Car car, MultipartFile image) throws IOException {
        if (image != null){
            Path userFolder = Paths.get(USER_FOLDER + car.getCategory()).toAbsolutePath().normalize();
            if (!Files.exists(userFolder)){
                Files.createDirectories(userFolder);
            }
            Files.deleteIfExists(Paths.get(userFolder + FORWARD_SLASH + car.getNumber() + DOT + JPG_EXTENSION));
            Files.copy(image.getInputStream(), userFolder.resolve(car.getNumber() + DOT + JPG_EXTENSION), REPLACE_EXISTING);
            car.setImage(setImageUrl(car.getCategory(), car.getNumber()));
            return car;
        }
        return car;
    }

    private String setImageUrl(String category, String number) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().
                path(CAR_PATH + FORWARD_SLASH + category + FORWARD_SLASH + number + DOT + JPG_EXTENSION).toUriString();
    }

    @Override
    public void create(Car car) {
        carRepository.save(car);
    }

    @Override
    public void createCar(CarDTO carDTO, MultipartFile image) throws IOException {
        Car car = new Car();
        car.setNumber(generateNumber());
        car.setCategory(getCategory(carDTO.getCategory()));
        car.setBrand(carDTO.getBrand());
        car.setEngineCapacity(carDTO.getEngineCapacity());
        car.setVolumeCapacity(carDTO.getVolumeCapacity());
        car.setMachineDrive(getMachineDrive(carDTO.getMachineDrive()));
        car.setColor(carDTO.getColor());
        car.setConsumption(carDTO.getConsumption());
        car.setNumberOfSeats(carDTO.getNumberOfSeats());
        car.setPrice(carDTO.getPrice());
        car.setStatus(Status.FREE.name());
        car = saveImage(car, image);
        carRepository.save(car);
    }

    @Override
    public CarAnswerDTO getCar(Long id) {
        Car car = carRepository.findById(id).get();
        return createCarAnswerDTO(car);
    }

    @Override
    @Transactional
    public List<CarAnswerDTO> application(ApplicationDTO applicationDTO, String usernameWithToken) throws UsernameExistException {
        Car car = carRepository.findById(applicationDTO.getCarId()).get();
        car.setStatus(Status.BUSY.name());
        User user = userService.findByUsername(usernameWithToken);
        Application application = new Application();
        application.setName(applicationDTO.getName());
        application.setSurname(applicationDTO.getSurname());
        application.setPatronymic(applicationDTO.getPatronymic());
        application.setPassport(applicationDTO.getPassport());
        application.setMobile(applicationDTO.getMobile());
        application.setAmountOfDays(applicationDTO.getAmountOfDays());
        application = applicationRepository.save(application);
        user.addApplication(application);
        userRepository.save(user);
        car.addApplication(application);
        carRepository.save(car);
        return getCarsByCategory(applicationDTO.getCategory());
    }

    @Override
    public List<CarAnswerDTO> getApp(String usernameWithToken) throws UsernameExistException {
        User user = userService.findByUsername(usernameWithToken);
        return user.getApplications().stream().map(app -> app.getCar()).map(this::createCarAnswerDTO).toList();
    }

    private String getMachineDrive(String machineDrive) {
        String result = "";
        switch (machineDrive) {
            case "Заднеприводный":  {
                result = MachineDrive.REAR_WHEEL_DRIVE.getWheel();
                break;
            }
            case "Переднеприводный":  {
                result = MachineDrive.FRONT_WHEEL_DRIVE.getWheel();
                break;
            }
            case "Полноприводный":  {
                result = MachineDrive.ALL_WHEEL_DRIVE.getWheel();
                break;
            }
        }
        return result;
    }

    private String getCategory(String category) {
        String result = "";
        switch (category) {
            case "Комфорт класс":  {
                result = Category.COMFORT_CLASS.getCategory();
                break;
            }
            case "Бизнес класс":  {
                result = Category.BUSINESS_CLASS.getCategory();
                break;
            }
            case "Премиум":  {
                result = Category.PREMIUM.getCategory();
                break;
            }
            case "Внедорожники":  {
                result = Category.SUV.getCategory();
                break;
            }
            case "Кабриолеты":  {
                result = Category.CONVERTIBLES.getCategory();
                break;
            }
            case "Спорт":  {
                result = Category.SPORT.getCategory();
                break;
            }
            case "Микроавтобусы":  {
                result = Category.MINIBUSES.getCategory();
                break;
            }
        }
        return result;
    }

    private String generateNumber() {
        return RandomStringUtils.randomAlphanumeric(10);
    }


}
