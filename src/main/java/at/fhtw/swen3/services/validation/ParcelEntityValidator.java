package at.fhtw.swen3.services.validation;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.Parcel;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ParcelEntityValidator {

    public ParcelEntity validateParcelEntity(ParcelEntity parcelEntity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ParcelEntity>> violations = validator.validate(parcelEntity);
        return parcelEntity;
    }

}
