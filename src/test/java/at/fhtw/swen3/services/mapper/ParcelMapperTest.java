package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
public class ParcelMapperTest {
/*
    @Test
    void entityToDto() {
        RecipientEntity recipientEntity = new RecipientEntity();
        recipientEntity.setStreet("Maxstraße 1");
        recipientEntity.setCountry("Austria");
        recipientEntity.setName("Alex");
        recipientEntity.setCity("Wien");
        recipientEntity.setPostalCode("1345");
        recipientEntity.setId(123455L);

        ParcelEntity parcelEntity = new ParcelEntity();
        parcelEntity.setState(TrackingInformation.StateEnum.PICKUP);
        parcelEntity.setTrackingId("123456789");
        parcelEntity.setId(3233L);
        parcelEntity.setSender(recipientEntity);
        parcelEntity.setRecipient(recipientEntity);
        parcelEntity.setWeight(1.0f);
        parcelEntity.setFutureHops(new ArrayList<>());
        parcelEntity.setVisitedHops(new ArrayList<>());

        Parcel parcel = ParcelMapper.INSTANCE.entityToDto(parcelEntity);

        assertEquals(parcel.getRecipient().getName(), parcelEntity.getRecipient().getName());
        assertEquals(parcel.getSender().getName(), parcelEntity.getSender().getName());
        assertEquals(parcel.getWeight(), parcelEntity.getWeight());
    }

    @Test
    void dtoToEntity() {
        Recipient recipient = new Recipient();
        recipient.setStreet("Hauptstraße 1");
        recipient.setCountry("Austria");
        recipient.setName("Tina");
        recipient.setCity("Wien");
        recipient.setPostalCode("1236");

        Parcel parcel = new Parcel();
        parcel.setRecipient(recipient);
        parcel.setSender(recipient);
        parcel.setWeight(2.0f);

        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel);

        assertEquals(parcel.getRecipient().getStreet(), parcelEntity.getRecipient().getStreet());
        assertEquals(parcel.getWeight(), parcelEntity.getWeight());
        assertEquals(parcel.getSender().getCity(), parcelEntity.getSender().getCity());
    }*/
}