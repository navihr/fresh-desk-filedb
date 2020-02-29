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
package com.navi.filedb.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * Package Name : com.navi.filedb.model.request,
 * Class Name   : NodeRequest,
 * Created By   : navi,
 * Created Time : 22/02/20 11:26 PM
 */
@Getter
@Setter
@Accessors(chain = true)
public class NodeRequest implements Serializable {
    private String userId;
    private String key;
    private Map<String,Object> node;
    private Action action;
}
