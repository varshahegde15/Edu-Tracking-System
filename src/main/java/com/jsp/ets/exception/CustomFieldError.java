package com.jsp.ets.exception;

public class CustomFieldError {

    private String fieldName;
    private String errorMessage;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static CustomFieldError create(String fieldName, String errorMessage) {
        CustomFieldError error = new CustomFieldError();
        error.setErrorMessage(errorMessage);
        error.setFieldName(fieldName);

        return error;
    }

}
