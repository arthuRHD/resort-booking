package com.resort.booking.dao;

import com.resort.booking.model.Guest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestDao extends BaseDao<Guest> {

    private final BookingDao bookingDao;

    public GuestDao() {
        this.bookingDao = new BookingDao();
        this.deleteQuery = "DELETE FROM GUEST WHERE ID = ?";
        this.updateQuery =  "UPDATE GUEST SET NAME = ?, EMAIL = ? WHERE ID = ?";
        this.addQuery = "INSERT INTO GUEST VALUES (?, ?, ?)";
        this.getQuery = "SELECT * FROM GUEST WHERE ID = ?";
        this.getAllQuery = "SELECT * FROM GUEST";
    }
    @Override
    public void update(Guest model) {
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
    public void add(Guest model) {
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
    public Guest get(int id) {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(getQuery)
        ) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Guest(resultSet);
            }

        } catch (SQLException e) {
            handleSqlError(e);
        }
        return null;
    }

    @Override
    protected List<Guest> getAll() {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(getAllQuery)
        ) {
            List<Guest> outputList = new ArrayList<>();
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                outputList.add(new Guest(results));
            }
            return outputList;

        } catch (SQLException e) {
            handleSqlError(e);
        }
        return null;
    }

    @Override
    public int delete(int id) {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(deleteQuery)
        ) {

            if (get(id) == null) {
                return 1;
            }

            this.bookingDao.deleteWhereGuestId(id);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            handleSqlError(e);
        }
        return 0;
    }


}
