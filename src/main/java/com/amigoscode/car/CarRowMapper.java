package com.amigoscode.car;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRowMapper implements RowMapper<Car> {
    @Override
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Car(
                rs.getInt("id"),
                rs.getString("regnumber"),
                Brand.valueOf(rs.getString("brand")),
                rs.getDouble("price")
        );
    };

}
