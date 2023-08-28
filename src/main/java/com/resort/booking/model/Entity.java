package com.resort.booking.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Entity {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract void map(PreparedStatement preparedStatement) throws SQLException;
    public abstract void mapUpdate(PreparedStatement preparedStatement) throws SQLException;
}
