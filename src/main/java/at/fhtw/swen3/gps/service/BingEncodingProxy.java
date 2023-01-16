package at.fhtw.swen3.gps.service;

import at.fhtw.swen3.gps.GeoEncodingService;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class BingEncodingProxy implements GeoEncodingService {
    @Override
    public GeoCoordinateEntity encodeAddress(RecipientEntity recipientEntity)  {

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("nominatim.openstreetmap.org")
                .path("/search")
                .queryParam("street", recipientEntity.getStreet())
                .queryParam("city", recipientEntity.getCity())
                .queryParam("postalcode", recipientEntity.getPostalCode())
                .queryParam("country", recipientEntity.getCountry())
                .queryParam("format", "json")
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest;
        HttpResponse response;

        try
        {
            httpRequest = HttpRequest.newBuilder().uri(new URI(uri.toString())).build();
        }
        catch(Exception e) {
            return null;
        }

        try
        {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        }
        catch (IOException | InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        String json_string = response.body().toString();
        JSONArray buffa;
        JSONObject buffb;
        try
        {
            buffa = new JSONArray(json_string);
            buffb = new JSONObject(buffa.get(0).toString());
        }
        catch (JSONException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            double lat = Double.parseDouble(buffb.get("lat").toString());
            double lon = Double.parseDouble(buffb.get("lon").toString());
            GeoCoordinateEntity geoCoordinateEntity = new GeoCoordinateEntity();
            geoCoordinateEntity.setLat(lat);
            geoCoordinateEntity.setLon(lon);
            return geoCoordinateEntity;
        }
        catch (JSONException e)
        {
            throw new RuntimeException(e);
        }

    }


}


