package de.dissose17.data;

import java.time.LocalDate;

/**
 * @author ngnmhieu
 * @since 15.04.17
 */
public class Contract
{
    // contract number
    private Integer contractNo;

    // contract date
    private LocalDate date;

    // settlement place
    private String place;

    public Contract(Integer contractNo, LocalDate date, String place)
    {
        this(date, place);
        this.contractNo = contractNo;
    }

    public Contract(LocalDate date, String place)
    {
        this.date = date;
        this.place = place;
    }

    public Integer getContractNo()
    {
        return contractNo;
    }

    public void setContractNo(Integer contractNo)
    {
        this.contractNo = contractNo;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public String getPlace()
    {
        return place;
    }

    public void setPlace(String place)
    {
        this.place = place;
    }
}
