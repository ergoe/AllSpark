package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {
    private static final Logger LOG = LoggerFactory.getLogger(SqlConnection.class);

    private Connection connection;
    private String dbURL = "";
    private String user = "";
    private String pass = "";

    public SqlConnection() {

    }

    public Connection getConnection() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(dbURL, user, pass);

        } catch (SQLException sqlex) {
            LOG.error("SQL exc", sqlex.getLocalizedMessage());
        } catch (ClassNotFoundException cnfex) {
            LOG.error("Unable to find jdbc Driver class", cnfex.getLocalizedMessage());

        } catch (IllegalAccessException iaex) {
            LOG.error("Illegal access exception for Class.forName", iaex.getLocalizedMessage());

        } catch (InstantiationException iex) {
            LOG.error("Instantiation exception for Class.forName", iex.getLocalizedMessage());
        }

        return connection;
    }

}
