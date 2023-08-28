package com.resort.booking.dao;

import com.resort.booking.model.PropertyType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropertyTypeDao extends BaseDao<PropertyType> {

    private final ListingDao listingDao;

    public PropertyTypeDao() {
        this.listingDao = new ListingDao();
        this.deleteQuery = "DELETE FROM PROPERTY_TYPE WHERE ID = ?";
        this.updateQuery =  "UPDATE PROPERTY_TYPE SET NAME = ? WHERE ID = ?";
        this.addQuery = "INSERT INTO PROPERTY_TYPE VALUES (?, ?)";
        this.getQuery = "SELECT * FROM PROPERTY_TYPE WHERE ID = ?";
        this.getAllQuery = "SELECT * FROM PROPERTY_TYPE";
    }
    @Override
    public void update(PropertyType model) {
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
    public void add(PropertyType model) {
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
    public PropertyType get(int id) {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(getQuery)
        ) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new PropertyType(resultSet);
            }

        } catch (SQLException e) {
            handleSqlError(e);
        }
        return null;
    }
    @Override
    public List<PropertyType> getAll() {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(getAllQuery)
        ) {
            List<PropertyType> outputList = new ArrayList<>();
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                outputList.add(new PropertyType(results));
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

            this.listingDao.deleteWherePropertyTypeId(id);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            handleSqlError(e);
        }
        return 0;
    }
}
