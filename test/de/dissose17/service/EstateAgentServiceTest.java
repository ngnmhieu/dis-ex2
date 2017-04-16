package de.dissose17.service;

import de.dissose17.DB2ConnectionManager;
import de.dissose17.data.EstateAgent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * @author ngnmhieu
 * @since 15.04.17
 */
public class EstateAgentServiceTest extends DBTest
{
    private EstateAgentService service;

    @Before
    public void setup() throws SQLException
    {
        service = new EstateAgentService();
    }

    @Test
    public void createAccount() throws Exception
    {
        EstateAgent agent = service.createAccount("Max", "Kielort", "maxagent", "max12345");
        assertNotNull(agent.getId());

        PreparedStatement stmt = DB2ConnectionManager.getInstance().getConnection().prepareStatement("SELECT * FROM estateAgent WHERE ID = ?");
        stmt.setInt(1, agent.getId());
        ResultSet rs = stmt.executeQuery();
        assertTrue(rs.next());
    }

    @Test
    public void save() throws Exception
    {
        EstateAgent agent = service.createAccount("Max", "Kielort", "maxagent", "max12345");
        agent.setAddress("Hamburg");
        service.save(agent);

        PreparedStatement stmt = DB2ConnectionManager.getInstance().getConnection().prepareStatement("SELECT * FROM estateAgent WHERE ID = ?");
        stmt.setInt(1, agent.getId());
        ResultSet rs = stmt.executeQuery();
        assertTrue(rs.next());
        assertTrue(agent.getId() == rs.getInt("ID"));
        assertEquals("Hamburg", rs.getString("address"));
    }

    @Test
    public void delete() throws Exception
    {
        EstateAgent agent = service.createAccount("Max", "Kielort", "maxagent", "max12345");
        service.delete(agent);
        PreparedStatement stmt = DB2ConnectionManager.getInstance().getConnection().prepareStatement("SELECT * FROM estateAgent WHERE ID = ?");
        stmt.setInt(1, agent.getId());
        ResultSet rs = stmt.executeQuery();
        assertFalse(rs.next());
    }

    @Test
    public void authenticateSuccess() throws Exception
    {
        EstateAgent agent = service.createAccount("Max", "Kielort", "maxagent", "max12345");
        assertTrue(service.authenticate("maxagent", "max12345"));
        assertTrue(service.isLoggedIn());
        assertEquals(agent.getId(), service.getCurrentAgent().getId());
    }

    @Test
    public void authenticateFailure() throws Exception
    {
        EstateAgent agent = service.createAccount("Max", "Kielort", "maxagent", "max12345");
        assertFalse(service.authenticate("maxagent", "12345"));
        assertFalse(service.isLoggedIn());
        assertNull(service.getCurrentAgent());
    }
}