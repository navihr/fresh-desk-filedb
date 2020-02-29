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
package com.navi.filedb.service;

import com.navi.filedb.model.request.NodeRequest;
import com.navi.filedb.model.response.Response;

import java.util.Map;

/**
 * Package Name   : com.navi.filedb.service,
 * Interface Name : CreateNodeService,
 * Created By     : navi,
 * Created Time   : 22/02/20 9:30 PM
 */

public interface CreateNodeService extends FileDBService<NodeRequest, Response<Map<String, Object>>> {
}
