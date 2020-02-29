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
package com.navi.filedb.controller;

import com.navi.filedb.config.FileDBException;
import com.navi.filedb.constant.FileDBErrorCode;
import com.navi.filedb.constant.FileDBErrorMsg;
import com.navi.filedb.constant.FileDBMessageSource;
import com.navi.filedb.model.response.Error;
import com.navi.filedb.model.response.Response;
import com.navi.filedb.model.response.ResponseStatus;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serializable;
import java.util.Locale;


/**
 * Package Name : com.navi.filedb.controller,
 * Class Name   : FileDBExceptionHandler,
 * Created By   : navi,
 * Created Time : 20/02/20 7:13 PM
 */
@ControllerAdvice
@NoArgsConstructor
public class FileDBExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({FileDBException.class, IllegalArgumentException.class})
    public final ResponseEntity<Response<Object>> handleExceptionInternal(Exception ex, WebRequest request) {
        logger.debug("Start IbExceptionResponseProcessor -> handleExceptionInternal method");
        Response<Object> errorHandlerResponse = new Response<>();
        if (ex instanceof MethodArgumentNotValidException || ex instanceof IllegalArgumentException) {
            if(ex instanceof MethodArgumentNotValidException) {
                BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
                setMethodArgumentNotValidExceptionResponse(bindingResult, errorHandlerResponse);
            }
            else if(ex instanceof IllegalArgumentException) {
                errorHandlerResponse.addErrors(new Error().setTitle("java.lang.IllegalArgumentException")
                        .setMessage(ex.getMessage()));
            }
            errorHandlerResponse.setHttpResponseCode(HttpStatus.BAD_REQUEST);
            errorHandlerResponse.setStatus(ResponseStatus.Failure);
        }
        else if(ex instanceof FileDBException) {
            FileDBException fileDBException = (FileDBException) ex;
            int statusCode = fileDBException.getHttpStatusCode();
            HttpStatus httpStatus = null;
            if(statusCode > 0) {
                httpStatus = HttpStatus.valueOf(statusCode);
            }
            httpStatus = (httpStatus != null) ? httpStatus : HttpStatus.INTERNAL_SERVER_ERROR;
            errorHandlerResponse
                    .setHttpResponseCode(
                            setHttpStatus(fileDBException.getHttpStatusCode(), httpStatus));
            setResponseCode(errorHandlerResponse, fileDBException.getErrorCode());
            setResponseMessage(errorHandlerResponse, fileDBException.getErrorMessage());
        }
        else {
            errorHandlerResponse.setHttpResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);
            errorHandlerResponse.setResponseMsg(FileDBErrorMsg.UNKNOWN_MSG_TEXT);
        }
        return new ResponseEntity<>(errorHandlerResponse,errorHandlerResponse.getHttpResponseCode());
    }

    private void setMethodArgumentNotValidExceptionResponse(BindingResult bindingResult, Response<Object> errorHandlerResponse) {
        for(FieldError fieldError : bindingResult.getFieldErrors()) {
            errorHandlerResponse.addErrors(new Error().setTitle(fieldError.getField())
                    .setCode(fieldError.getCode()).setRejectedValue((Serializable) fieldError.getRejectedValue())
                    .setMessage(constructMessage(fieldError.getDefaultMessage(), messageSource)));
        }
    }

    private String constructMessage(String key, MessageSource messageSource) {
        String constructMsg = null;
        Locale currentLocale = LocaleContextHolder.getLocale();
        if (messageSource != null && currentLocale != null) {
            constructMsg = messageSource.getMessage(key, null, key, currentLocale);
        }
        return constructMsg;
    }

    private HttpStatus setHttpStatus(int httpStatusCode, HttpStatus defaultHttpStatus) {
        HttpStatus httpStatus = null;
        try {
            if (httpStatusCode >= HttpStatus.BAD_REQUEST.value()
                    && httpStatusCode < HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                httpStatus = HttpStatus.valueOf(httpStatusCode);
            } else {
                httpStatus = defaultHttpStatus;
            }
        } catch (IllegalArgumentException iae) {
            httpStatus = defaultHttpStatus;
        }
        return httpStatus;
    }

    private void setResponseCode(Response<Object> errorHandlerResponse, String errorCode) {
        if (errorCode == null || errorCode.isEmpty()) {
            errorHandlerResponse.setResponseCode(FileDBErrorCode.INPUT_VALIDATIONCODE_FAILED);
        } else {
            errorHandlerResponse.setResponseCode(errorCode);
        }
    }

    private void setResponseMessage(Response<Object> errorHandlerResponse, String errorMessage) {
        if (errorMessage == null || errorMessage.isEmpty()) {
            errorHandlerResponse.setResponseMsg(constructMessage(FileDBMessageSource.INPUT_VALIDATION_FAILED, messageSource));
        } else {
            errorHandlerResponse.setResponseMsg(errorMessage);
        }
    }
}
