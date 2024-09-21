package com.jsp.ets.utility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructure<T> {

    private int status;
    private String message;
    private T data;

    public static <T> ResponseStructure<T> create(int status, String message, T data) {
        ResponseStructure<T> structure = new ResponseStructure<>();
        structure.setData(data);
        structure.setMessage(message);
        structure.setStatus(status);

        return structure;
    }
}
