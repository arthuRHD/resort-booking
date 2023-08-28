package com.resort.booking.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class City extends Entity {
    private String name;

    public City(int id, String name) {
        this.setId(id);
        this.name = name;
    }

    public City(ResultSet resultSet) throws SQLException {
        this.setId(resultSet.getInt("ID"));
        this.name = resultSet.getString("NAME");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void map(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, this.getId());
        preparedStatement.setString(2, this.getName());
    }

    @Override
    public void mapUpdate(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, this.getName());
        preparedStatement.setInt(2, this.getId());
    }
}
