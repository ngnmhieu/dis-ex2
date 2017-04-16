package de.dissose17.service;

import de.dissose17.DB2ConnectionManager;
import de.dissose17.data.EstateAgent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ngnmhieu
 * @since 15.04.17
 */
public class EstateAgentService
{
    private Connection conn = DB2ConnectionManager.getInstance().getConnection();

    // currently logged in estate agent
    private EstateAgent currentAgent;

    /**
     * Create account for an estate agent
     *
     * @return A new estate agent, null if it cannot be created
     */
    public EstateAgent createAccount(String name, String address, String login, String password)
    {
        return save(new EstateAgent(name, address, login, password));
    }

    /**
     * Load an estate agent from database
     *
     * @param id ID of the agent
     * @return EstateAgent instance
     */
    public EstateAgent getEstateAgent(int id)
    {
        try {
            // Erzeuge Anfrage
            String selectSQL = "SELECT * FROM estateagent WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            pstmt.setInt(1, id);

            // Führe Anfrage aus
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                EstateAgent ts = new EstateAgent(id, rs.getString("name"), rs.getString("address"), rs.getString("login"), rs.getString("password"));
                rs.close();
                pstmt.close();
                return ts;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Save an estate agent.
     * A new estate agent account will be created if no id is assigned.
     *
     * @param agent
     * @return new or updated estate agent
     */
    public EstateAgent save(EstateAgent agent)
    {
        PreparedStatement stm = null;
        boolean isInsert = agent.getId() == null;
        EstateAgent updatedAgent = null;
        try {
            if (isInsert) {
                stm = conn.prepareStatement("INSERT INTO estateAgent(name, address, login, password) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            } else {
                stm = conn.prepareStatement("UPDATE estateAgent SET name = ?, address = ?, login = ?, password = ? WHERE ID = ?");
                stm.setInt(5, agent.getId());
            }
            stm.setString(1, agent.getName());
            stm.setString(2, agent.getAddress());
            stm.setString(3, agent.getLogin());
            stm.setString(4, agent.getPassword());
            stm.executeUpdate();

            if (isInsert) {
                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    updatedAgent = getEstateAgent(rs.getInt(1));
                }
                rs.close();
            } else {
                updatedAgent = getEstateAgent(agent.getId());
            }
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updatedAgent;
    }

    /**
     * Delete an estate agent
     *
     * @return true if agent successfully deleted
     */
    public boolean delete(EstateAgent agent)
    {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM estateAgent WHERE ID = ?");
            stmt.setInt(1, agent.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Authenticate an estate agent
     *
     * @param login
     * @param password
     * @return
     */
    public boolean authenticate(String login, String password)
    {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM estateAgent WHERE login = ? AND password = ?");
            stmt.setString(1, login);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                currentAgent = new EstateAgent(rs.getInt("ID"), rs.getString("name"), rs.getString("address"), rs.getString("login"), rs.getString("password"));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * @return true if an estate agent is logged in
     */
    public boolean isLoggedIn()
    {
        return currentAgent != null;
    }

    /**
     * @return currently logged in estate agent
     */
    public EstateAgent getCurrentAgent()
    {
        return currentAgent;
    }
}
