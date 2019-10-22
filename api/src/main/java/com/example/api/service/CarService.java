package com.example.api.service;

import com.example.api.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    Iterable<Car> listAll();

    Optional<Car> listById(Long id);

    Car saveCar(Car car);

    void deleteCar(Car car);

    void deleteAllCar(Iterable<Car> cars);

    List<Car> saveAllCars(List<Car> cars);
}
