package com.resort.booking;

import com.resort.booking.dao.*;
import com.resort.booking.model.*;

import java.time.LocalDateTime;
import java.util.Objects;

public class TestOperations {

    public static void main( String[] args )
    {
        BookingDao bookingDao = new BookingDao();
        CityDao cityDao = new CityDao();
        GuestDao guestDao = new GuestDao();
        HostDao hostDao = new HostDao();
        ListingDao listingDao = new ListingDao();
        PropertyTypeDao propertyTypeDao = new PropertyTypeDao();

        propertyTypeDao.delete(100);
        listingDao.delete(100);
        bookingDao.delete(100);
        hostDao.delete(100);
        hostDao.delete(101);
        cityDao.delete(100);
        guestDao.delete(100);

        City city = new City(100, "Paris");
        Guest guest = new Guest(100, "Jean DUPONT", "jean.dupont@gmail.com");
        PropertyType propertyType = new PropertyType(100, "HOUSE");
        Host host = new Host(100, "HostName", LocalDateTime.now(), "IMMEDIATE", 5.6F, false);
        Listing listing = new Listing(100, "https://google.fr", "My Super House", "","https://pictureurl.com", "+55", "-85", 78F, 1, 5, 80F, 8752, 4.5F, false, 100, 100, 100);
        Booking booking = new Booking(100, LocalDateTime.now(), 7, 100, 100);

        cityDao.add(city);
        guestDao.add(guest);
        propertyTypeDao.add(propertyType);
        hostDao.add(host);
        listingDao.add(listing);

        System.out.println("Added a listing, a city, a guest, a property type and a host: " + (Objects.equals(listingDao.get(listing.getId()).getName(), "My Super House") ? "SUCCESS" : "FAILED"));

        bookingDao.add(booking);
        booking.setNights(10);
        bookingDao.update(booking);

        System.out.println("Update persisted in database: " + (bookingDao.get(100).getNights() == 10 ? "SUCCESS" : "FAILED"));

        Host myNewHost = new Host(101, "Jean-jacques DUPONT", LocalDateTime.now(), "DIFFERED", 23.5F, true);
        hostDao.add(myNewHost);
        myNewHost.setName("Jean-jacques DUPONT");
        hostDao.update(myNewHost);
        Host hostFromDatabase = hostDao.get(myNewHost.getId());

        System.out.println("Added and retrieved from database: " + (Objects.equals(hostFromDatabase.getName(), myNewHost.getName()) ? "SUCCESS" : "FAILED"));

        PropertyType propertyType2 = new PropertyType(101, "APARTMENT");
        propertyTypeDao.add(propertyType2);
        propertyTypeDao.delete(propertyType2.getId());
        System.out.println("Deleted in database: " + (propertyTypeDao.get(propertyType2.getId()) == null ? "SUCCESS" : "FAILED"));

        bookingDao.delete(100);
        listingDao.delete(100);
        hostDao.delete(100);
        hostDao.delete(101);
        cityDao.delete(100);
        guestDao.delete(100);
        propertyTypeDao.delete(100);
    }
}
