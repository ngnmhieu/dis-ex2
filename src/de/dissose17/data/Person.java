package de.dissose17.data;

/**
 * @author ngnmhieu
 * @since 15.04.17
 */
public class Person
{
    private Integer id;

    private String firstName;

    private String lastName;

    private String address;

    public Person(Integer id, String firstName, String lastName, String address)
    {
        this(firstName, lastName, address);
        this.id = id;
    }

    public Person(String firstName, String lastName, String address)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
}
