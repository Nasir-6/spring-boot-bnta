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



    @Override
    public Car selectCarById(Integer id) {
        // Sort this out
        // Check if can find in the DB
        for (Car car : db) {
            if(car.getId() == id){
                return car;
            }
        }
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
        // Will need to search for it to delete anyways
        // BY THIS POINT IT CAN DO IT 100%
        Car carToDelete = selectCarById(id);
        db.remove(carToDelete);
        return 1;
    }

    @Override
    public int updateCar(Integer id, Car update) {
        // Id to replcae should exist!! at this point !!! as null is checked before
        Car carToUpdate = selectCarById(id);
        carToUpdate.setBrand(update.getBrand());
        carToUpdate.setRegNumber(update.getRegNumber());
        carToUpdate.setPrice(update.getPrice());
        return 1;
    }
}
