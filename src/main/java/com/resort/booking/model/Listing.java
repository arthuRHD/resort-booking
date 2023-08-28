package com.resort.booking.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Listing extends Entity {
    private String url;
    private String name;
    private String description;
    private String pictureUrl;
    private String latitude;
    private String longitude;
    private float price;
    private int minNight;
    private int maxNight;
    private float availabilityPercent;
    private int reviews;
    private float reviewScore;
    private boolean instantBookable;
    private int hostId;
    private int cityId;
    private int propertyTypeId;

    public Listing(int id, String url, String name, String description, String pictureUrl, String latitude, String longitude, float price, int minNight, int maxNight, float availabilityPercent, int reviews, float reviewScore, boolean instantBookable, int hostId, int cityId, int propertyTypeId) {
        this.setId(id);
        this.url = url;
        this.name = name;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
        this.minNight = minNight;
        this.maxNight = maxNight;
        this.availabilityPercent = availabilityPercent;
        this.reviews = reviews;
        this.reviewScore = reviewScore;
        this.instantBookable = instantBookable;
        this.hostId = hostId;
        this.cityId = cityId;
        this.propertyTypeId = propertyTypeId;
    }

    public Listing(ResultSet resultSet) throws SQLException {
        this.setId(resultSet.getInt("ID"));
        this.name = resultSet.getString("NAME");
        this.description = resultSet.getString("DESCRIPTION");
        this.pictureUrl = resultSet.getString("PICTURE_URL");
        this.latitude = resultSet.getString("LATITUDE");
        this.longitude = resultSet.getString("LONGITUDE");
        this.price = resultSet.getFloat("PRICE");
        this.minNight = resultSet.getInt("MINIMUM_NIGHTS");
        this.maxNight = resultSet.getInt("MAXIMUM_NIGHTS");
        this.availabilityPercent = resultSet.getFloat("AVAILABILITY_365");
        this.reviews = resultSet.getInt("NUMBER_OF_REVIEWS");
        this.reviewScore = resultSet.getFloat("REVIEW_SCORES_RATING");
        this.instantBookable = resultSet.getBoolean("INSTANT_BOOKABLE");
        this.hostId = resultSet.getInt("HOST_ID");
        this.cityId = resultSet.getInt("CITY_ID");
        this.propertyTypeId = resultSet.getInt("PROPERTY_TYPE_ID");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMinNight() {
        return minNight;
    }

    public void setMinNight(int minNight) {
        this.minNight = minNight;
    }

    public int getMaxNight() {
        return maxNight;
    }

    public void setMaxNight(int maxNight) {
        this.maxNight = maxNight;
    }

    public float getAvailabilityPercent() {
        return availabilityPercent;
    }

    public void setAvailabilityPercent(float availabilityPercent) {
        this.availabilityPercent = availabilityPercent;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public float getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(float reviewScore) {
        this.reviewScore = reviewScore;
    }

    public boolean isInstantBookable() {
        return instantBookable;
    }

    public void setInstantBookable(boolean instantBookable) {
        this.instantBookable = instantBookable;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getPropertyTypeId() {
        return propertyTypeId;
    }

    public void setPropertyTypeId(int propertyTypeId) {
        this.propertyTypeId = propertyTypeId;
    }

    @Override
    public void map(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, this.getId());
        preparedStatement.setString(2, this.getUrl());
        preparedStatement.setString(3, this.getName());
        preparedStatement.setString(4, this.getDescription());
        preparedStatement.setString(5, this.getPictureUrl());
        preparedStatement.setString(6, this.getLatitude());
        preparedStatement.setString(7, this.getLongitude());
        preparedStatement.setFloat(8, this.getPrice());
        preparedStatement.setInt(9, this.getMinNight());
        preparedStatement.setInt(10, this.getMaxNight());
        preparedStatement.setFloat(11, this.getAvailabilityPercent());
        preparedStatement.setInt(12, this.getReviews());
        preparedStatement.setFloat(13, this.getReviewScore());
        preparedStatement.setInt(14, this.isInstantBookable() ? 1 : 0);
        preparedStatement.setInt(15, this.getHostId());
        preparedStatement.setInt(16, this.getCityId());
        preparedStatement.setInt(17, this.getPropertyTypeId());
    }

    @Override
    public void mapUpdate(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, this.getUrl());
        preparedStatement.setString(2, this.getName());
        preparedStatement.setString(3, this.getDescription());
        preparedStatement.setString(4, this.getPictureUrl());
        preparedStatement.setString(5, this.getLatitude());
        preparedStatement.setString(6, this.getLongitude());
        preparedStatement.setFloat(7, this.getPrice());
        preparedStatement.setInt(8, this.getMinNight());
        preparedStatement.setInt(9, this.getMaxNight());
        preparedStatement.setFloat(10, this.getAvailabilityPercent());
        preparedStatement.setInt(11, this.getReviews());
        preparedStatement.setFloat(12, this.getReviewScore());
        preparedStatement.setInt(13, this.isInstantBookable() ? 1 : 0);
        preparedStatement.setInt(14, this.getHostId());
        preparedStatement.setInt(15, this.getCityId());
        preparedStatement.setInt(16, this.getPropertyTypeId());
        preparedStatement.setInt(17, this.getId());
    }
}
