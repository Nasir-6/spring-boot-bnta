package com.amigoscode.car;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private CarDAO carDAO;

    // Change qualifier to faker or postgres
    public CarService(@Qualifier("fake") CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public void registerNewCar(Car car) {
        // business logic. check if reg number is valid and not take
        if (car.getPrice() <= 0) {
            throw new IllegalStateException("Car price cannot be 0 or less");
        }
        // Maybe check if id is here

        int result = carDAO.insertCar(car);

        // Anything else retunred then Could not save car
        // But isn't this redundant if always returning 1
        if (result != 1) {
            throw new IllegalStateException("Could not save car...");
        }
    }

    public List<Car> getCars() {
        return carDAO.selectAllCars();
    }



   /* public Car getCarById(Integer id){

        if (id == null){
            throw new IllegalStateException("Id cannot be null");
        }

        // This is checking if id exists - so can actually replace
        Car carToGet = carDAO.selectCarById(id);

        if(carToGet == null){
            throw new IllegalStateException("Id cannot be found in the database...");
        }

        int result =  carDAO.getCarById(id);

        if (result != 1) {
            throw new IllegalStateException("Could not update car...");
        }

    }*/







    // DO logic for
    // Each one calls the carservice
    //Car selectCarById(Integer id);
    // Do checks here - check That id exists in the table
    //
    public void deleteCar(Integer id){

        if (id == null){
            throw new IllegalStateException("Id cannot be null");
        }

        // TODO:Add in if car exists in DB ---- TURN THIS INTO A METHOD
        Car carToDelete = carDAO.selectCarById(id);

        if (carToDelete == null){
            throw new IllegalStateException("Car to delete not found in db..");
        }

        //TODO -----------------------


        int result =  carDAO.deleteCar(id);

        if (result != 1) {
            throw new IllegalStateException("Could not delete car...");
        }

    };
    //


    //    int updateCar(Integer id, Car update)
    public void updateCar(Integer id, Car update){

        if (id == null){
            throw new IllegalStateException("Id cannot be null");
        }else if (update == null){
            throw new IllegalStateException("Car cannot be null");
        } else if (update.getPrice() < 0 ){

        }

        // TODO:Add in if car exists in DB ---- TURN THIS INTO A METHOD
        // This is checking if id exists - so can actually replace
        Car carToReplace = carDAO.selectCarById(id);

        if(carToReplace == null){
            throw new IllegalStateException("Id cannot be found in the database...");
        }


        int result =  carDAO.updateCar(id, update);

        if (result != 1) {
            throw new IllegalStateException("Could not update car...");
        }

    };




}
