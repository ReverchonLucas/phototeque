package fr.epsi.phototeque.models;

public class ChangeImageRequest {
    private String token;
    private int idImage;
    private String nom;
    private String categorie;
    private String description;

    public String getToken() {return token;}
    public int getIdImage() {return idImage;}
    public String getNom() {return nom;}
    public String getCategorie() {return categorie;}
    public String getDescription() {return description;}
}
