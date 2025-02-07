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

    @Column(nullable = false, columnDefinition = "TEXT")
    private String nom;

    @ManyToOne
    @JoinColumn(name = "categorie", nullable = false)
    private Categorie categorie;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private int taille;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateCreation;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateModif;

    @Column(nullable = false)
    private int nbTelechargement;

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
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public Categorie getCategorie() {return categorie;}
    public void setCategorie(Categorie categorie) {this.categorie = categorie;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public int getTaille() {return taille;}
    public void setTaille(int taille) {this.taille = taille;}
    public Date getDateCreation() {return dateCreation;}
    public void setDateCreation(Date dateCreation) {this.dateCreation = dateCreation;}
    public Date getDateModif() {return dateModif;}
    public void setDateModif(Date dateModif) {this.dateModif = dateModif;}
    public int getNbTelechargement() {return nbTelechargement;}
    public void setNbTelechargement(int nbTelechargement) {this.nbTelechargement = nbTelechargement;}
    public String getListeElements() {return listeElements;}
    public void setListeElements(String listeElements) {this.listeElements = listeElements;}
    public boolean isIndividu() {return individu;}
    public void setIndividu(boolean individu) {this.individu = individu;}
    public String getUrl() {return url;}
    public void setUrl(String url) {this.url = url;}
}