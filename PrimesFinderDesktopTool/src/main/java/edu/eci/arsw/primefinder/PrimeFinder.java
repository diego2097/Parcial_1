package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrimeFinder{
        
	
	
        
	public static void findPrimes(BigInteger _a, BigInteger _b, PrimesResultSet prs,Hilo hilo){
            
                BigInteger a=_a;
                BigInteger b=_b;

                MathUtilities mt=new MathUtilities();
                
                int itCount=0;
            
                BigInteger i=a;
                while (i.compareTo(b)<=0){
                     if (hilo.isSuspendidos()){
                        try {
                            synchronized(hilo.getPrs()){
                                hilo.getPrs().wait();
                            }
                        } catch (InterruptedException ex) {
                            Logger.getLogger(PrimeFinder.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    itCount++;
                    if (mt.isPrime(i)){
                        prs.addPrime(i);
                    }
                   
                    i=i.add(BigInteger.ONE);
                }
                
	}
	
	
	
	
	
}
