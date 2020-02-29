/*******************************************************************************
 * Copyright ©2XXX-2XXX IBreakers - All rights reserved.
 *
 * All information contained here in is, and remains the property of IBreakers.
 * IBreakers including, without limitation, all software and other elements thereof,
 * are owned or controlled exclusively by IBreakers and protected by copyright, patent
 * and other laws. Use without permission is prohibited.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *
 * For further information contact IBreakers at info@ibreakers.co.
 ******************************************************************************/
package com.navi.filedb.config;

import lombok.Getter;

/**
 * Package Name : com.ibreakers.zkapp.ibzkapp.errorhandler,
 * Class Name   : IbException,
 * Created By   : navi,
 * Created Time : 6/8/19 12:54 PM
 */

public class FileDBException extends RuntimeException {

    @Getter
    private final String errorCode;

    @Getter
    private final String errorMessage;

    @Getter
    private final int httpStatusCode;

    @Getter
    private final transient Object[] parameters;

    public FileDBException(String errorCode) {
        this(errorCode, null, null);
    }

    public FileDBException(String errorCode, String errorMessage) {
        this(errorCode, errorMessage, null);
    }

    public FileDBException(String errorCode, String errorMessage, int httpStatusCode) {
        this(errorCode, errorMessage, null, httpStatusCode);
    }

    public FileDBException(String errorCode, String errorMessage, Throwable throwable) {
        this(errorCode, errorMessage, throwable, -1);
    }

    public FileDBException(String errorCode, String errorMessage, Throwable throwable, int httpStatusCode,
                              Object... parameters) {
        super(errorMessage, throwable);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.httpStatusCode = httpStatusCode;
        this.parameters = parameters.clone();
    }
}
