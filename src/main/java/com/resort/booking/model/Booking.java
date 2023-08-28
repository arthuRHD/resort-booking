package com.resort.booking.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Booking extends Entity {
    private LocalDateTime arrivalDate;
    private int nights;
    private int listingId;
    private int guestId;

    public Booking(int id, LocalDateTime arrivalDate, int nights, int listingId, int guestId) {
        this.setId(id);
        this.arrivalDate = arrivalDate;
        this.nights = nights;
        this.listingId = listingId;
        this.guestId = guestId;
    }

    public Booking(ResultSet resultSet) throws SQLException {
        this.setId(resultSet.getInt("ID"));
        this.arrivalDate = LocalDateTime.of(resultSet.getDate("ARRIVAL").toLocalDate(), LocalTime.MIN);;
        this.listingId = resultSet.getInt("LISTING_ID");
        this.guestId = resultSet.getInt("GUEST_ID");
        this.nights = resultSet.getInt("NIGHTS");
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    @Override
    public void map(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, this.getId());
        preparedStatement.setInt(2, this.getListingId());
        preparedStatement.setInt(3, this.getGuestId());
        preparedStatement.setDate(4, Date.valueOf(this.getArrivalDate().toLocalDate()));
        preparedStatement.setInt(5, this.getNights());
    }

    @Override
    public void mapUpdate(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, this.getListingId());
        preparedStatement.setInt(2, this.getGuestId());
        preparedStatement.setDate(3, Date.valueOf(this.getArrivalDate().toLocalDate()));
        preparedStatement.setInt(4, this.getNights());
        preparedStatement.setInt(5, this.getId());

    }
}
