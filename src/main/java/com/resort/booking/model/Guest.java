package com.resort.booking.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Guest extends Entity {
    private String name;
    private String email;

    public Guest(int id, String name, String email) {
        this.setId(id);
        this.name = name;
        this.email = email;
    }

    public Guest(ResultSet resultSet) throws SQLException {
        this.setId(resultSet.getInt("ID"));
        this.name = resultSet.getString("NAME");
        this.email = resultSet.getString("EMAIL");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void map(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, this.getId());
        preparedStatement.setString(2, this.getName());
        preparedStatement.setString(3, this.getEmail());
    }

    @Override
    public void mapUpdate(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, this.getName());
        preparedStatement.setString(2, this.getEmail());
        preparedStatement.setInt(3, this.getId());
    }
}
