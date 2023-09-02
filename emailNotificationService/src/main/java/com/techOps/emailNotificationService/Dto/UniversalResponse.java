package com.techOps.emailNotificationService.Dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@ToString
public class UniversalResponse {
    private int status; //200 , 404 - not found , 400 -bad request
    private String message;
    private Object data;
    private String timestamp;
    //    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object metadata;

    public UniversalResponse() {
        this.timestamp = new Date().toString();
    }

    public UniversalResponse(int status, String message, Object data) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.timestamp = new Date().toString();
    }

    public UniversalResponse(int status, String message){
        this.status =status;
        this.message=message;
        this.data = new ArrayList<>();
        this.timestamp=new Date().toString();
    }

    public static ResponseEntity<UniversalResponse> responseFormatter(int httpResponse, int responseCode, String message, Object data){
        UniversalResponse universalResponse = new UniversalResponse();
        universalResponse.setMessage(message);
        universalResponse.setData(data);
        universalResponse.setStatus(responseCode);

        return new ResponseEntity<UniversalResponse>(universalResponse, HttpStatus.valueOf(httpResponse));
    }
}
