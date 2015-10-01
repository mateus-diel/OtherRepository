package br.com.acme.model;

import java.io.Serializable;

public class Journal implements Serializable{

    private String name;
    private byte impactFactor;
    private Publisher publisher;

    public Journal() {
    }   

    public Journal(String name, byte impactFactor, Publisher publisher) throws IllegalArgumentException {
        if (impactFactor < 0) {
            throw new IllegalArgumentException ("Impact factor cannot be negative");
        }
        this.name = name;
        this.impactFactor = impactFactor;
        this.publisher = publisher;
    }  
    

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getImpactFactor() {
        return impactFactor;
    }

    public void setImpactFactor(byte impactFactor) throws IllegalArgumentException {
        if (impactFactor < 0) {
            throw new IllegalArgumentException ("Impact factor cannot be negative");
        }
        this.impactFactor = impactFactor;
    }
}
