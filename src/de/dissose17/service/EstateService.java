package de.dissose17.service;

import de.dissose17.DB2ConnectionManager;
import de.dissose17.data.Apartment;
import de.dissose17.data.Estate;
import de.dissose17.data.EstateAgent;
import de.dissose17.data.House;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author ngnmhieu
 * @since 15.04.17
 */
public class EstateService
{
    private Connection conn = DB2ConnectionManager.getInstance().getConnection();

    private EstateAgentService estateAgentService;

    public EstateService(EstateAgentService estateAgentService)
    {
        this.estateAgentService = estateAgentService;
    }

    /**
     * @param city
     * @param postalCode
     * @param street
     * @param streetNumber
     * @param squareArea
     * @param agent
     * @param numFloors
     * @param price
     * @param hasGarden
     * @return
     */
    public House createHouse(String city, int postalCode, String street, int streetNumber, int squareArea, EstateAgent agent, int numFloors, double price, boolean hasGarden)
    {
        return saveHouse(new House(city, postalCode, street, streetNumber, squareArea, agent, numFloors, price, hasGarden));
    }

    /**
     * @param city
     * @param postalCode
     * @param street
     * @param streetNumber
     * @param squareArea
     * @param agent
     * @param floor
     * @param rentPrice
     * @param numRooms
     * @param hasBalcony
     * @param hasBuiltInKitchen
     * @return
     */
    public Apartment createApartment(String city, int postalCode, String street, int streetNumber, int squareArea, EstateAgent agent, int floor, double rentPrice, int numRooms, boolean hasBalcony, boolean hasBuiltInKitchen)
    {
        return saveApartment(new Apartment(city, postalCode, street, streetNumber, squareArea, agent, floor, rentPrice, numRooms, hasBalcony, hasBuiltInKitchen));
    }


