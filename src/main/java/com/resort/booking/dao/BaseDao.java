package com.resort.booking.dao;

import com.resort.booking.model.Booking;
import com.resort.booking.model.Entity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao<T> {

    public String deleteQuery = "";
    public String updateQuery = "";
    public String addQuery = "";
    public String getQuery = "";
    public String getAllQuery = "";

    static final String DUPLICATED_ID_ERROR_CODE = "ORA-00001";
    public int delete(int id) {
        try (
                ConnectionHelper helper = new ConnectionHelper();
                PreparedStatement preparedStatement = helper.prepareQuery(deleteQuery)
        ) {

            if (get(id) == null) {
                return 1;
            }

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            handleSqlError(e);
        }
        return 0;
    }

    abstract public void update(T model);

    abstract public void add(T model);

    abstract protected T get(int id);

    abstract protected List<T> getAll();

    public void handleSqlError(SQLException e) {
        if (e.getMessage().contains(DUPLICATED_ID_ERROR_CODE))
            System.out.println("Cet identifiant de fournisseur existe déja. Ajout impossible !");
        else
            e.printStackTrace();
    }
}
