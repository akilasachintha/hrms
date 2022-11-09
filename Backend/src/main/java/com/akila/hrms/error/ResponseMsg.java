package com.akila.hrms.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMsg {
    private String message;
    private String status;
}
