package at.fhtw.swen3.services.validation;

import at.fhtw.swen3.persistence.BLValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
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

    public <T> boolean validate(T o) throws BLValidationException {
        javax.validation.Validator validator = getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(o);
        if (!violations.isEmpty()) {
            for (ConstraintViolation violation : violations) {
                log.error(violation.getMessage());
                throw new BLValidationException(1L, violation.getMessage(), null);
            }
        }
        return true;
    }

    public <T> Set<ConstraintViolation<T>> validates(T o) throws BLValidationException {
        javax.validation.Validator validator = getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(o);
        if (!violations.isEmpty()) {
            for (ConstraintViolation violation : violations) {
                log.error(violation.getMessage());
                throw new BLValidationException(1L, violation.getMessage(), null);
            }
        }
        return violations;
    }
}
