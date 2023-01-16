package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.gps.service.BingEncodingProxy;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class BingEncodingProxyTest {

    @Test
    void encodeAddressTestCorrect() {
        BingEncodingProxy geoEncodingService = new BingEncodingProxy();

        RecipientEntity recipient = new RecipientEntity();
        recipient.setStreet("Zieglergasse");
        recipient.setCity("Vienna");
        recipient.setCountry("Austria");
        recipient.setPostalCode("1070");

        GeoCoordinateEntity coordinates = new GeoCoordinateEntity();
        coordinates.setLon(16.3450036);
        coordinates.setLat(48.2018829);

        GeoCoordinateEntity coordinatesTest = geoEncodingService.encodeAddress(recipient);

        assertEquals(coordinatesTest.getLat(), coordinates.getLat());
        assertEquals(coordinatesTest.getLon(), coordinates.getLon());

    }

    @Test
    void encodeAddressTestFalse() {
        BingEncodingProxy geoEncodingService = new BingEncodingProxy();

        RecipientEntity recipient = new RecipientEntity();
        recipient.setStreet("Schrannengasse");
        recipient.setCity("Salzburg");
        recipient.setCountry("Austria");
        recipient.setPostalCode("5020");

        GeoCoordinateEntity coordinates = new GeoCoordinateEntity();
        coordinates.setLon(16.3450036);
        coordinates.setLat(48.2018829);

        GeoCoordinateEntity coordinatesTest = geoEncodingService.encodeAddress(recipient);

        assertNotEquals(coordinatesTest.getLat(), coordinates.getLat());
        assertNotEquals(coordinatesTest.getLon(), coordinates.getLon());

    }
}