package de.dissose17.data;

/**
 * @author ngnmhieu
 * @since 15.04.17
 */
public class Apartment extends Estate
{
    // on which floor
    private int floor;

    // rent price
    private double rentPrice;

    // number of rooms
    private int numRooms;

    // does it has a balcony
    private boolean hasBalcony;

    // does it has a built-in kitchen
    private boolean hasBuiltInKitchen;

    public Apartment(Integer id, String city, int postalCode, String street, int streetNumber, int squareArea, EstateAgent agent, int floor, double rentPrice, int numRooms, boolean hasBalcony, boolean hasBuiltInKitchen)
    {
        super(id, city, postalCode, street, streetNumber, squareArea, agent);
        this.floor = floor;
        this.rentPrice = rentPrice;
        this.numRooms = numRooms;
        this.hasBalcony = hasBalcony;
        this.hasBuiltInKitchen = hasBuiltInKitchen;
    }

    public Apartment(String city, int postalCode, String street, int streetNumber, int squareArea, EstateAgent agent, int floor, double rentPrice, int numRooms, boolean hasBalcony, boolean hasBuiltInKitchen)
    {
        super(city, postalCode, street, streetNumber, squareArea, agent);
        this.floor = floor;
        this.rentPrice = rentPrice;
        this.numRooms = numRooms;
        this.hasBalcony = hasBalcony;
        this.hasBuiltInKitchen = hasBuiltInKitchen;
    }

    public int getFloor()
    {
        return floor;
    }

    public void setFloor(int floor)
    {
        this.floor = floor;
    }

    public double getRentPrice()
    {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice)
    {
        this.rentPrice = rentPrice;
    }

    public int getNumRooms()
    {
        return numRooms;
    }

    public void setNumRooms(int numRooms)
    {
        this.numRooms = numRooms;
    }

    public boolean hasBalcony()
    {
        return hasBalcony;
    }

    public void setHasBalcony(boolean hasBalcony)
    {
        this.hasBalcony = hasBalcony;
    }

    public boolean hasBuiltInKitchen()
    {
        return hasBuiltInKitchen;
    }

    public void setHasBuiltInKitchen(boolean hasBuiltInKitchen)
    {
        this.hasBuiltInKitchen = hasBuiltInKitchen;
    }
}
