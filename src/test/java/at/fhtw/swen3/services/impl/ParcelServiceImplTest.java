package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ParcelServiceImplTest {

    @Autowired
    private ParcelRepository parcelRepository;
    @Autowired
    private RecipientRepository recipientRepository;

    private final ParcelService parcelService;

    ParcelServiceImplTest(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @Test
    void submitNewParcel() {
        ParcelEntity parcelEntity = new ParcelEntity();
        RecipientEntity recipientEntity = new RecipientEntity();
        RecipientEntity recipientEntityTwo = new RecipientEntity();

        //recipient
        recipientEntity.setId(3233L);
        recipientEntity.setCity("Vienna");
        recipientEntity.setStreet("Hauptstraße 1");
        recipientEntity.setName("Max Mustermann");
        recipientEntity.setPostalCode("1200");
        recipientEntity.setCountry("Austria");

        //sender
        recipientEntityTwo.setId(3233L);
        recipientEntityTwo.setCity("Vienna");
        recipientEntityTwo.setStreet("Hauptstraße 31");
        recipientEntityTwo.setName("Hanna Musterfrau");
        recipientEntityTwo.setPostalCode("1100");
        recipientEntityTwo.setCountry("Austria");

        parcelEntity.setId(3233455L);
        parcelEntity.setTrackingId("323456789");
        parcelEntity.setRecipient(recipientEntity);
        parcelEntity.setState(TrackingInformation.StateEnum.INTRANSPORT);
        parcelEntity.setWeight(1.0f);
        parcelEntity.setSender(recipientEntityTwo);

        try {
            parcelService.submitNewParcel(parcelEntity);
            assertTrue(parcelService.findParcelByTrackingId(parcelEntity.getTrackingId()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void reportParcelDelivery() {
        try {
            assertTrue(parcelService.reportParcelDelivery("323456789"));
            TrackingInformation trackingInformation = parcelService.getTrackingInformation("323456789");
            assertEquals(trackingInformation.getState(), TrackingInformation.StateEnum.DELIVERED);
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
    }
}