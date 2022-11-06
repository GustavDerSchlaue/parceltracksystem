package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.services.dto.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {

    Truck findById(int id);
}
