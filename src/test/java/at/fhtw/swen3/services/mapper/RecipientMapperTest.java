package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.Recipient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecipientMapperTest {

    /*@Test
    void entityToDto() {
        RecipientEntity recipientEntity = new RecipientEntity();
        recipientEntity.setStreet("Maxstraße 1");
        recipientEntity.setCountry("Austria");
        recipientEntity.setName("Alex");
        recipientEntity.setCity("Wien");
        recipientEntity.setPostalCode("1345");
        recipientEntity.setId(123455L);

        Recipient recipient = RecipientMapper.INSTANCE.entityToDto(recipientEntity);
        assertEquals(recipient.getStreet(), recipientEntity.getStreet());
        assertEquals(recipient.getCountry(), recipientEntity.getCountry());
        assertEquals(recipient.getName(), recipientEntity.getName());
        assertEquals(recipient.getCity(), recipientEntity.getCity());
        assertEquals(recipient.getPostalCode(), recipientEntity.getPostalCode());
    }

    @Test
    void dtoToEntity() {
        Recipient recipient = new Recipient();
        recipient.setStreet("Hauptstraße 1");
        recipient.setCountry("Austria");
        recipient.setName("Tina");
        recipient.setCity("Wien");
        recipient.setPostalCode("1236");

        RecipientEntity recipientEntity = RecipientMapper.INSTANCE.dtoToEntity(recipient);
        assertEquals(recipient.getStreet(), recipientEntity.getStreet());
        assertEquals(recipient.getCountry(), recipientEntity.getCountry());
        assertEquals(recipient.getName(), recipientEntity.getName());
        assertEquals(recipient.getCity(), recipientEntity.getCity());
        assertEquals(recipient.getPostalCode(), recipientEntity.getPostalCode());
    }*/
}