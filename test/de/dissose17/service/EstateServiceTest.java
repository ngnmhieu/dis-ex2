package de.dissose17.service;

import de.dissose17.DB2ConnectionManager;
import de.dissose17.data.Apartment;
import de.dissose17.data.EstateAgent;
import de.dissose17.data.House;
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
 * @since 16.04.17
 */
public class EstateServiceTest extends DBTest
{
    private EstateService service;

    private EstateAgent agent1;

    private EstateAgent agent2;

    @Before
    public void setup()
    {
        service = new EstateService(new EstateAgentService());
        EstateAgentService agentService = new EstateAgentService();
        agent1 = agentService.createAccount("Max", "Kielort", "maxagent", "max12345");
        agent2 = agentService.createAccount("Toni", "Wattkorn", "toniagent", "toni12345");
    }

    @Test
    public void createHouse() throws Exception
    {
        House house = service.createHouse("Hamburg", 22417, "Kielort", 80, 50, agent1, 3, 850, true);
        assertNotNull(house.getId());

        PreparedStatement stmt = DB2ConnectionManager.getInstance().getConnection().prepareStatement("SELECT * FROM house H, estate E WHERE H.ID = E.ID AND H.ID = ?");
        stmt.setInt(1, house.getId());
        ResultSet rs = stmt.executeQuery();
        assertTrue(rs.next());
        assertTrue(agent1.getId() == rs.getInt("agentID"));
        assertEquals("Hamburg", rs.getString("city"));
    }

    @Test
    public void createApartment() throws Exception
    {
        Apartment apartment = service.createApartment("Hamburg", 22417, "Kielort", 80, 50, agent1, 3, 800, 5, false, true);
        assertNotNull(apartment.getId());
        PreparedStatement stmt = DB2ConnectionManager.getInstance().getConnection().prepareStatement("SELECT * FROM apartment A, estate E WHERE A.ID = E.ID AND A.ID = ?");
        stmt.setInt(1, apartment.getId());
        ResultSet rs = stmt.executeQuery();
        assertTrue(rs.next());
        assertTrue(agent1.getId() == rs.getInt("agentID"));
        assertEquals("Kielort", rs.getString("street"));
    }

    @Test
    public void saveHouse() throws Exception
    {
        House house = service.createHouse("Hamburg", 22417, "Kielort", 80, 50, agent1, 3, 850, true);
        assertNotNull(house.getId());
        house.setNumFloors(5);
        house.setAgent(agent2);
        service.saveHouse(house);
        assertTrue(agent2.getId() == house.getAgent().getId());
        assertEquals(5, house.getNumFloors());
    }

    @Test
    public void saveApartment() throws Exception
    {
        Apartment apartment = service.createApartment("Hamburg", 22417, "Kielort", 80, 50, agent1, 3, 800, 5, false, true);
        assertNotNull(apartment.getId());
        apartment.setHasBuiltInKitchen(false);
        apartment.setAgent(agent2);
        service.saveApartment(apartment);
        assertTrue(agent2.getId() == apartment.getAgent().getId());
        assertFalse(apartment.hasBuiltInKitchen());
    }

    @Test
    public void deleteApartment() throws Exception
    {
        Apartment apartment = service.createApartment("Hamburg", 22417, "Kielort", 80, 50, agent1, 3, 800, 5, false, true);

        service.deleteEstate(apartment);

        PreparedStatement stmt = DB2ConnectionManager.getInstance().getConnection().prepareStatement("SELECT * FROM estate WHERE ID = ?");
        stmt.setInt(1, apartment.getId());
        ResultSet rs = stmt.executeQuery();
        assertFalse(rs.next());

        stmt = DB2ConnectionManager.getInstance().getConnection().prepareStatement("SELECT * FROM apartment WHERE ID = ?");
        stmt.setInt(1, apartment.getId());
        rs = stmt.executeQuery();
        assertFalse(rs.next());
    }

    @Test
    public void deleteHouse() throws Exception
    {
        House house = service.createHouse("Hamburg", 22417, "Kielort", 80, 50, agent1, 3, 850, true);

        service.deleteEstate(house);

        PreparedStatement stmt = DB2ConnectionManager.getInstance().getConnection().prepareStatement("SELECT * FROM estate WHERE ID = ?");
        stmt.setInt(1, house.getId());
        ResultSet rs = stmt.executeQuery();
        assertFalse(rs.next());

        stmt = DB2ConnectionManager.getInstance().getConnection().prepareStatement("SELECT * FROM house WHERE ID = ?");
        stmt.setInt(1, house.getId());
        rs = stmt.executeQuery();
        assertFalse(rs.next());
    }
}