package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.ErrorEntity;
import at.fhtw.swen3.services.dto.Error;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-23T19:56:07+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
public class ErrorMapperImpl implements ErrorMapper {

    @Override
    public Error entityToDto(ErrorEntity errorEntity) {
        if ( errorEntity == null ) {
            return null;
        }

        Error error = new Error();

        error.setErrorMessage( errorEntity.getErrorMessage() );

        return error;
    }

    @Override
    public ErrorEntity dtoToEntity(Error error) {
        if ( error == null ) {
            return null;
        }

        ErrorEntity errorEntity = new ErrorEntity();

        errorEntity.setErrorMessage( error.getErrorMessage() );

        return errorEntity;
    }
}