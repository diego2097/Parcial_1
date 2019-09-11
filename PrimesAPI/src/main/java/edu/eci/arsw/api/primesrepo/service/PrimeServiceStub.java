package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.model.AlreadyPrimeException;
import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.model.PrimeNotFoundException;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Santiago Carrillo 2/22/18.
 */
@Service
public class PrimeServiceStub implements PrimeService {

    private List<FoundPrime> primos = new ArrayList<FoundPrime>();

    public PrimeServiceStub() {
        FoundPrime primo = new FoundPrime("rafael", "3");
        primos.add(primo);
        FoundPrime primo2 = new FoundPrime("miguelangel", "5");
        primos.add(primo2);
        FoundPrime primo3 = new FoundPrime("leonardo", "7");
        primos.add(primo3);
    }

    @Override
    public void addFoundPrime(FoundPrime foundPrime) throws AlreadyPrimeException {
        FoundPrime primo = null;
        try {
            primo = getPrime(foundPrime.getPrime());
            if (!(primo.getUser().equals(foundPrime.getUser()))) {
                throw new AlreadyPrimeException("Este primo ya fue encontrado");
            }
        } catch (PrimeNotFoundException ex) {
            synchronized (primos) {
                primos.add(foundPrime);
            }
        }
    }

    @Override
    public List<FoundPrime> getFoundPrimes() {
        return primos;
    }

    @Override
    public FoundPrime getPrime(String prime) throws PrimeNotFoundException {
        for (int i = 0; i < primos.size(); i++) {
            if (primos.get(i).getPrime().equals(prime)) {
                return primos.get(i);
            }
        }
        throw new PrimeNotFoundException("Primo no encontrado");

    }
}
