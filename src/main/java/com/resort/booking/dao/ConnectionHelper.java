package com.resort.booking.dao;

import java.sql.*;
import java.util.Arrays;

public class ConnectionHelper implements AutoCloseable {

    private final Connection connection;
    public ConnectionHelper() throws SQLException {
        loadDriver();
        String password = "******";
        String login = "******";

        // Intranet
        // String url = "jdbc:oracle:thin:@//srvoracledb.intranet.int:1521/orcl.intranet.int";

        // Remote
        String url = "jdbc:oracle:thin:@oracle.esigelec.fr:1521:orcl";

        this.connection = DriverManager.getConnection(url, login, password);
    }

    public PreparedStatement prepareQuery(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    private void loadDriver() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
            System.out.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
        }
    }

    @Override
    public void close() throws SQLException {
        if (this.connection != null) {
            this.connection.close();
        }
    }
}
