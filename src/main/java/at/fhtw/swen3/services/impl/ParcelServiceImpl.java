package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.ParcelService;
import org.springframework.stereotype.Service;

@Service
public class ParcelServiceImpl implements ParcelService {

    ParcelRepository parcelRepository;

    public ParcelServiceImpl(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    @Override
    public ParcelEntity getTrackingInformation(String trackingId) {

        //Validation

        //Saving in REPO

        return null;
    }
}
