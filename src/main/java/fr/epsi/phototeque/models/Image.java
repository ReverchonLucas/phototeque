package fr.epsi.phototeque.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Lob
    @Column(nullable = false)
    private byte[] image;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String nom;

    @Column(nullable = false, length = 50)
    private String categorie;

    @Column(nullable = false)
    private int taille;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateCreation;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateModif;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String listeElements;

    @Column(nullable = false)
    private boolean individu;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String url;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
    public byte[] getImage() {return image;}
    public void setImage(byte[] image) {this.image = image;}
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public String getCategorie() {return categorie;}
    public void setCategorie(String categorie) {this.categorie = categorie;}
    public int getTaille() {return taille;}

    public void setTaille(int taille) {this.taille = taille;}
    public Date getDateCreation() {return dateCreation;}
    public void setDateCreation(Date dateCreation) {this.dateCreation = dateCreation;}
    public Date getDateModif() {return dateModif;}
    public void setDateModif(Date dateModif) {this.dateModif = dateModif;}
    public String getListeElements() {return listeElements;}
    public void setListeElements(String listeElements) {this.listeElements = listeElements;}
    public boolean isIndividu() {return individu;}
    public void setIndividu(boolean individu) {this.individu = individu;}
    public String getUrl() {return url;}
    public void setUrl(String url) {this.url = url;}
}