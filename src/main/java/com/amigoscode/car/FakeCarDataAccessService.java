package com.amigoscode.car;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fake")
public class FakeCarDataAccessService implements CarDAO {

    private List<Car> db;

    public FakeCarDataAccessService() {
        this.db = new ArrayList<>();
    }


    // This function serves 2 purposes:
    // 1) It is used as a check to make sure the car we are deleting/updating/getting actually exists (see null checks in CarService)
    // 2) It is used to return the car in the getCarById method in CarService
    // IMPORTANT: This method doesn't return 0s or 1s as we can determine it's success via the Car Object being returned!!
    @Override
    public Car selectCarById(Integer id) {
        // Check if can find in the DB
        for (Car car : db) {
            if(car.getId() == id){
                return car;
            }
        }
        // This null is used for purpose 1 mentioned above
        return null;
    }



    @Override
    public List<Car> selectAllCars() {
        return db;
    }

    @Override
    public int insertCar(Car car) {
        // Do we do check - it will never return anything apart from 1
        db.add(car);
        return 1;
    }

    @Override
    public int deleteCar(Integer id) {
        // BY THIS POINT IT CAN DO IT 100% - All checks have been made in CarService
        Car carToDelete = selectCarById(id);
        db.remove(carToDelete);
        return 1;
    }

    @Override
    public int updateCar(Integer id, Car update) {
        // Id to replcae should exist at this point !!! as null is checked in CarService before calling this method
        Car carToUpdate = selectCarById(id);
        carToUpdate.setBrand(update.getBrand());
        carToUpdate.setRegNumber(update.getRegNumber());
        carToUpdate.setPrice(update.getPrice());
        return 1;
    }
}
