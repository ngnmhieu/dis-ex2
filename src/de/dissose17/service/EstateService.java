package de.dissose17.service;

import de.dissose17.data.Apartment;
import de.dissose17.data.Estate;
import de.dissose17.data.EstateAgent;
import de.dissose17.data.House;

/**
 * @author ngnmhieu
 * @since 15.04.17
 */
public class EstateService
{
    /**
     * @param city
     * @param postalCode
     * @param street
     * @param streetNumber
     * @param squareArea
     * @param agent
     * @param numFloors
     * @param price
     * @param hasGarden
     * @return
     */
    public House createHouse(String city, int postalCode, String street, int streetNumber, int squareArea, EstateAgent agent, int numFloors, double price, boolean hasGarden) {
        return null;
    }

    /**
     * @param city
     * @param postalCode
     * @param street
     * @param streetNumber
     * @param squareArea
     * @param agent
     * @param floor
     * @param rentPrice
     * @param numRooms
     * @param hasBalcony
     * @param hasBuiltInKitchen
     * @return
     */
    public Apartment createApartment(String city, int postalCode, String street, int streetNumber, int squareArea, EstateAgent agent, int floor, double rentPrice, int numRooms, boolean hasBalcony, boolean hasBuiltInKitchen) {
        return null;
    }

    /**
     *
     * @param house
     * @return
     */
    public House saveHouse(House house) {
        return null;
    }

    /**
     * @param apartment
     * @return
     */
    public Apartment saveApartment(Apartment apartment) {
        return null;
    }

    /**
     * Delete an estate
     * @param estate
     */
    public void deleteEstate(Estate estate) {
    }
}
