package app.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ServiceException extends Exception {

    private final HttpStatus reasonCode;
    private final String messageDetail;

    public ServiceException(HttpStatus reasonCode, String messageDetail) {
        this.reasonCode = reasonCode;
        this.messageDetail = messageDetail;
    }

    public ServiceException(String messageDetail){
        this.reasonCode = HttpStatus.INTERNAL_SERVER_ERROR;
        this.messageDetail = messageDetail;
    }
}
