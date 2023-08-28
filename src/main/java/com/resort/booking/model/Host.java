package com.resort.booking.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Host extends Entity {
    private String name;
    private LocalDateTime creationDate;
    private String responseTime;
    private float acceptanceRate;
    private boolean isSuperHost;

    public Host(int id, String name, LocalDateTime creationDate, String responseTime, float acceptanceRate, boolean isSuperHost) {
        this.setId(id);
        this.name = name;
        this.creationDate = creationDate;
        this.responseTime = responseTime;
        this.acceptanceRate = acceptanceRate;
        this.isSuperHost = isSuperHost;
    }

    public Host(ResultSet resultSet) throws SQLException {
        this.setId(resultSet.getInt("ID"));
        this.name = resultSet.getString("NAME");
        this.creationDate = LocalDateTime.of(resultSet.getDate("HOST_SINCE").toLocalDate(), LocalTime.MIN);
        this.responseTime = resultSet.getString("HOST_RESPONSE_TIME");
        this.acceptanceRate = resultSet.getFloat("HOST_ACCEPTANCE_RATE");
        this.isSuperHost = resultSet.getBoolean("HOST_IS_SUPERHOST");
    }

    public static String[] getColumnNames() {
        return new String[]{"ID", "Nom", "Date de création", "Temps de réponse", "Taux d'acceptation", "Superhost"};
    }

    public Object[] toRowData() {
        return new Object[]{this.getId(), this.name, this.creationDate, this.responseTime, this.acceptanceRate, this.isSuperHost};
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public float getAcceptanceRate() {
        return acceptanceRate;
    }

    public void setAcceptanceRate(float acceptanceRate) {
        this.acceptanceRate = acceptanceRate;
    }

    public boolean isSuperHost() {
        return isSuperHost;
    }

    public void setSuperHost(boolean superHost) {
        isSuperHost = superHost;
    }

    @Override
    public void map(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, this.getId());
        preparedStatement.setString(2, this.getName());
        preparedStatement.setDate(3, Date.valueOf(this.getCreationDate().toLocalDate()));
        preparedStatement.setString(4, this.getResponseTime());
        preparedStatement.setFloat(5, this.getAcceptanceRate());
        preparedStatement.setInt(6, this.isSuperHost() ? 1 : 0);
    }

    @Override
    public void mapUpdate(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, this.getName());
        preparedStatement.setDate(2, Date.valueOf(this.getCreationDate().toLocalDate()));
        preparedStatement.setString(3, this.getResponseTime());
        preparedStatement.setFloat(4, this.getAcceptanceRate());
        preparedStatement.setInt(5, this.isSuperHost() ? 1 : 0);
        preparedStatement.setInt(6, this.getId());
    }
}
