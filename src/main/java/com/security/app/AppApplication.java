package com.security.app;

import com.security.app.enumeration.Category;
import com.security.app.enumeration.MachineDrive;
import com.security.app.model.Car;
import com.security.app.service.CarService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(CarService carService) {
		return args -> {
//			createComfortClass(carService);
//			createBusinessClass(carService);
//			createPremium(carService);
//			createSUV(carService);
//			createConvertibles(carService);
//			createSport(carService);
//			createMinibuses(carService);
		};
	}

//	private void createMinibuses(CarService carService) {
//		Car car1 = new Car();
//		car1.setCategory(Category.COMFORT_CLASS.getName());
//		car1.setBrand("Ford Mondeo Black");
//		car1.setEngineCapacity(145);
//		car1.setVolumeCapacity(2);
//		car1.setMachineDrive(MachineDrive.FRONT_WHEEL_DRIVE.name());
//		car1.setColor("Черный");
//		car1.setConsumption(10.0);
//		car1.setNumberOfSeats(5);
//		car1.setPrice(55.0);
//		carService.create(car1);
//
//		Car car2 = new Car();
//		car2.setCategory(Category.COMFORT_CLASS.getName());
//		car2.setBrand("Toyota Corolla");
//		car2.setEngineCapacity(122);
//		car2.setVolumeCapacity(1.6);
//		car2.setMachineDrive(MachineDrive.FRONT_WHEEL_DRIVE.name());
//		car2.setColor("Чёрный-Металлик");
//		car2.setConsumption(6.0);
//		car2.setNumberOfSeats(5);
//		car2.setPrice(55.0);
//		carService.create(car2);
//
//		Car car3 = new Car();
//		car3.setCategory(Category.COMFORT_CLASS.getName());
//		car3.setBrand("Volkswagen Passat GTE");
//		car3.setEngineCapacity(240);
//		car3.setVolumeCapacity(1.4);
//		car3.setMachineDrive(MachineDrive.FRONT_WHEEL_DRIVE.name());
//		car3.setColor("Серый");
//		car3.setConsumption(6.0);
//		car3.setNumberOfSeats(5);
//		car3.setPrice(70.0);
//		carService.create(car3);
//	}
//
//	private void createSport(CarService carService) {
//		Car car1 = new Car();
//		car1.setCategory();
//		car1.setBrand();
//		car1.setEngineCapacity();
//		car1.setVolumeCapacity();
//		car1.setMachineDrive();
//		car1.setColor();
//		car1.setConsumption();
//		car1.setNumberOfSeats();
//		carService.create(car1);
//
//		Car car2 = new Car();
//		car2.setCategory();
//		car2.setBrand();
//		car2.setEngineCapacity();
//		car2.setVolumeCapacity();
//		car2.setMachineDrive();
//		car2.setColor();
//		car2.setConsumption();
//		car2.setNumberOfSeats();
//		carService.create(car2);
//
//		Car car3 = new Car();
//		car3.setCategory();
//		car3.setBrand();
//		car3.setEngineCapacity();
//		car3.setVolumeCapacity();
//		car3.setMachineDrive();
//		car3.setColor();
//		car3.setConsumption();
//		car3.setNumberOfSeats();
//		carService.create(car3);	}
//
//	private void createConvertibles(CarService carService) {
//		Car car1 = new Car();
//		car1.setCategory();
//		car1.setBrand();
//		car1.setEngineCapacity();
//		car1.setVolumeCapacity();
//		car1.setMachineDrive();
//		car1.setColor();
//		car1.setConsumption();
//		car1.setNumberOfSeats();
//		carService.create(car1);
//
//		Car car2 = new Car();
//		car2.setCategory();
//		car2.setBrand();
//		car2.setEngineCapacity();
//		car2.setVolumeCapacity();
//		car2.setMachineDrive();
//		car2.setColor();
//		car2.setConsumption();
//		car2.setNumberOfSeats();
//		carService.create(car2);
//
//		Car car3 = new Car();
//		car3.setCategory();
//		car3.setBrand();
//		car3.setEngineCapacity();
//		car3.setVolumeCapacity();
//		car3.setMachineDrive();
//		car3.setColor();
//		car3.setConsumption();
//		car3.setNumberOfSeats();
//		carService.create(car3);
//	}
//
//	private void createSUV(CarService carService) {
//		Car car1 = new Car();
//		car1.setCategory();
//		car1.setBrand();
//		car1.setEngineCapacity();
//		car1.setVolumeCapacity();
//		car1.setMachineDrive();
//		car1.setColor();
//		car1.setConsumption();
//		car1.setNumberOfSeats();
//		carService.create(car1);
//
//		Car car2 = new Car();
//		car2.setCategory();
//		car2.setBrand();
//		car2.setEngineCapacity();
//		car2.setVolumeCapacity();
//		car2.setMachineDrive();
//		car2.setColor();
//		car2.setConsumption();
//		car2.setNumberOfSeats();
//		carService.create(car2);
//
//		Car car3 = new Car();
//		car3.setCategory();
//		car3.setBrand();
//		car3.setEngineCapacity();
//		car3.setVolumeCapacity();
//		car3.setMachineDrive();
//		car3.setColor();
//		car3.setConsumption();
//		car3.setNumberOfSeats();
//		carService.create(car3);
//	}
//
//	private void createPremium(CarService carService) {
//		Car car1 = new Car();
//		car1.setCategory();
//		car1.setBrand();
//		car1.setEngineCapacity();
//		car1.setVolumeCapacity();
//		car1.setMachineDrive();
//		car1.setColor();
//		car1.setConsumption();
//		car1.setNumberOfSeats();
//		carService.create(car1);
//
//		Car car2 = new Car();
//		car2.setCategory();
//		car2.setBrand();
//		car2.setEngineCapacity();
//		car2.setVolumeCapacity();
//		car2.setMachineDrive();
//		car2.setColor();
//		car2.setConsumption();
//		car2.setNumberOfSeats();
//		carService.create(car2);
//
//		Car car3 = new Car();
//		car3.setCategory();
//		car3.setBrand();
//		car3.setEngineCapacity();
//		car3.setVolumeCapacity();
//		car3.setMachineDrive();
//		car3.setColor();
//		car3.setConsumption();
//		car3.setNumberOfSeats();
//		carService.create(car3);
//	}
//
//	private void createBusinessClass(CarService carService) {
//		Car car1 = new Car();
//		car1.setCategory();
//		car1.setBrand();
//		car1.setEngineCapacity();
//		car1.setVolumeCapacity();
//		car1.setMachineDrive();
//		car1.setColor();
//		car1.setConsumption();
//		car1.setNumberOfSeats();
//		carService.create(car1);
//
//		Car car2 = new Car();
//		car2.setCategory();
//		car2.setBrand();
//		car2.setEngineCapacity();
//		car2.setVolumeCapacity();
//		car2.setMachineDrive();
//		car2.setColor();
//		car2.setConsumption();
//		car2.setNumberOfSeats();
//		carService.create(car2);
//
//		Car car3 = new Car();
//		car3.setCategory();
//		car3.setBrand();
//		car3.setEngineCapacity();
//		car3.setVolumeCapacity();
//		car3.setMachineDrive();
//		car3.setColor();
//		car3.setConsumption();
//		car3.setNumberOfSeats();
//		carService.create(car3);
//	}

	private void createComfortClass(CarService carService) {
		Car car1 = new Car();
		car1.setCategory(Category.COMFORT_CLASS.getCategory());
		car1.setBrand("Ford Mondeo Black");
		car1.setEngineCapacity(145);
		car1.setVolumeCapacity(2.0);
		car1.setMachineDrive(MachineDrive.FRONT_WHEEL_DRIVE.getWheel());
		car1.setColor("Черный");
		car1.setConsumption(10.0);
		car1.setNumberOfSeats(5);
		car1.setPrice(55.0);
		carService.create(car1);

		Car car2 = new Car();
		car2.setCategory(Category.COMFORT_CLASS.getCategory());
		car2.setBrand("Toyota Corolla");
		car2.setEngineCapacity(122);
		car2.setVolumeCapacity(1.6);
		car2.setMachineDrive(MachineDrive.FRONT_WHEEL_DRIVE.getWheel());
		car2.setColor("Чёрный-Металлик");
		car2.setConsumption(6.0);
		car2.setNumberOfSeats(5);
		car2.setPrice(55.0);
		carService.create(car2);

		Car car3 = new Car();
		car3.setCategory(Category.COMFORT_CLASS.getCategory());
		car3.setBrand("Volkswagen Passat GTE");
		car3.setEngineCapacity(240);
		car3.setVolumeCapacity(1.4);
		car3.setMachineDrive(MachineDrive.FRONT_WHEEL_DRIVE.getWheel());
		car3.setColor("Серый");
		car3.setConsumption(6.0);
		car3.setNumberOfSeats(5);
		car3.setPrice(70.0);
		carService.create(car3);
	}

}
