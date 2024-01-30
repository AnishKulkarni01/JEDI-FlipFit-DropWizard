package com.flipkart.utils;
import java.sql.*;

import static com.flipkart.constants.Constants.*;

public class DBUtils {
    static final String DB_URL = "jdbc:mysql://localhost:3306/jedi_flipfit_schema";
    static final String USER = "root";
    static final String PASS = "$$Root1$$";
    private static Connection singleInstance = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("Could not find JDBC driver. Make sure you have the MySQL Connector/J JAR in your classpath.");
        }
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public static Connection connect() throws SQLException {
        if (singleInstance == null || singleInstance.isClosed()) {
           // System.out.println(PURPLE_COLOR + "Creating a new connection to DB...." + RESET_COLOR);

            try {
                Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
               // System.out.println(GREEN_COLOR + "Connection to database is successful." + RESET_COLOR);
                singleInstance = connection;
                return connection;
            } catch (SQLException e) {
                throw new RuntimeException(RED_COLOR + "Error in establishing the database connection - " + RESET_COLOR, e);
            }
        } else {
            return singleInstance;
        }
    }
}
