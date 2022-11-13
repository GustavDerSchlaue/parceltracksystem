package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Slf4j
public class ParcelServiceImpl implements ParcelService {

    private final ParcelRepository parcelRepository;
    private final RecipientRepository recipientRepository;
    private final Validator validator;

    @Override
    public ParcelEntity mapParcelDtoToParcelEntity(@NotNull Parcel parcelDto) {
        return ParcelMapper.INSTANCE.dtoToEntity(parcelDto);
    }

    @Override
    public Parcel mapParcelEntityToParcelDto(@NotNull ParcelEntity parcelEntity) {
        return ParcelMapper.INSTANCE.entityToDto(parcelEntity);
    }

    @Override
    public void submitNewParcel(@NotNull Parcel parcelDto) {
        //Map Dto to Entity
        log.info("Mapping to ParcelDto to ParcelEntity submitNewParcel()");
        ParcelEntity parcelEntity = this.mapParcelDtoToParcelEntity(parcelDto);
        //Validate Entity
        if(validator.validate(parcelEntity)){
            //Save Entity in REPO
            log.info("Saving ParcelEntity to REPO in submitNewParcel()");
            parcelRepository.save(parcelEntity);
            log.info("Saved ParcelEntity to REPO in submitNewParcel()");
        }
    }

    @Override
    public Parcel getTrackingInformation(@NotNull String trackingId) {
        log.info("Returning ParcelDto from getTrackingInformation() to ParcelApiController");
        return this.mapParcelEntityToParcelDto(parcelRepository.findById(Integer.parseInt(trackingId)));
    }
}
