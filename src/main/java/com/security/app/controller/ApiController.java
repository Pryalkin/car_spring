package com.security.app.controller;

import com.security.app.dto.ApplicationDTO;
import com.security.app.dto.CarDTO;
import com.security.app.dto.answer.CarAnswerDTO;
import com.security.app.dto.util.HttpResponse;
import com.security.app.exception.ExceptionHandling;
import com.security.app.exception.model.PasswordException;
import com.security.app.exception.model.UsernameExistException;
import com.security.app.service.CarService;
import com.security.app.utility.JWTTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.nio.file.Paths;

import static com.security.app.constant.FileConstant.*;
import static com.security.app.constant.HttpAnswer.response;
import static com.security.app.constant.SecurityConstant.TOKEN_PREFIX;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.MimeTypeUtils.IMAGE_JPEG_VALUE;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApiController extends ExceptionHandling {

    private final CarService carService;
    public static final String APPLICATION_SENT = "Application sent";
    private final JWTTokenProvider jwtTokenProvider;

    @PostMapping("/category")
    public ResponseEntity<List<CarAnswerDTO>> getCategory(@RequestParam String category) throws UsernameExistException, PasswordException {
        return new ResponseEntity<>(carService.getCarsByCategory(category), OK);
    }

    @GetMapping("/app")
    public ResponseEntity<List<CarAnswerDTO>> getApp(HttpServletRequest request) throws UsernameExistException, PasswordException {
        String usernameWithToken = getUsernameWithToken(request);
        return new ResponseEntity<>(carService.getApp(usernameWithToken), OK);
    }

    @PostMapping("/application")
    public ResponseEntity<List<CarAnswerDTO>> application(@RequestBody ApplicationDTO applicationDTO,
                                                          HttpServletRequest request) throws UsernameExistException, PasswordException {
        String usernameWithToken = getUsernameWithToken(request);
        return new ResponseEntity<>(carService.application(applicationDTO, usernameWithToken), OK);
    }

    @PostMapping("/car/get")
    public ResponseEntity<CarAnswerDTO> getCar(@RequestParam Long id) throws UsernameExistException, PasswordException {
        return new ResponseEntity<>(carService.getCar(id), OK);
    }


    @PostMapping("/car/create")
    public ResponseEntity<HttpResponse> create(@RequestParam("category") String category,
                                               @RequestParam("brand") String brand,
                                               @RequestParam("engineCapacity") Integer engineCapacity,
                                               @RequestParam("volumeCapacity") Double volumeCapacity,
                                               @RequestParam("machineDrive") String machineDrive,
                                               @RequestParam("color") String color,
                                               @RequestParam("consumption") Double consumption,
                                               @RequestParam("numberOfSeats") Integer numberOfSeats,
                                               @RequestParam("price") Double price,
                                               @RequestParam MultipartFile file) throws IOException{
        CarDTO carDTO = new CarDTO();
        carDTO.setCategory(category);
        carDTO.setBrand(brand);
        carDTO.setEngineCapacity(engineCapacity);
        carDTO.setVolumeCapacity(volumeCapacity);
        carDTO.setMachineDrive(machineDrive);
        carDTO.setColor(color);
        carDTO.setConsumption(consumption);
        carDTO.setNumberOfSeats(numberOfSeats);
        carDTO.setPrice(price);
        carService.createCar(carDTO, file);
        return response(OK, APPLICATION_SENT);
    }

    @GetMapping(path = "/car/{category}/{number}", produces = IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable("category") String category,
                           @PathVariable("number") String number) throws IOException {
        return Files.readAllBytes(Paths.get(USER_FOLDER + category + FORWARD_SLASH + number));
    }

    private String getUsernameWithToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = authorizationHeader.substring(TOKEN_PREFIX.length());
        return jwtTokenProvider.getSubject(token);
    }

}