    /**
     * @param id Estate ID
     * @return House instance
     */
    public House getHouse(int id)
    {
        House house = null;
        try {
            String selectSQL = "SELECT * FROM house H, estate E WHERE H.ID = E.ID AND H.ID = ?";
            PreparedStatement stmt = conn.prepareStatement(selectSQL);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int agentID = rs.getInt("agentID");
                EstateAgent agent = estateAgentService.getEstateAgent(agentID);
                house = new House(id, rs.getString("city"), rs.getInt("postalcode"), rs.getString("street"), rs.getInt("streetnumber"),
                        rs.getInt("squarearea"), agent, rs.getInt("floors"), rs.getDouble("price"), rs.getBoolean("garden"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return house;
    }

    public Vector<House> getAllHousesForAgent(int agentID){
        Vector<House> houses= new Vector<>();
        try {
            String selectSQL = "SELECT * FROM house H, estate E WHERE H.ID = E.ID AND E.AGENTID = "+ agentID;
            PreparedStatement stmt = conn.prepareStatement(selectSQL);
            //stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                EstateAgent agent = estateAgentService.getEstateAgent(agentID);
                houses.add(new House(rs.getInt("Id"), rs.getString("city"), rs.getInt("postalcode"), rs.getString("street"), rs.getInt("streetnumber"),
                        rs.getInt("squarearea"), agent, rs.getInt("floors"), rs.getDouble("price"), rs.getBoolean("garden")));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return houses;
    }

    /**
     * @param house
     * @return
     */
    public House saveHouse(House house)
    {
        PreparedStatement stmt = null;
        House updatedHouse = null;
        try {
            if (house.getId() == null) {
                stmt = conn.prepareStatement("INSERT INTO estate(city, postalcode, street, streetnumber, squarearea, agentID) VALUES (?,?,?,?,?,?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, house.getCity());
                stmt.setInt(2, house.getPostalCode());
                stmt.setString(3, house.getStreet());
                stmt.setInt(4, house.getStreetNumber());
                stmt.setInt(5, house.getSquareArea());
                stmt.setInt(6, house.getAgent().getId());
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int estateId = rs.getInt(1);
                    stmt = conn.prepareStatement("INSERT INTO house(ID, floors, price, garden) VALUES (?,?,?,?)");
                    stmt.setInt(1, estateId);
                    stmt.setInt(2, house.getNumFloors());
                    stmt.setDouble(3, house.getPrice());
                    stmt.setBoolean(4, house.hasGarden());

                    if (stmt.executeUpdate() > 0) {
                        updatedHouse = getHouse(estateId);
                    }
                }
                rs.close();
            } else {
                stmt = conn.prepareStatement("UPDATE estate SET city = ?, postalcode = ?, street = ?, streetnumber = ?, squarearea = ?, agentID = ? WHERE ID = ?");
                stmt.setString(1, house.getCity());
                stmt.setInt(2, house.getPostalCode());
                stmt.setString(3, house.getStreet());
                stmt.setInt(4, house.getStreetNumber());
                stmt.setInt(5, house.getSquareArea());
                stmt.setInt(6, house.getAgent().getId());
                stmt.setInt(7, house.getId());

                if (stmt.executeUpdate() > 0) {
                    stmt = conn.prepareStatement("UPDATE house SET floors = ?, price = ?, garden = ? WHERE ID = ?");
                    stmt.setInt(1, house.getNumFloors());
                    stmt.setDouble(2, house.getPrice());
                    stmt.setBoolean(3, house.hasGarden());
                    stmt.setInt(4, house.getId());


                    if (stmt.executeUpdate() > 0) {
                        updatedHouse = getHouse(house.getId());
                    }
                }
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updatedHouse;
    }

    /**
     * @param apartment
     * @return
     */
    public Apartment saveApartment(Apartment apartment)
    {
        PreparedStatement stmt = null;
        Apartment updatedApartment = null;
        try {
            if (apartment.getId() == null) {
                stmt = conn.prepareStatement("INSERT INTO estate(city, postalcode, street, streetnumber, squarearea, agentID) VALUES (?,?,?,?,?,?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, apartment.getCity());
                stmt.setInt(2, apartment.getPostalCode());
                stmt.setString(3, apartment.getStreet());
                stmt.setInt(4, apartment.getStreetNumber());
                stmt.setInt(5, apartment.getSquareArea());
                stmt.setInt(6, apartment.getAgent().getId());
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int estateId = rs.getInt(1);
                    stmt = conn.prepareStatement("INSERT INTO apartment(ID, floor, rent, rooms, balcony, builtinkitchen) VALUES (?,?,?,?,?,?)");
                    stmt.setInt(1, estateId);
                    stmt.setInt(2, apartment.getFloor());
                    stmt.setDouble(3, apartment.getRentPrice());
                    stmt.setInt(4, apartment.getNumRooms());
                    stmt.setBoolean(5, apartment.hasBalcony());
                    stmt.setBoolean(6, apartment.hasBuiltInKitchen());

                    if (stmt.executeUpdate() > 0) {
                        updatedApartment = getApartment(estateId);
                    }
                }
                rs.close();
            } else {
                stmt = conn.prepareStatement("UPDATE estate SET city = ?, postalcode = ?, street = ?, streetnumber = ?, squarearea = ?, agentID = ? WHERE ID = ?");
                stmt.setString(1, apartment.getCity());
                stmt.setInt(2, apartment.getPostalCode());
                stmt.setString(3, apartment.getStreet());
                stmt.setInt(4, apartment.getStreetNumber());
                stmt.setInt(5, apartment.getSquareArea());
                stmt.setInt(6, apartment.getAgent().getId());
                stmt.setInt(7, apartment.getId());

                if (stmt.executeUpdate() > 0) {
                    stmt = conn.prepareStatement("UPDATE apartment SET floor = ?, rent = ?, rooms = ?, balcony = ?, builtinkitchen = ? WHERE ID = ?");
                    stmt.setInt(1, apartment.getFloor());
                    stmt.setDouble(2, apartment.getRentPrice());
                    stmt.setInt(3, apartment.getNumRooms());
                    stmt.setBoolean(4, apartment.hasBalcony());
                    stmt.setBoolean(5, apartment.hasBuiltInKitchen());
                    stmt.setInt(6, apartment.getId());


                    if (stmt.executeUpdate() > 0) {
                        updatedApartment = getApartment(apartment.getId());
                    }
                }
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: rollback transaction
        }

        return updatedApartment;
    }

    /**
     * @param id Estate ID
     * @return
     */
    public Apartment getApartment(int id)
    {
        Apartment apartment = null;
        try {
            String selectSQL = "SELECT * FROM apartment A, estate E WHERE A.ID = E.ID AND A.ID = ?";
            PreparedStatement stmt = conn.prepareStatement(selectSQL);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int agentID = rs.getInt("agentID");
                EstateAgent agent = estateAgentService.getEstateAgent(agentID);
                apartment = new Apartment(id, rs.getString("city"), rs.getInt("postalcode"), rs.getString("street"), rs.getInt("streetnumber"),
                        rs.getInt("squarearea"), agent, rs.getInt("floor"), rs.getDouble("rent"), rs.getInt("rooms"), rs.getBoolean("balcony"),
                        rs.getBoolean("builtinkitchen"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apartment;
    }

    public Vector<Apartment> getAllApartmentsForAgent(int agentID)
    {
        Vector<Apartment> apartments = new Vector<>();
        try {
            String selectSQL = "SELECT * FROM apartment A, estate E WHERE A.ID = E.ID AND E.AGENTID = " +agentID;
            PreparedStatement stmt = conn.prepareStatement(selectSQL);
            //stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                EstateAgent agent = estateAgentService.getEstateAgent(agentID);
                apartments.add(new Apartment(rs.getInt("Id"), rs.getString("city"), rs.getInt("postalcode"), rs.getString("street"), rs.getInt("streetnumber"),
                        rs.getInt("squarearea"), agent, rs.getInt("floor"), rs.getDouble("rent"), rs.getInt("rooms"), rs.getBoolean("balcony"),
                        rs.getBoolean("builtinkitchen")));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apartments;
    }

    /**
     * Delete an estate
     *
     * @param estate
     */
    public boolean deleteEstate(Estate estate)
    {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM house WHERE ID = ?");
            stmt.setInt(1, estate.getId());
            stmt.executeUpdate();

            stmt = conn.prepareStatement("DELETE FROM apartment WHERE ID = ?");
            stmt.setInt(1, estate.getId());
            stmt.executeUpdate();

            stmt = conn.prepareStatement("DELETE FROM estate WHERE ID = ?");
            stmt.setInt(1, estate.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}
