package com.resort.booking.dao;

import com.resort.booking.model.Booking;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDao extends BaseDao<Booking> {

    private final String deleteWhereGuestId;
    private final String deleteWhereListingId;

    public BookingDao() {
        this.deleteQuery = "DELETE FROM BOOKING WHERE ID = ?";
        this.updateQuery = "UPDATE BOOKING SET LISTING_ID = ?, GUEST_ID = ?, ARRIVAL = ?, NIGHTS = ? WHERE ID = ?";
        this.addQuery = "INSERT INTO BOOKING VALUES (?, ?, ?, ?, ?)";
        this.getQuery = "SELECT * FROM BOOKING WHERE ID = ?";
        this.getAllQuery = "SELECT * FROM BOOKING";
        this.deleteWhereGuestId = "DELETE FROM BOOKING WHERE GUEST_ID = ?";
        this.deleteWhereListingId = "DELETE FROM BOOKING WHERE LISTING_ID = ?";
    }


    @Override
    public void update(Booking model) {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(updateQuery)
        ) {
            if (get(model.getId()) != null) {
                model.mapUpdate(preparedStatement);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            handleSqlError(e);
        }
    }


    @Override
    public void add(Booking model) {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(addQuery)
        ) {

            model.map(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            handleSqlError(e);
        }
    }

    @Override
    public Booking get(int id) {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(getQuery)
        ) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Booking(resultSet);
            }

        } catch (SQLException e) {
            handleSqlError(e);
        }
        return null;
    }

    @Override
    protected List<Booking> getAll() {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(getAllQuery)
        ) {
            List<Booking> outputList = new ArrayList<>();
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                outputList.add(new Booking(results));
            }
            return outputList;

        } catch (SQLException e) {
            handleSqlError(e);
        }
        return null;
    }

    public int deleteWhereGuestId(int guestId) {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(deleteWhereGuestId)
        ) {

            preparedStatement.setInt(1, guestId);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            handleSqlError(e);
        }
        return 0;
    }

    public int deleteWhereListingId(int listingId) {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(deleteWhereListingId)
        ) {

            preparedStatement.setInt(1, listingId);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            handleSqlError(e);
        }
        return 0;
    }
}
