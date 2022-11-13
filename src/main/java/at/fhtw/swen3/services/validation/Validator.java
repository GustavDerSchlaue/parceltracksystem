package at.fhtw.swen3.services.validation;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Slf4j
@Component
public class Validator {

    static ValidatorFactory getValidatorFactory() {
        return Validation.buildDefaultValidatorFactory();
    }

    javax.validation.Validator getValidator() {
        return getValidatorFactory().getValidator();
    }

    public  <T> boolean validate(T o) {
        javax.validation.Validator validator = getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(o);
        if (!violations.isEmpty()) {
            violations.forEach(v -> log.error(v.getMessage()));
            //throw new ConstraintViolationException(violations);
            return false;
        }
        return true;

    }

}
