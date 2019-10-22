package com.example.api.serviceImpl;

import com.example.api.entity.Car;
import com.example.api.repository.CarRepo;
import com.example.api.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepo carRepo;

    @Override
    public Iterable<Car> listAll() {
        return carRepo.findAll();
    }

    @Override
    public Optional<Car> listById(Long id) {
        return carRepo.findById(id);
    }

    @Override
    public Car saveCar(Car car) {
        return carRepo.save(car);
    }

    @Override
    public void deleteCar(Car car) {
        carRepo.delete(car);
    }

    @Override
    public void deleteAllCar(Iterable<Car> cars) {
        carRepo.deleteAll(cars);
    }

    @Override
    public List<Car> saveAllCars(List<Car> cars) {
        return carRepo.saveAll(cars);
    }


}
