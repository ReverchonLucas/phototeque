package fr.epsi.phototeque.models;

import jakarta.persistence.*;


@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 255)
    private String nom;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
}

