package de.dissose17.data;

import java.sql.Date;

/**
 * @author ngnmhieu
 * @since 15.04.17
 */
public class TenancyContract extends Contract
{
    // contract start date
    private Date startDate;

    // in months
    private int duration;

    // additional costs
    private double additionalCosts;

    // the person who rents the house
    private Person renter;

    // which apartment is rented
    private Apartment apartment;

    public TenancyContract(Integer contractNo, Date date, String place, Date startDate, int duration, double additionalCosts, Person renter, Apartment apartment)
    {
        super(contractNo, date, place);
        this.startDate = startDate;
        this.duration = duration;
        this.additionalCosts = additionalCosts;
        this.renter = renter;
        this.apartment = apartment;
    }

    public TenancyContract(Date date, String place, Date startDate, int duration, double additionalCosts, Person renter, Apartment apartment)
    {
        super(date, place);
        this.startDate = startDate;
        this.duration = duration;
        this.additionalCosts = additionalCosts;
        this.renter = renter;
        this.apartment = apartment;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public double getAdditionalCosts()
    {
        return additionalCosts;
    }

    public void setAdditionalCosts(double additionalCosts)
    {
        this.additionalCosts = additionalCosts;
    }

    public Person getRenter()
    {
        return renter;
    }

    public void setRenter(Person renter)
    {
        this.renter = renter;
    }

    public Apartment getApartment()
    {
        return apartment;
    }

    public void setApartment(Apartment apartment)
    {
        this.apartment = apartment;
    }
}
