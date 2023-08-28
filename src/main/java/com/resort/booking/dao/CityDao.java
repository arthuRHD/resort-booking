package com.resort.booking.dao;

import com.resort.booking.model.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDao extends BaseDao<City> {

    private final ListingDao listingDao;

    public CityDao() {
        this.listingDao = new ListingDao();
        this.deleteQuery = "DELETE FROM CITY WHERE ID = ?";
        this.updateQuery =  "UPDATE CITY SET NAME = ? WHERE ID = ?";
        this.addQuery = "INSERT INTO CITY VALUES (?, ?)";
        this.getQuery = "SELECT * FROM CITY WHERE ID = ?";
        this.getAllQuery = "SELECT * FROM CITY";
    }

    @Override
    public void update(City model) {
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
    public void add(City model) {
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
    public City get(int id) {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(getQuery)
        ) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new City(resultSet);
            }

        } catch (SQLException e) {
            handleSqlError(e);
        }
        return null;
    }

    @Override
    protected List<City> getAll() {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(getAllQuery)
        ) {
            List<City> outputList = new ArrayList<>();
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                outputList.add(new City(results));
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

            this.listingDao.deleteWhereCityId(id);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            handleSqlError(e);
        }
        return 0;
    }
}
