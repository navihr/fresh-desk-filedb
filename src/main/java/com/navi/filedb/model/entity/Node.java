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
package com.navi.filedb.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Map;

/**
 * Package Name : com.navi.filedb.entity,
 * Class Name   : Node,
 * Created By   : navi,
 * Created Time : 20/02/20 4:40 PM
 */

@Getter
@Setter
public class Node implements Serializable {
    private long createdTime;
    private long expiryTime;
    private Map<String,Object> value;

    @JsonIgnore
    public boolean isExpired() {
        Calendar currentTime = Calendar.getInstance();
        return expiryTime < currentTime.getTimeInMillis();
    }
}
