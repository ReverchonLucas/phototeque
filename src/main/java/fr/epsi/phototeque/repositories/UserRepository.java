package fr.epsi.phototeque.repositories;

import fr.epsi.phototeque.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByPseudo(String pseudo);
}