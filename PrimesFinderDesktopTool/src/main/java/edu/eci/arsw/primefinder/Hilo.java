/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.primefinder;

import java.math.BigInteger;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2125509
 */
public class Hilo extends Thread {

    private BigInteger a;
    private BigInteger b;
    private Collection rta;
    private PrimesResultSet prs;
    private boolean suspendidos;

    public Hilo(PrimesResultSet prs, BigInteger a, BigInteger b) {
        this.prs = prs;
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {

        PrimeFinder.findPrimes(a, b, prs, this);
    }

    public boolean isSuspendidos() {
        return suspendidos;
    }

    public void setSuspendidos(boolean suspendidos) {
        this.suspendidos = suspendidos;
    }

    public Collection getRta() {
        return rta;
    }

    public void setRta(Collection rta) {
        this.rta = rta;
    }

    public BigInteger getA() {
        return a;
    }

    public void setA(BigInteger a) {
        this.a = a;
    }

    public BigInteger getB() {
        return b;
    }

    public void setB(BigInteger b) {
        this.b = b;
    }

    public PrimesResultSet getPrs() {
        return prs;
    }

    public void setPrs(PrimesResultSet prs) {
        this.prs = prs;
    }

}
