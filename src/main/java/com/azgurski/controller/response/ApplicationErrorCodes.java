package com.azgurski.controller.response;

public enum ApplicationErrorCodes {

    SQL_ERROR(10),

    INVALID_INPUT_FORMAT_ERROR(20),
    ENTITY_NOT_FOUND(40),
    ENTITY_NOT_CREATED(50),
    FATAL_ERROR(1);

    public int getCodeId() {
        return codeId;
    }

    private int codeId;

    ApplicationErrorCodes(int codeId) {
        this.codeId = codeId;
    }
}
