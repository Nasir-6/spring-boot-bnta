package com.amigoscode.car;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private CarDAO carDAO;

    // Change qualifier to faker or postgres
    public CarService(@Qualifier("postgres") CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public void registerNewCar(Car car) {
        // business logic. check if reg number is valid and not take
        if (car.getPrice() <= 0) {
            throw new IllegalStateException("Car price cannot be 0 or less");
        }

        // Check if car Exists in db already - if not null (exists throw exception!)
        Car carInDb = carDAO.selectCarById(car.getId());

        if(carInDb != null){
            throw new IllegalStateException("Car with the id "+ car.getId() + "already exists in the db. Cannot register with the same id");
        }


        // All checks made insertCar
        int result = carDAO.insertCar(car);

        // Anything else retunred then Could not save car
        // But isn't this redundant if always returning 1 - it is for FakeCarDataAccessService
        // But this last part is for when we connect/implement a SQL database (different DAO implementation) where it can go wrong and return a different value other than 1
        if (result != 1) {
            throw new IllegalStateException("Could not save car...");
        }
    }


    public List<Car> getCars() {
        return carDAO.selectAllCars();
    }



    public Car getCarById(Integer id){

        if (id == null){
            throw new IllegalStateException("Id cannot be null");
        }

        // Here we are fullfilling both purposes in the selectCarById method
        // See selectCarById method in the FakeCarDataAccessService for more details
        Car carToGet = carDAO.selectCarById(id);

        if(carToGet == null){
            throw new IllegalStateException("Id of Car to get cannot be found in the database...");
        }else{
            return carToGet;
        }
        // NOTE: There are no checks for 0s and 1s here as SelectCarById success is determined by the returned Car Object
        // null = Fail, actual car = Pass
        // int is returned more for DAO services which are void to allow user to know if method was successful
        // Here the success can be determined by whether we return a car or not (no need for 0s and 1s)

    }







    // DO logic for
    //Car selectCarById(Integer id);
    // Do checks here - check That id exists in the table
    public void deleteCar(Integer id){

        if (id == null){
            throw new IllegalStateException("Id cannot be null");
        }

        // TODO:Add in if car exists in DB ---- TURN THIS INTO A METHOD
        // Don't turn into a method for readability sake
        Car carToDelete = carDAO.selectCarById(id);

        if (carToDelete == null){
            throw new IllegalStateException("Car to delete not found in db..");
        }


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
            throw new IllegalStateException("Car cannot have negative price");
        } else if (update.getRegNumber() == "" || update.getRegNumber() == null ){
            throw new IllegalStateException("Car needs a valid Registration number");
        }//TODO: DO CarBrand check with ENums (Create a new method to return boolean flag, isValidBrand
        // CAN DO A CAR BRAND CHECK DO LATER!!!



        // TODO:Add in if car exists in DB ---- TURN THIS INTO A METHOD
        // ACTUALLY don't turn it into a method for readability's sake
        // This is checking if id exists - so can actually replace
        Car carToUpdate = carDAO.selectCarById(id);

        if(carToUpdate == null){
            throw new IllegalStateException("Car to update Id cannot be found in the database...");
        }


        int result =  carDAO.updateCar(id, update);

        if (result != 1) {
            throw new IllegalStateException("Could not update car...");
        }

    };




}
