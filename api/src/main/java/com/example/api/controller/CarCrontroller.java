package com.example.api.controller;

import com.example.api.entity.Car;
import com.example.api.exception.CarNotFoundException;
import com.example.api.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/cars")
@RestController
public class CarCrontroller {

    @Autowired
    private CarService carService;

    @GetMapping(value = "/all")
    public Iterable<Car> showAllCars() {
        return carService.listAll();
    }

    @GetMapping(value = "/all/{id}")
    public Optional<Car> showAllCars(@PathVariable Long id) {
        return carService.listById(id);
    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public Car saveCar(@RequestBody @NotNull Car car) {
        return carService.saveCar(car);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Car updateCar(@RequestBody @NotNull Car newCar, @PathVariable Long id) {
        return listById(newCar, id);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCar(@PathVariable Long id) {
        Car car = carService.listById(id).orElseThrow(() -> new CarNotFoundException());
        carService.deleteCar(car);
    }

    @DeleteMapping(value = "/deleteAll")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllCar()  {
        Iterable<Car> car = carService.listAll();
        carService.deleteAllCar(car);
    }

    @PostMapping(value = "/saveAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> saveAllCar(@RequestBody List<Car> allCar) {
        return carService.saveAllCars(allCar);
    }

    @PutMapping(value = "/updateAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> updateAllCar(@RequestBody List<Car> newAllCar) {
        return newListCars(newAllCar);
    }

    private Car listById(@RequestBody @NotNull Car newCar, @PathVariable Long id) {
        return carService.listById(id)
                .map(car -> {
                    car.setType(newCar.getType());
                    car.setBrand(newCar.getBrand());
                    car.setModel(newCar.getModel());
                    car.setColor(newCar.getColor());
                    car.setYear(newCar.getYear());
                    return carService.saveCar(car);
                })
                .orElseThrow(() -> new CarNotFoundException());
    }

    private List<Car> newListCars(@RequestBody List<Car> newAllCar) {
        List<Car> newListCar = new ArrayList<>();

        newAllCar.forEach(newCar->{
                Car car = carService.listById(newCar.getId())
                    .orElseThrow(() -> new CarNotFoundException());

                    car.setType(newCar.getType());
                    car.setBrand(newCar.getBrand());
                    car.setModel(newCar.getModel());
                    car.setColor(newCar.getColor());
                    car.setYear(newCar.getYear());
                    newListCar.add(car);
                });

        return carService.saveAllCars(newListCar);
    }
}
