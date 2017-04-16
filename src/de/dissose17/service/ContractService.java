package de.dissose17.service;

import de.dissose17.DB2ConnectionManager;
import de.dissose17.data.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ngnmhieu
 * @since 15.04.17
 */
public class ContractService
{
    private Connection conn = DB2ConnectionManager.getInstance().getConnection();

    private EstateService estateService;

    public ContractService(EstateService estateService)
    {
        this.estateService = estateService;
    }

    /**
     * Add a person to system
     *
     * @param firstName
     * @param lastName
     * @param address
     * @return
     */
    public Person addPerson(String firstName, String lastName, String address)
    {
        Person person = null;
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO " +
                    "person(firstname, name, address) VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, address);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                person = getPerson(rs.getInt(1));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    /**
     * @param id
     * @return
     */
    public Person getPerson(int id)
    {
        PreparedStatement st = null;
        Person p = null;
        try {
            st = conn.prepareStatement("SELECT * FROM person WHERE ID = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                p = new Person(id, rs.getString("firstname"), rs.getString("name"), rs.getString("address"));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    /**
     * Create a new purchase contract
     *
     * @param date
     * @param place
     * @param numInstallments
     * @param interestRate
     * @param house
     * @param owner
     * @return a new contract
     */
    public PurchaseContract createPurchaseContract(Date date, String place, int numInstallments, double interestRate, House house, Person owner)
    {
        return savePurchaseContract(new PurchaseContract(date, place, numInstallments, interestRate, house, owner));
    }

    /**
     * @param contract
     * @return updated PurchaseContract instance
     */
    public PurchaseContract savePurchaseContract(PurchaseContract contract)
    {
        PurchaseContract updatedContract = null;
        try {
            PreparedStatement st = null;
            if (contract.getContractNo() == null) {
                st = conn.prepareStatement("INSERT INTO contract(date, place) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                st.setDate(1, contract.getDate());
                st.setString(2, contract.getPlace());
                st.executeUpdate();

                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int contractId = rs.getInt(1);
                    st = conn.prepareStatement("INSERT INTO purchaseContract(contractNo, noOfInstallments, houseID, ownerID) VALUES (?,?,?,?)");
                    st.setInt(1, contractId);
                    st.setInt(2, contract.getNumInstallments());
                    st.setInt(3, contract.getHouse().getId());
                    st.setInt(4, contract.getOwner().getId());

                    if (st.executeUpdate() > 0) {
                        updatedContract = getPurchaseContract(contractId);
                    }
                }
                rs.close();
            } else {
                st = conn.prepareStatement("UPDATE contract SET date = ?, place = ? WHERE contractNo = ?");
                st.setDate(1, contract.getDate());
                st.setString(2, contract.getPlace());
                st.setInt(3, contract.getContractNo());

                if (st.executeUpdate() > 0) {
                    st = conn.prepareStatement("UPDATE purchaseContract SET noOfInstallments = ?, interestRate = ?, houseID = ?, ownerID = ? WHERE contractNo = ?");
                    st.setInt(1, contract.getNumInstallments());
                    st.setDouble(2, contract.getInterestRate());
                    st.setInt(3, contract.getHouse().getId());
                    st.setInt(4, contract.getOwner().getId());
                    st.setInt(5, contract.getContractNo());

                    if (st.executeUpdate() > 0) {
                        updatedContract = getPurchaseContract(contract.getContractNo());
                    }
                }
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updatedContract;
    }

    /**
     * @param id
     * @return
     */
    public PurchaseContract getPurchaseContract(int id)
    {
        PurchaseContract contract = null;
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM purchaseContract P, contract C WHERE P.contractNo = C.contractNo AND P.contractNo = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                contract = purchaseContractFromResultSet(rs, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contract;
    }

    /**
     * Create a new tenancy contract
     *
     * @param date
     * @param place
     * @param startDate
     * @param duration
     * @param additionalCosts
     * @param apartment
     * @param renter
     * @return a new contract
     */
    public TenancyContract createTenancyContract(Date date, String place, Date startDate, int duration, double additionalCosts, Apartment apartment, Person renter)
    {
        return saveTenancyContract(new TenancyContract(date, place, startDate, duration, additionalCosts, renter, apartment));
    }

    /**
     * @return updated tenancy contract instance
     */
    public TenancyContract saveTenancyContract(TenancyContract contract)
    {
        TenancyContract updatedContract = null;
        try {
            PreparedStatement st = null;
            if (contract.getContractNo() == null) {
                st = conn.prepareStatement("INSERT INTO contract(date, place) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                st.setDate(1, contract.getDate());
                st.setString(2, contract.getPlace());
                st.executeUpdate();

                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int contractId = rs.getInt(1);
                    st = conn.prepareStatement("INSERT INTO tenancyContract(contractNo, startDate, duration, additionalCosts, apartmentID, renterID) VALUES (?,?,?,?,?,?)");
                    st.setInt(1, contractId);
                    st.setDate(2, contract.getStartDate());
                    st.setInt(3, contract.getDuration());
                    st.setDouble(4, contract.getAdditionalCosts());
                    st.setInt(5, contract.getApartment().getId());
                    st.setInt(6, contract.getRenter().getId());

                    if (st.executeUpdate() > 0) {
                        updatedContract = getTenancyContract(contractId);
                    }
                }
                rs.close();
            } else {
                st = conn.prepareStatement("UPDATE contract SET date = ?, place = ? WHERE contractNo = ?");
                st.setDate(1, contract.getDate());
                st.setString(2, contract.getPlace());
                st.setInt(3, contract.getContractNo());

                if (st.executeUpdate() > 0) {
                    st = conn.prepareStatement("UPDATE tenancyContract SET startDate = ?, duration = ?, additionalCosts = ?, apartmentID = ?, renterID = ? WHERE contractNo = ?");
                    st.setDate(1, contract.getStartDate());
                    st.setInt(2, contract.getDuration());
                    st.setDouble(3, contract.getAdditionalCosts());
                    st.setDouble(4, contract.getApartment().getId());
                    st.setDouble(5, contract.getRenter().getId());
                    st.setInt(6, contract.getContractNo());
                    if (st.executeUpdate() > 0) {
                        updatedContract = getTenancyContract(contract.getContractNo());
                    }
                }
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updatedContract;
    }

    /**
     * @param id
     * @return
     */
    public TenancyContract getTenancyContract(int id)
    {
        TenancyContract contract = null;
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM tenancyContract P, contract C WHERE P.contractNo = C.contractNo AND P.contractNo = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                contract = tenancyContractFromResultSet(rs, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contract;
    }

    public List<PurchaseContract> getPurchaseContracts()
    {
        List<PurchaseContract> contracts = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM purchaseContract P, contract C WHERE P.contractNo = C.contractNo");
            while (rs.next()) {
                contracts.add(purchaseContractFromResultSet(rs, null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }

    private PurchaseContract purchaseContractFromResultSet(ResultSet rs, Integer id) throws SQLException
    {
        return new PurchaseContract(id == null ? rs.getInt("contractNo") : id, rs.getDate("date"), rs.getString("place"), rs.getInt("noOfInstallments"),
                rs.getDouble("interestRate"), estateService.getHouse(rs.getInt("houseID")), getPerson(rs.getInt("ownerID")));
    }

    private TenancyContract tenancyContractFromResultSet(ResultSet rs, Integer id) throws SQLException
    {
        return new TenancyContract(id == null ? rs.getInt("contractNo") : id, rs.getDate("date"), rs.getString("place"),
                rs.getDate("startDate"), rs.getInt("duration"), rs.getDouble("additionalCosts"),
                getPerson(rs.getInt("renterID")), estateService.getApartment(rs.getInt("apartmentID")));
    }

    /**
     * @return all tenancy contracts
     */
    public List<TenancyContract> getTenancyContracts()
    {
        List<TenancyContract> contracts = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM tenancyContract T, contract C WHERE T.contractNo = C.contractNo");
            while (rs.next()) {
                contracts.add(tenancyContractFromResultSet(rs, null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }
}
