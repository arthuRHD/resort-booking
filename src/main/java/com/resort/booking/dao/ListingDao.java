package com.resort.booking.dao;

import com.resort.booking.model.Listing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListingDao extends BaseDao<Listing> {

    private final String deleteWhereHostId;
    private final String deleteWhereCityId;
    private final String deleteWherePropertyTypeId;
    private final BookingDao bookingDao;

    public ListingDao() {
        this.bookingDao = new BookingDao();
        this.deleteQuery = "DELETE FROM LISTING WHERE ID = ?";
        this.updateQuery =  "UPDATE LISTING SET URL = ?, NAME = ?, DESCRIPTION = ?, PICTURE_URL = ?, LATITUDE = ?, LONGITUDE = ?, PRICE = ?, MINIMUM_NIGHTS = ?, MAXIMUM_NIGHTS = ?, AVAILABILITY_365 = ?, NUMBER_OF_REVIEWS = ?, REVIEW_SCORES_RATING = ?, INSTANT_BOOKABLE = ?, HOST_ID = ?, CITY_ID = ?, PROPERTY_TYPE_ID = ? WHERE ID = ?";
        this.addQuery = "INSERT INTO LISTING VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getQuery = "SELECT * FROM LISTING WHERE ID = ?";
        this.getAllQuery = "SELECT * FROM LISTING";
        this.deleteWhereHostId = "DELETE FROM LISTING WHERE HOST_ID = ?";
        this.deleteWhereCityId = "DELETE FROM LISTING WHERE CITY_ID = ?";
        this.deleteWherePropertyTypeId = "DELETE FROM LISTING WHERE PROPERTY_TYPE_ID = ?";
    }
    @Override
    public void update(Listing model) {
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
    public void add(Listing model) {
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
    public Listing get(int id) {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(getQuery)
        ) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Listing(resultSet);
            }

        } catch (SQLException e) {
            handleSqlError(e);
        }
        return null;
    }

    @Override
    public List<Listing> getAll() {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(getAllQuery)
        ) {
            List<Listing> outputList = new ArrayList<>();
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                outputList.add(new Listing(results));
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

            this.bookingDao.deleteWhereListingId(id);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            handleSqlError(e);
        }
        return 0;
    }

    public int deleteWhereHostId(int hostId) {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(deleteWhereHostId)
        ) {

            preparedStatement.setInt(1, hostId);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            handleSqlError(e);
        }
        return 0;
    }

    public int deleteWhereCityId(int cityId) {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(deleteWhereCityId)
        ) {

            preparedStatement.setInt(1, cityId);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            handleSqlError(e);
        }
        return 0;
    }

    public int deleteWherePropertyTypeId(int propertyTypeId) {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(deleteWherePropertyTypeId)
        ) {

            preparedStatement.setInt(1, propertyTypeId);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            handleSqlError(e);
        }
        return 0;
    }


}

