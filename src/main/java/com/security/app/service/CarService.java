package com.security.app.service;

import com.security.app.dto.ApplicationDTO;
import com.security.app.dto.CarDTO;
import com.security.app.dto.answer.CarAnswerDTO;
import com.security.app.exception.model.UsernameExistException;
import com.security.app.model.Car;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService {
    List<CarAnswerDTO> getCarsByCategory(String category);

    void create(Car car);

    void createCar(CarDTO carDTO, MultipartFile image) throws IOException;

    CarAnswerDTO getCar(Long id);

    List<CarAnswerDTO> application(ApplicationDTO applicationDTO, String usernameWithToken) throws UsernameExistException;

    List<CarAnswerDTO> getApp(String usernameWithToken) throws UsernameExistException;
}
