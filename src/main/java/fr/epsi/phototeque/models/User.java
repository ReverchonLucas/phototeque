package fr.epsi.phototeque.models;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 25)
    private String nom;

    @Column(nullable = false, length = 25)
    private String prenom;

    @Column(nullable = false, length = 30, unique = true)
    private String pseudo;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean admin;

    @Column(nullable = false)
    private boolean block;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    public String getPseudo() {return pseudo;}
    public void setPseudo(String pseudo) {this.pseudo = pseudo;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public boolean isAdmin() {return admin;}
    public void setAdmin(boolean admin) {this.admin = admin;}
    public boolean isBlock() {return block;}
    public void setBlock(boolean block) {this.block = block;}
}