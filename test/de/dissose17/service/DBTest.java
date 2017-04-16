package de.dissose17.service;

import de.dissose17.DB2ConnectionManager;
import org.junit.Before;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author ngnmhieu
 * @since 16.04.17
 */
public class DBTest
{
    @Before
    public void clearDB() throws SQLException
    {
        Connection conn = DB2ConnectionManager.getInstance().getConnection();
        conn.createStatement().executeUpdate("DELETE FROM PURCHASECONTRACT");
        conn.createStatement().executeUpdate("DELETE FROM TENANCYCONTRACT");
        conn.createStatement().executeUpdate("DELETE FROM CONTRACT");
        conn.createStatement().executeUpdate("DELETE FROM PERSON");
        conn.createStatement().executeUpdate("DELETE FROM HOUSE");
        conn.createStatement().executeUpdate("DELETE FROM APARTMENT");
        conn.createStatement().executeUpdate("DELETE FROM ESTATE");
        conn.createStatement().executeUpdate("DELETE FROM ESTATEAGENT");
    }
}
