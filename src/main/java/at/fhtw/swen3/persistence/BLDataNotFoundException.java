package at.fhtw.swen3.persistence;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import lombok.Getter;

@Getter
public class BLDataNotFoundException extends Exception {

    private final Exception innerException;
    private final ErrorEntity errorEntity;

    public BLDataNotFoundException(long errorId, String errorMsg, Exception e) {
        this.innerException = e;
        this.errorEntity = new ErrorEntity();
        errorEntity.setId(errorId);
        errorEntity.setErrorMessage(errorMsg);
    }

    public String getMessage() {
        return errorEntity.getErrorMessage();
    }
}
