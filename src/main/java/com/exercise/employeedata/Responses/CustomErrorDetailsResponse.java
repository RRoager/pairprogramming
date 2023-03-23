package com.exercise.employeedata.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CustomErrorDetailsResponse {
    private Date timestamp;
    private String message;
    private String errorDetails;
}
