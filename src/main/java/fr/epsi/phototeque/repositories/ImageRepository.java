package fr.epsi.phototeque.repositories;

import fr.epsi.phototeque.models.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Page<Image> findByUserId(int userId, Pageable pageable);
    Page<Image> findAllByOrderByDateCreationDesc(Pageable pageable);
    Optional<Image> findById(int id);
}