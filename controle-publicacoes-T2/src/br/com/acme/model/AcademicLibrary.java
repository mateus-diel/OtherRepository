package br.com.acme.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcademicLibrary implements ILibrary, Serializable  {

    private String name;
    private String description;
    private String creationDate;
    private Map<Long, Article> articles;
    private Map<Long, Book> books;
    private Map<String, User> users;
    
    public AcademicLibrary(String name, String description, String creationDate) {
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.articles = new HashMap<>();
        this.books = new HashMap<>();
        this.users = new HashMap<>();
    }

    public Map<String, User> getUsers() {
        return users;
    }
    
   
    public User findUser(String login) {
        return users.get(login);
    }

    @Override
    public void addPublication(Publication p) {
        if (p instanceof Article) {
            Article a = (Article) p;
            articles.put(a.getIssn(), a);
        } else if (p instanceof Book) {
            Book b = (Book) p;
            books.put(b.getIsbn(), b);
        }
    }

    @Override
    public boolean removePublication(long id) {

        if (articles.containsKey(id)) {
            articles.remove(id);
            return true;
        }

        if (books.containsKey(id)) {
            books.remove(id);
            return true;
        }

        return false;
    }

    @Override
    public ArrayList<Publication> getAllPublications() {
        ArrayList<Publication> res = new ArrayList<>();

        res.addAll(this.articles.values());
        res.addAll(this.books.values());

        return res;
    }


    @Override
    public short countPublications() {
        return (short) (getAllPublications().size());
    }

    @Override
    public Publication findPublication(String title) {
        for (Publication p : getAllPublications()) {
            if (p.getTitle().equalsIgnoreCase(title)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean hasPublication(String title) {
        if (findPublication(title) != null) {
            return true;
        }
        return false;
    }

    public short countArticles() {
        return (short) articles.size();
    }

    public short countBooks() {
        return (short) books.size();
    }

    public Article findArticle(long issn) {
        return articles.get(issn);
    }

    public Article findArticle(String title) {
        for (Article a : articles.values()) {
            if (a.getTitle().equalsIgnoreCase(title)) {
                return a;
            }
        }
        return null;
    }

    public Book findBook(long isbn) {
        return books.get(isbn);
    }

    public Book findBook(String title) {
        for (Book b : books.values()) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }    
    
}
