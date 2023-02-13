package kz.redmadrobot.redmadrobotbootcampproject.security.repository;

import java.util.Optional;

import kz.redmadrobot.redmadrobotbootcampproject.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
