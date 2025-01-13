package fr.epsi.phototeque.repositories;

import fr.epsi.phototeque.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}