package de.dissose17.service;

import de.dissose17.DB2ConnectionManager;
import de.dissose17.data.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author ngnmhieu
 * @since 16.04.17
 */
public class ContractServiceTest extends DBTest
{
    private ContractService service;

    private static Date date = Date.valueOf(LocalDate.of(2017, Month.APRIL, 20));
    private EstateAgentService estateAgentService;
    private EstateService estateService;
    private House house;
    private Apartment apartment;
    private EstateAgent agent;
    private Person person;

    @Before
    public void setUp() throws Exception
    {
        estateAgentService = new EstateAgentService();
        agent = estateAgentService.createAccount("Max", "Kielort", "maxagent", "max12345");

        estateService = new EstateService(estateAgentService);
        house = estateService.createHouse("Hamburg", 22417, "Kielort", 80, 50, agent, 3, 850, true);
        apartment = estateService.createApartment("Hamburg", 22417, "Kielort", 80, 50, agent, 3, 800, 5, false, true);

        service = new ContractService(estateService);
        person = service.addPerson("Han", "Mueller", "Kiwi");
    }

    @Test
    public void addPerson() throws Exception
    {
        Person p = service.addPerson("Han", "Mueller", "Kiwi");
        assertNotNull(p.getId());
    }

    @Test
    public void getPerson() throws Exception
    {
        Person p = service.addPerson("Han", "Mueller", "Kiwi");
        Person person = service.getPerson(p.getId());
        assertEquals(p.getId(), person.getId());
    }

    @Test
    public void createPurchaseContract() throws Exception
    {
        PurchaseContract contract = service.createPurchaseContract(date, "Hamburg", 10, 0.5, house, person);
        assertNotNull(contract.getContractNo());
    }

    @Test
    public void savePurchaseContract() throws Exception
    {
        PurchaseContract contract = service.createPurchaseContract(date, "Hamburg", 10, 0.5, house, person);
        contract.setDate(Date.valueOf(LocalDate.of(2017,12,12)));
        service.savePurchaseContract(contract);

        PreparedStatement stmt = DB2ConnectionManager.getInstance().getConnection().prepareStatement(
                "SELECT * FROM purchaseContract P, contract C WHERE P.contractNo = C.contractNo AND P.contractNo = ?"
        );
        stmt.setInt(1, contract.getContractNo());
        ResultSet rs = stmt.executeQuery();
        assertTrue(rs.next());
        assertTrue(contract.getContractNo() == rs.getInt("contractNo"));
        assertEquals(Date.valueOf(LocalDate.of(2017,12,12)), rs.getDate("date"));

    }

    @Test
    public void createTenancyContract() throws Exception
    {
        TenancyContract contract = service.createTenancyContract(date, "Hamburg", date, 12, 200, apartment, person);
        assertNotNull(contract.getContractNo());
    }

    @Test
    public void saveTenancyContract() throws Exception
    {
        TenancyContract contract = service.createTenancyContract(date, "Hamburg", date, 12, 200, apartment, person);
        contract.setStartDate(Date.valueOf(LocalDate.of(2017,12,12)));
        service.saveTenancyContract(contract);

        PreparedStatement stmt = DB2ConnectionManager.getInstance().getConnection().prepareStatement(
                "SELECT * FROM tenancyContract P, contract C WHERE P.contractNo = C.contractNo AND P.contractNo = ?"
        );
        stmt.setInt(1, contract.getContractNo());
        ResultSet rs = stmt.executeQuery();
        assertTrue(rs.next());
        assertTrue(contract.getContractNo() == rs.getInt("contractNo"));
        assertEquals(Date.valueOf(LocalDate.of(2017,12,12)), rs.getDate("startDate"));
    }

    @Test
    public void getPurchaseContracts() throws Exception
    {
        service.createPurchaseContract(date, "Hamburg", 20, 0.1, house, person);
        service.createPurchaseContract(date, "Stuttgart", 9, 0.2, house, person);
        service.createPurchaseContract(date, "Munich", 10, 0.3, house, person);
        service.createPurchaseContract(date, "Berlin", 20, 0.2, house, person);
        service.createPurchaseContract(date, "Wien", 70, 0.2, house, person);
        List<PurchaseContract> contracts = service.getPurchaseContracts();
        assertEquals(5, contracts.size());
    }

    @Test
    public void getTenancyContracts() throws Exception
    {
        service.createTenancyContract(date, "Hamburg", date, 12, 200, apartment, person);
        service.createTenancyContract(date, "Stuttgart", date, 12, 200, apartment, person);
        service.createTenancyContract(date, "Berlin", date, 12, 200, apartment, person);
        service.createTenancyContract(date, "Wien", date, 12, 200, apartment, person);
        List<TenancyContract> contracts = service.getTenancyContracts();
        assertEquals(4, contracts.size());
    }

}