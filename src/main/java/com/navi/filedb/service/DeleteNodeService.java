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
 * Interface Name : DeleteNodeService,
 * Created By     : navi,
 * Created Time   : 29/02/20 10:02 AM
 */

public interface DeleteNodeService extends FileDBService<NodeRequest, Response<Map<String, Object>>> {
}
