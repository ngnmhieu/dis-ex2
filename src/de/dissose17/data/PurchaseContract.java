package de.dissose17.data;

import java.sql.Date;

/**
 * @author ngnmhieu
 * @since 15.04.17
 */
public class PurchaseContract extends Contract
{
    // number of installments
    private int numInstallments;

    // interest rate for the estate agent
    private double interestRate;

    // the house of interest
    private House house;

    // the person who buys (or sells) the house
    private Person owner;

    public PurchaseContract(Integer contractNo, Date date, String place, int numInstallments, double interestRate, House house, Person owner)
    {
        super(contractNo, date, place);
        this.numInstallments = numInstallments;
        this.interestRate = interestRate;
        this.house = house;
        this.owner = owner;
    }

    public PurchaseContract(Date date, String place, int numInstallments, double interestRate, House house, Person owner)
    {
        super(date, place);
        this.numInstallments = numInstallments;
        this.interestRate = interestRate;
        this.house = house;
        this.owner = owner;
    }

    public int getNumInstallments()
    {
        return numInstallments;
    }

    public void setNumInstallments(int numInstallments)
    {
        this.numInstallments = numInstallments;
    }

    public double getInterestRate()
    {
        return interestRate;
    }

    public void setInterestRate(double interestRate)
    {
        this.interestRate = interestRate;
    }

    public House getHouse()
    {
        return house;
    }

    public void setHouse(House house)
    {
        this.house = house;
    }

    public Person getOwner()
    {
        return owner;
    }

    public void setOwner(Person owner)
    {
        this.owner = owner;
    }
}
