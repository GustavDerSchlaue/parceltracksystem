package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class RecipientRepositoryTest {
/*
    @Autowired
    private RecipientRepository recipientRepository;


    @Test
    void findByName() {
        RecipientEntity recipientEntity = new RecipientEntity();
        recipientEntity.setCountry("Austria");
        recipientEntity.setName("Claudia Klaufmann");
        recipientEntity.setCity("Mauthausen");
        recipientEntity.setPostalCode("1293");
        recipientEntity.setStreet("Mauthausen Hauptstraße 12");

        recipientRepository.save(recipientEntity);

        assertEquals(recipientRepository.findByName(recipientEntity.getName()).getName(), recipientEntity.getName());
    }*/
}