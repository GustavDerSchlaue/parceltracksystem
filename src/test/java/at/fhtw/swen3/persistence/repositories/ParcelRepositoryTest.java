package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.BLException;
import at.fhtw.swen3.persistence.BLValidationException;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.NewParcelInfoMapper;
import at.fhtw.swen3.services.validation.Validator;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParcelRepositoryTest {

 /*   @Autowired
    private ParcelRepository parcelRepository;
    @Autowired
    private RecipientRepository recipientRepository;
    @Autowired
    private  Validator validator;

    @Test
    void saveParcel(){

        ParcelEntity parcelEntity = new ParcelEntity();
        RecipientEntity recipientEntity = new RecipientEntity();
        RecipientEntity recipientEntityTwo = new RecipientEntity();

        //recipient
        recipientEntity.setId(1233L);
        recipientEntity.setCity("Vienna");
        recipientEntity.setStreet("Hauptstrasse 1");
        recipientEntity.setName("Max Mustermann");
        recipientEntity.setPostalCode("1200");
        recipientEntity.setCountry("Austria");

        //sender
        recipientEntityTwo.setId(1233L);
        recipientEntityTwo.setCity("Vienna");
        recipientEntityTwo.setStreet("Hauptstrasse 31");
        recipientEntityTwo.setName("Hanna Musterfrau");
        recipientEntityTwo.setPostalCode("1100");
        recipientEntityTwo.setCountry("Austria");

        parcelEntity.setId(1233455L);
        parcelEntity.setTrackingId("123456789");
        parcelEntity.setRecipient(recipientEntity);
        parcelEntity.setState(TrackingInformation.StateEnum.INTRANSPORT);
        parcelEntity.setWeight(1.0f);
        parcelEntity.setSender(recipientEntityTwo);

        recipientRepository.save(parcelEntity.getSender());
        recipientRepository.save(parcelEntity.getRecipient());
        parcelRepository.save(parcelEntity);


        assertEquals(parcelRepository.findByTrackingId("123456789").getState().toString(), parcelEntity.getState().toString());
    }

    @Test
    void saveParcelWithFalseWeight(){
        //SOLL DER FAILEN?
        ParcelEntity parcelEntity = new ParcelEntity();
        RecipientEntity recipientEntity = new RecipientEntity();
        RecipientEntity recipientEntityTwo = new RecipientEntity();

        //recipient
        recipientEntity.setId(2233L);
        recipientEntity.setCity("Vienna");
        recipientEntity.setStreet("Hauptstrasse");
        recipientEntity.setName("Max Mustermann");
        recipientEntity.setPostalCode("1200");
        recipientEntity.setCountry("Austria");

        //sender
        recipientEntityTwo.setId(2233L);
        recipientEntityTwo.setCity("Vienna");
        recipientEntityTwo.setStreet("Hauptstrasse");
        recipientEntityTwo.setName("Hanna Musterfrau");
        recipientEntityTwo.setPostalCode("1100");
        recipientEntityTwo.setCountry("Austria");

        parcelEntity.setId(2233455L);
        parcelEntity.setTrackingId("223456789");
        parcelEntity.setRecipient(recipientEntity);
        //parcelEntity.setState(TrackingInformation.StateEnum.INTRANSPORT);
        parcelEntity.setSender(recipientEntityTwo);

        parcelEntity.setState(TrackingInformation.StateEnum.PICKUP);
        parcelEntity.setFutureHops(new ArrayList<>());
        parcelEntity.setVisitedHops(new ArrayList<>());
        //Validate Entity
        Set<ConstraintViolation<ParcelEntity>> violationSet;
        try {
            violationSet = validator.validates(parcelEntity);
            if(violationSet.isEmpty()) {
                recipientRepository.save(parcelEntity.getSender());
                recipientRepository.save(parcelEntity.getRecipient());
                parcelRepository.save(parcelEntity);
            }
        } catch (BLValidationException e) {
            assertTrue(true);
        }
        assertFalse(false);
    }*/
}