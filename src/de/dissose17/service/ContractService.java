package de.dissose17.service;

import de.dissose17.data.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author ngnmhieu
 * @since 15.04.17
 */
public class ContractService
{
    /**
     * Add a person to system
     *
     * @param firstName
     * @param lastName
     * @param address
     * @return
     */
    public Person addPerson(String firstName, String lastName, String address)
    {
        return null;
    }

    /**
     * Create a new purchase contract
     * @param date
     * @param place
     * @param numInstallments
     * @param interestRate
     * @param house
     * @param owner
     * @return a new contract
     */
    public PurchaseContract createPurchaseContract(LocalDate date, String place, int numInstallments, double interestRate, House house, Person owner)
    {
        return null;
    }

    /**
     * Create a new tenancy contract
     * @param date
     * @param place
     * @param startDate
     * @param duration
     * @param additionalCosts
     * @param apartment
     * @param renter
     * @return a new contract
     */
    public TenancyContract createTenancyContract(LocalDate date, String place, LocalDate startDate, int duration, double additionalCosts, Apartment apartment, Person renter)
    {
        return null;
    }

    public List<PurchaseContract> getPurchaseContracts()
    {
        return null;
    }

    /**
     * @return all tenancy contracts
     */
    public List<TenancyContract> getTenancyContracts()
    {
        return null;
    }
}
