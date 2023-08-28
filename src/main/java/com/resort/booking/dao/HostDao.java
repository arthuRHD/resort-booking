package com.resort.booking.dao;

import com.resort.booking.model.Host;
import com.resort.booking.model.Listing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HostDao extends BaseDao<Host> {

    private final ListingDao listingDao;
    public HostDao() {
        this.listingDao = new ListingDao();
        this.deleteQuery = "DELETE FROM HOST WHERE ID = ?";
        this.updateQuery =  "UPDATE HOST SET NAME = ?, HOST_SINCE = ?, HOST_RESPONSE_TIME = ?, HOST_ACCEPTANCE_RATE = ?, HOST_IS_SUPERHOST = ? WHERE ID = ?";
        this.addQuery = "INSERT INTO HOST VALUES (?, ?, ?, ?, ?, ?)";
        this.getQuery = "SELECT * FROM HOST WHERE ID = ?";
        this.getAllQuery = "SELECT * FROM HOST";
    }
    @Override
    public void update(Host model) {
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
    public void add(Host model) {
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
    public Host get(int id) {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(getQuery)
        ) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Host(resultSet);
            }

        } catch (SQLException e) {
            handleSqlError(e);
        }
        return null;
    }

    @Override
    public List<Host> getAll() {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(getAllQuery)
        ) {
            List<Host> outputList = new ArrayList<>();
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                try {
                    outputList.add(new Host(results));
                } catch (NullPointerException ignored) {

                }
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

            this.listingDao.deleteWhereHostId(id);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            handleSqlError(e);
        }
        return 0;
    }
}
