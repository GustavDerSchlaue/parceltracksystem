package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.BLValidationException;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class ParcelServiceImplTest {
/*
    @Autowired
    private ParcelRepository parcelRepository;
    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    private ParcelService parcelService;

    @Autowired
    private Validator validator;



    @Test
    void submitNewParcel() {
        ParcelEntity parcelEntity = new ParcelEntity();
        RecipientEntity recipientEntity = new RecipientEntity();
        RecipientEntity recipientEntityTwo = new RecipientEntity();

        //recipient
        recipientEntity.setId(3233L);
        recipientEntity.setCity("Vienna");
        recipientEntity.setStreet("Hauptstrasse 1");
        recipientEntity.setName("Max Mustermann");
        recipientEntity.setPostalCode("1200");
        recipientEntity.setCountry("Austria");

        //sender
        recipientEntityTwo.setId(3233L);
        recipientEntityTwo.setCity("Vienna");
        recipientEntityTwo.setStreet("Hauptstrasse 31");
        recipientEntityTwo.setName("Hanna Musterfrau");
        recipientEntityTwo.setPostalCode("1100");
        recipientEntityTwo.setCountry("Austria");

        parcelEntity.setId(3233455L);
        parcelEntity.setTrackingId("323456789");
        parcelEntity.setRecipient(recipientEntity);
        parcelEntity.setState(TrackingInformation.StateEnum.INTRANSPORT);
        parcelEntity.setWeight(1.0f);
        parcelEntity.setSender(recipientEntityTwo);
        parcelEntity.setVisitedHops(new ArrayList<>());
        parcelEntity.setFutureHops(new ArrayList<>());

        Set<ConstraintViolation<ParcelEntity>> violationSet;
        try {
            violationSet = validator.validates(parcelEntity);
            if(violationSet.isEmpty()) {
                parcelService.submitNewParcel(parcelEntity.getTrackingId(), parcelEntity);
                assertTrue(parcelService.findParcelByTrackingId(parcelEntity.getTrackingId()));
            }
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void reportParcelDelivery() {
        try {
            assertTrue(parcelService.reportParcelDelivery("323456789"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getTrackingInformation() {
        try {
            TrackingInformation trackingInformation = parcelService.getTrackingInformation("323456789");
            assertEquals(trackingInformation.getState(), TrackingInformation.StateEnum.DELIVERED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void findParcelByTrackingId() {
        try {
            assertTrue(parcelService.findParcelByTrackingId("323456789"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/
}