package de.dissose17.data;

import java.sql.Date;

/**
 * @author ngnmhieu
 * @since 15.04.17
 */
abstract public class Contract
{
    // contract number
    private Integer contractNo;

    // contract date
    private Date date;

    // settlement place
    private String place;

    public Contract(Integer contractNo, Date date, String place)
    {
        this(date, place);
        this.contractNo = contractNo;
    }

    public Contract(Date date, String place)
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

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
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
