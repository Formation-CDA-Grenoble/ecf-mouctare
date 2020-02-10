package com.example.api.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "livres")
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "contenu", nullable = false)
    private String contenu;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "jaime", nullable = false)
    private int jaime;
    

    public int getJaime() {
    	return this.jaime;
    }
    public void setJaime(int jaime) {
    	this.jaime = jaime;
    }

    public String getContenu() {
    	return this.contenu;
    }
    public void setContenu(String contenu) {
    	this.contenu = contenu;
    }
    
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date2) {
        this.date = date2;
    }

    public String getTitle() {
    	return this.title;
    }
    public void setTitle(String title) {
    	this.title = title;
    }

    public long getId() {
    	return this.id;
    }
    public void setId(long id) {
    	this.id = id;
    }
	
	public String getAuthor() {
        return this.author;
    }
        
	public void setAuthor(String author) {
     this.author = author;
    }
    

	}
	
	

