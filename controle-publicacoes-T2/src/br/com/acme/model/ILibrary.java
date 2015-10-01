package br.com.acme.model;

import java.util.ArrayList;

public interface ILibrary {
    
    public void addPublication(Publication p);
    public boolean removePublication(long id);
    public ArrayList<Publication> getAllPublications();
    public short countPublications();
    public Publication findPublication(String title);
    public boolean hasPublication(String title);      
    
}
