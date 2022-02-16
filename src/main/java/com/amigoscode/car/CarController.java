package com.amigoscode.car;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping(path = "cars")
    public void addCar(@RequestBody Car car) {
        carService.registerNewCar(car);
    }


    @GetMapping(path = "cars")
    public List<Car> getCars() {
        return carService.getCars();
    }


    // TODO: ---------------------------------------------------------

//    // DO THIS
//    @GetMapping(path = "cars/{id}")
//    public Car getCarById(@PathVariable("id") Integer carId) {
//        // GET CAR BY ID
//        return carService.getCarById(carId);
//    }

    // TODO: ---------------------------------------------------------

    // Make a path for
    // Each one calls the carservice
//    Car selectCarById(Integer id);
    //    int deleteCar(Integer id);
    // Doesn't return anything

    // DO we need @ResponseBody - NO IT IS REDUNDANT in new spring boot
    @DeleteMapping(path = "cars/{id}")
    public void getCars(@PathVariable("id") Integer id) {
        carService.deleteCar(id);
    }


    //    int updateCar(Integer id, Car update)
    // Take in the id and get repsonsbody
    //@ResponseBody   // response is not needed here as GETCAR will give you it back when you get
    @PutMapping(path = "cars/{id}")
    public void updateCar(@PathVariable("id") Integer id, @RequestBody Car update) {
        carService.updateCar(id, update);
    }




}
