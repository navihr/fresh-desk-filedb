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

/*
 * Package Name : com.ibreakers.zkapp.ibzkapp.model.stateless,
 * Enum Name    : ResponseStatus,
 * Created By   : navi,
 * Created Time : 20/7/19 11:20 PM
 */

public enum ResponseStatus {
    Success("Response Status Success"),
    Failure("Response Status Failure");

    private final String reasonPhrase;

    private ResponseStatus(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}
