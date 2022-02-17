package com.amigoscode.car;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


// The sole purpose of this implementation is to make SQL commands to access the DB and return their row affected or object
@Repository("postgres")
public class CarDataAccessService implements CarDAO {

    private JdbcTemplate jdbcTemplate;

    public CarDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Car selectCarById(Integer id) {
        // todo: implement this method to get car by id from database
        return null;
    }


    @Override
    public List<Car> selectAllCars() {
        // Start with the Sql command
        String sql = """
                SELECT id, regnumber, brand, price
                FROM car
                """;

        // carRowMapper is a method/lamda that goes through each row and converts the data into a car Object and returns
        RowMapper<Car> carRowMapper = (rs, rowNum) -> {
            Car car = new Car(
                    rs.getInt("id"),
                    rs.getString("regnumber"),
                    Brand.valueOf(rs.getString("brand")),
                    rs.getDouble("price")
            );
            return car;
        };

        //query combined with carRowMapper will go through the returned sql table, and return a list of cars
        List<Car> cars = jdbcTemplate.query(sql, carRowMapper);

        // ALTERNATIVE shorthand lamda method - carRowMapper is not named rather just used as a lamda expression!!
        /*
            List<Car> cars = jdbcTemplate.query(sql, (rs, rowNum) -> {
                Car car = new Car(
                        rs.getInt("id"),
                        rs.getString("regnumber"),
                        Brand.valueOf(rs.getString("brand")),
                        rs.getDouble("price")
                );
                return car;
            });
        */

        // Return the list of cars -> Service -> API -> Client
        return cars;
    }


    @Override
    public int insertCar(Car car) {
        // Note the sql command doesn't need the id as it is a SERIAL and is generated automatically
        String sql = """
                INSERT INTO car(regnumber, brand, price)
                VALUES(?, ?, ?)
                """;

        // Use .update Method when Inserting/deleting/updating
        int rowsAffected = jdbcTemplate.update(
                sql,
                car.getRegNumber(),
                car.getBrand().name(),
                car.getPrice()
        );
        // Should return 1 - anything else it should throw error in the service!!
        return rowsAffected;
    }


    @Override
    public int deleteCar(Integer id) {
        String sql = "DELETE FROM car WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }


    @Override
    public int updateCar(Integer id, Car update) {
        // todo: implement this method
        return 0;
    }
}
