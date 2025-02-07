package fr.epsi.phototeque.repositories;

import fr.epsi.phototeque.models.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Integer>{
    Categorie findByNom(String nom);
}
