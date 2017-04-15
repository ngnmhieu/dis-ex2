package de.dissose17.data;

/**
 * @author ngnmhieu
 * @since 15.04.17
 */
public class House extends Estate
{
    private int numFloors;

    private double price;

    private boolean hasGarden;

    public House(Integer id, String city, int postalCode, String street, int streetNumber, int squareArea, EstateAgent agent, int numFloors, double price, boolean hasGarden)
    {
        super(id, city, postalCode, street, streetNumber, squareArea, agent);
        this.numFloors = numFloors;
        this.price = price;
        this.hasGarden = hasGarden;
    }

    public House(String city, int postalCode, String street, int streetNumber, int squareArea, EstateAgent agent, int numFloors, double price, boolean hasGarden)
    {
        super(city, postalCode, street, streetNumber, squareArea, agent);
        this.numFloors = numFloors;
        this.price = price;
        this.hasGarden = hasGarden;
    }

    public int getNumFloors()
    {
        return numFloors;
    }

    public void setNumFloors(int numFloors)
    {
        this.numFloors = numFloors;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public boolean hasGarden()
    {
        return hasGarden;
    }

    public void setHasGarden(boolean hasGarden)
    {
        this.hasGarden = hasGarden;
    }
}
