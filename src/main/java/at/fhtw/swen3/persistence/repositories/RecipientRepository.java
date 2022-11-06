package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.services.dto.Recipient;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Long> {

    Recipient findById(int id);
}
