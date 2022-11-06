package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.services.dto.Hop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HopRepository extends JpaRepository<Hop, Long> {

    Hop findById(int id);
}
