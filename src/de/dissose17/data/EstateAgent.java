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
    private Integer id;

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
}
