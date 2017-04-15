package org.koweg.demo.dailylog.api;

public class ErrorMessage {
    
    private final String errorCode;
    private final String errorMessage;

    public ErrorMessage(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static class Builder {
        private String errorCode;

        private String errorMessage;

        public Builder withErrorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder withErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public ErrorMessage build() {
            return new ErrorMessage(errorCode, errorMessage);
        }
    }

}
