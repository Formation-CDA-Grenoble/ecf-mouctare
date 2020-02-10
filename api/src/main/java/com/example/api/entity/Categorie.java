package com.example.api.entity;


import javax.persistence.*;


@Entity
@Table(name = "categories")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id = 0;

   
@Column(name = "name", nullable = false)
    private String name;

   

    public String getName() {
        return this.name;
    }

    
    }
    

	}
	
	

