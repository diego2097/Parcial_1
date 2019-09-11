package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.model.AlreadyPrimeException;
import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.model.PrimeNotFoundException;

import java.util.List;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
public interface PrimeService
{

    void addFoundPrime( FoundPrime foundPrime ) throws AlreadyPrimeException;

    List<FoundPrime> getFoundPrimes();

    FoundPrime getPrime( String prime ) throws PrimeNotFoundException;

}
