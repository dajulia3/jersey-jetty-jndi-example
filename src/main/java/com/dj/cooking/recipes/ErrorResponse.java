package com.dj.cooking.recipes;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ErrorResponse {
    private String errorMessage;

    @JsonCreator
    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorResponse that = (ErrorResponse) o;

        return !(errorMessage != null ? !errorMessage.equals(that.errorMessage) : that.errorMessage != null);

    }

    @Override
    public int hashCode() {
        return errorMessage != null ? errorMessage.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
            "errorMessage='" + errorMessage + '\'' +
            '}';
    }
}
