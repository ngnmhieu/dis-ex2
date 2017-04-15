package de.dissose17.data;

/**
 * @author ngnmhieu
 * @since 15.04.17
 */
public class Estate
{
    private Integer id;

    private String city;

    private int postalCode;

    private String street;

    private int streetNumber;

    private int squareArea;

    private EstateAgent agent;

    public Estate(Integer id, String city, int postalCode, String street, int streetNumber, int squareArea, EstateAgent agent)
    {
        this(city, postalCode, street, streetNumber, squareArea, agent);
        this.id = id;
    }

    public Estate(String city, int postalCode, String street, int streetNumber, int squareArea, EstateAgent agent)
    {
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.streetNumber = streetNumber;
        this.squareArea = squareArea;
        this.agent = agent;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public int getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(int postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public int getStreetNumber()
    {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber)
    {
        this.streetNumber = streetNumber;
    }

    public int getSquareArea()
    {
        return squareArea;
    }

    public void setSquareArea(int squareArea)
    {
        this.squareArea = squareArea;
    }

    public EstateAgent getAgent()
    {
        return agent;
    }

    public void setAgent(EstateAgent agent)
    {
        this.agent = agent;
    }
}
