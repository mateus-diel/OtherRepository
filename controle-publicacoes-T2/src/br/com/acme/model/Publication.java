package br.com.acme.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Publication implements Serializable{

    private String title;
    private short year;
    private byte volume;
    private List<Author> authors;

    public Publication() {
        authors = new ArrayList<>();
    }   

    public Publication(String title, short year, byte volume) {
        this();
        this.title = title;
        this.year = year;
        this.volume = volume;
    }        
    
    public void addAuthor(Author author) {
        this.authors.add(author);
    }
    
    public void removeAuthor(String name) {
        for (Author a : authors) {
            if (a.getName().equalsIgnoreCase(name)) {
                authors.remove(a);
                return;
            }
        }
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public byte getVolume() {
        return volume;
    }

    public void setVolume(byte volume) {
        this.volume = volume;
    }    
    
    public List<Author> getAuthors() {
        return authors;
    }
    
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
            
}
