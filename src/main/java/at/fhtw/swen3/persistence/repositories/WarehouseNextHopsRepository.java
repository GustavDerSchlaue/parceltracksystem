package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.services.dto.WarehouseNextHops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseNextHopsRepository extends JpaRepository<WarehouseRepository, Long> {

    WarehouseNextHops findById(int id);
}
