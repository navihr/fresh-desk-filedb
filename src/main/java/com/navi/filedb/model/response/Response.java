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
package com.navi.filedb.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Package Name : com.ibreakers.zkapp.ibzkapp.model.stateless,
 * Class Name   : Response,
 * Created By   : navi,
 * Created Time : 20/7/19 10:03 PM
 */

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {
    @JsonIgnore
    private HttpStatus httpResponseCode;
    private String responseCode;
    private String responseMsg;
    private T dataBox;
    private ResponseStatus status;
    private List<Error> errors;

    public void setHttpResponseCode(HttpStatus httpResponseCode) {
        this.httpResponseCode = httpResponseCode;
        this.setResponseCode(String.valueOf(httpResponseCode.value()));
    }

    public void addErrors(Error e) {
        if(e == null) {
            return;
        }
        if(errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(e);
    }
}
