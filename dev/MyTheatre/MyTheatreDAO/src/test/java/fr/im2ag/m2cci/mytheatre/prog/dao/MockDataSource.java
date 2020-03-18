package fr.im2ag.m2cci.mytheatre.prog.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 * Mock Object pour simuler la DataSource qui dans l'applciation web est gérée
 * par Tomcat.
 *
 * @author Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
 */
public class MockDataSource implements DataSource {

    // paramètres de connexion JDBC
    private String dbURL;
    private String user;
    private String passwd;

    /**
     * Creates a new instance of TestDataSource
     *
     * @param jdbcDriver  nom qualifié de la classe du pilote JDBC
     * @param dbURL url de connexion à la BD
     * @param user nom de l'utilisateur (null si inutile)
     * @param passwd mot de passe (null si inutile)
     */
    public MockDataSource(String jdbcDriver, String dbURL, String user, String passwd) {
        try {
            Class.forName(jdbcDriver);
            this.dbURL = dbURL;
            this.user = user;
            this.passwd = passwd;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver " + jdbcDriver + " non trouvé");
            System.exit(0);
        }
    }

    public MockDataSource() {
        String jdbcDriver = null;
        try {
            Properties options = new Properties();
            options.load(new FileInputStream(new File("/home/nico/tmp/m2cci-1920-pi-GP02/dev/MyTheatre/MyTheatreDAO/bd/jdbc.properties")));
            jdbcDriver = options.getProperty("jdbcDriver");
            this.dbURL = options.getProperty("dataBaseUrl");
            this.user = null;
            this.passwd = null;
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver " + jdbcDriver + " non trouvé");
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("Problème chargement du fichier jdbc.properties");
            System.exit(0);
        }
    }

    /**
     * @return une connexion "normale " (pas gérée avec un pool de connexions")
     * @throws java.sql.SQLException
     */
    @Override
    public Connection getConnection() throws java.sql.SQLException {
        return DriverManager.getConnection(dbURL, user, passwd);
    }

    // les autre méthodes de l'interface javax.sql.DataSource ne font rien
    // à part lancer une exception. Ne sont jamais invoquées dans les tests.
    @Override
    public void setLoginTimeout(int seconds) throws java.sql.SQLException {
        throw new SQLException("NYI");
    }

    @Override
    public void setLogWriter(java.io.PrintWriter out) throws java.sql.SQLException {
        throw new SQLException("NYI");
    }

    @Override
    public int getLoginTimeout() throws java.sql.SQLException {

        throw new SQLException("NYI");
    }

    @Override
    public java.io.PrintWriter getLogWriter() throws java.sql.SQLException {

        throw new SQLException("NYI");
    }

    @Override
    public java.sql.Connection getConnection(String username, String password) throws java.sql.SQLException {
        throw new SQLException("NYI");
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new SQLException("NYI");
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new SQLException("NYI");
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * un petit programme pour tester si la datasource marche. Ouvre et ferme
     * une connexion.
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        DataSource ds = new MockDataSource();
        System.out.println("DataSource OK");
        try (Connection con = ds.getConnection()) {
            System.out.println("connexion OK");
        }
        System.out.println("connexion fermée");
    }

}
