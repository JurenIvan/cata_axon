package hr.fer.cata.auth.repository;

import hr.fer.cata.auth.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findAllByEmail(String email);
}
