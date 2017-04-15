package de.dissose17.data;

import de.dissose17.DB2ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * EstateAgent-Bean
 * <p>
 * Beispiel-Tabelle:
 * CREATE TABLE estateagent(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1, NO CACHE) PRIMARY KEY,
 * name varchar(255),
 * address varchar(255),
 * login varchar(40) UNIQUE,
 * password varchar(40));
 */
public class EstateAgent
{
    private Integer id = null;

    private String name;

    private String address;

    private String login;

    private String password;

    public EstateAgent(Integer id, String name, String address, String login, String password)
    {
        this(name, address, login, password);
        this.id = id;
    }

    public EstateAgent(String name, String address, String login, String password)
    {
        this.name = name;
        this.address = address;
        this.login = login;
        this.password = password;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }


    /**
     * Speichert den EstateAgent in der Datenbank. Ist noch keine ID vergeben
     * worden, wird die generierte Id von DB2 geholt und dem Model übergeben.
     */
    public void save()
    {
        // Hole Verbindung
        Connection con = DB2ConnectionManager.getInstance().getConnection();

        try {
            // FC<ge neues Element hinzu, wenn das Objekt noch keine ID hat.
            if (getId() == -1) {
                // Achtung, hier wird noch ein Parameter mitgegeben,
                // damit spC$ter generierte IDs zurC<ckgeliefert werden!
                String insertSQL = "INSERT INTO estateagent(name, address, login, password) VALUES (?, ?, ?, ?)";

                PreparedStatement pstmt = con.prepareStatement(insertSQL,
                        Statement.RETURN_GENERATED_KEYS);

                // Setze Anfrageparameter und fC<hre Anfrage aus
                pstmt.setString(1, getName());
                pstmt.setString(2, getAddress());
                pstmt.setString(3, getLogin());
                pstmt.setString(4, getPassword());
                pstmt.executeUpdate();

                // Hole die Id des engefC<gten Datensatzes
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    setId(rs.getInt(1));
                }

                rs.close();
                pstmt.close();
            } else {
                // Falls schon eine ID vorhanden ist, mache ein Update...
                String updateSQL = "UPDATE estateagent SET name = ?, address = ?, login = ?, password = ? WHERE id = ?";
                PreparedStatement pstmt = con.prepareStatement(updateSQL);

                // Setze Anfrage Parameter
                pstmt.setString(1, getName());
                pstmt.setString(2, getAddress());
                pstmt.setString(3, getLogin());
                pstmt.setString(4, getPassword());
                pstmt.setInt(5, getId());
                pstmt.executeUpdate();

                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
