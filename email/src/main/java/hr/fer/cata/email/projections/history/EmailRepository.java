package hr.fer.cata.email.projections.history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailDetails, Long> {
}
