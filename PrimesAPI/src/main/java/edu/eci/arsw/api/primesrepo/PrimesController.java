package edu.eci.arsw.api.primesrepo;

import com.google.gson.Gson;
import edu.eci.arsw.api.primesrepo.model.AlreadyPrimeException;
import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.model.PrimeNotFoundException;
import edu.eci.arsw.api.primesrepo.service.PrimeService;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Santiago Carrillo 2/22/18.
 */
@RestController
public class PrimesController {

    @Autowired
    PrimeService primeService;

    @RequestMapping(value = "/primes", method = GET)
    public List<FoundPrime> getPrimes() {
        return primeService.getFoundPrimes();
    }

    @RequestMapping(path = "/primes/{primenumber}", method = GET)
    public FoundPrime getPrime(@PathVariable (name="primenumber") String prime) {
        try {           
            return primeService.getPrime(prime);
        } catch (PrimeNotFoundException ex) {
            Logger.getLogger("404 Primo no encontrado",PrimesController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     @RequestMapping(path = "/primes", method = POST)
    public void addFoundPrime(@RequestBody FoundPrime prime) {
        try {       
            
            primeService.addFoundPrime(prime);
        } catch (AlreadyPrimeException ex) {
            Logger.getLogger("402 forbbiden primo ya registrado",PrimesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //TODO implement additional methods provided by PrimeService
}
