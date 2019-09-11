package edu.eci.arsw.primefinder;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class PrimesFinderTool {

    public static void main(String[] args) {

        int maxPrim = 1000;

        PrimesResultSet prs = new PrimesResultSet("john");

        int rango = 100;

        Hilo h1 = new Hilo(prs, new BigInteger("1"), new BigInteger(Integer.toString(rango / 4)));
        Hilo h2 = new Hilo(prs, new BigInteger(Integer.toString((rango / 4) + 1)), new BigInteger(Integer.toString((rango / 4) * 2)));
        Hilo h3 = new Hilo(prs, new BigInteger(Integer.toString((rango / 4) * 2 + 1)), new BigInteger(Integer.toString((rango / 4) * 3)));
        Hilo h4 = new Hilo(prs, new BigInteger(Integer.toString((rango / 4) * 3 + 1)), new BigInteger(Integer.toString(rango)));

        h1.start();
        h2.start();
        h3.start();
        h4.start();
        //primer punto
        /*try {
            h1.join();
            h2.join();
            h3.join();
            h4.join();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(PrimesFinderTool.class.getName()).log(Level.SEVERE, null, ex);
        }
         */

        //System.out.println("Prime numbers found:");
        //System.out.println(prs.getPrimes());
        while (h1.isAlive() || h2.isAlive() || h3.isAlive() || h4.isAlive()) {
            try {
                //check every 10ms if the idle status (10 seconds without mouse
                //activity) was reached. 
                Thread.sleep(10);
                if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement() > 10000) {
                    h1.setSuspendidos(false);h2.setSuspendidos(false);
                    h3.setSuspendidos(false); h4.setSuspendidos(false);
                    System.out.println("Idle CPU ");
                    synchronized (prs) {
                        prs.notifyAll();
                    }
                } else {
                    h1.setSuspendidos(true); h2.setSuspendidos(true);
                    h3.setSuspendidos(true); h4.setSuspendidos(true);

                    System.out.println("User working again!");

                }
            } catch (InterruptedException ex) {
                Logger.getLogger(PrimesFinderTool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Prime numbers found:");
        System.out.println(prs.getPrimes());
    }
}
