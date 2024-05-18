package br.com.will.startup.exceptions;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int statusCode;
    private String status;
    private String message;
    private String details;
    private Date timestamp;

    public ExceptionResponse(int statusCode, String status, String message, String details, Date timestamp) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatus() {
        return status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
