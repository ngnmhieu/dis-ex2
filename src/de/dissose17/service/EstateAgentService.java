package de.dissose17.service;

import de.dissose17.data.EstateAgent;

/**
 * @author ngnmhieu
 * @since 15.04.17
 */
public class EstateAgentService
{
    /**
     * Create account for an estate agent
     * @return a new estate agent
     */
    public EstateAgent createAccount(String name, String address, String login, String password) {
        return null;
    }

    /**
     * Save an estate agent.
     * A new estate agent account will be created if no id is assigned.
     *
     * @param agent
     * @return new or updated estate agent
     */
    public EstateAgent save(EstateAgent agent) {
        return null;
    }

    /**
     * Delete an estate agent
     */
    public void delete(EstateAgent agent) {
    }

    /**
     * Authenticate an estate agent
     *
     * @param login
     * @param password
     * @return
     */
    public boolean authenticate(String login, String password) {
        return false;
    }

    /**
     * @return true if an estate agent is logged in
     */
    public boolean isLoggedIn() {
        return false;
    }

    /**
     * @return currently logged in estate agent
     */
    public EstateAgent getCurrentEstateAgent() {
        return null;
    }
}
