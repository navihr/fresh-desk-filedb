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
package com.navi.filedb.service.impl;

import com.navi.filedb.config.FileDBConfig;
import com.navi.filedb.model.entity.Node;
import com.navi.filedb.model.request.NodeRequest;
import com.navi.filedb.model.response.Response;
import com.navi.filedb.model.response.ResponseStatus;
import com.navi.filedb.repository.NodeRepository;
import com.navi.filedb.service.DeleteNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Package Name : com.navi.filedb.service.impl,
 * Class Name   : DeleteNodeServiceImpl,
 * Created By   : navi,
 * Created Time : 29/02/20 10:03 AM
 */
@Service
public class DeleteNodeServiceImpl implements DeleteNodeService {

    @Autowired
    private FileDBConfig fileDBConfig;

    @Override
    public Response<Map<String, Object>> process(NodeRequest input) {
        NodeRepository repo = new NodeRepository(input.getUserId(), fileDBConfig);
        Node node = repo.deleteById(input.getKey());
        Response<Map<String, Object>> response = new Response<>();
        response.setStatus(ResponseStatus.Success);
        response.setDataBox(node.getValue());
        return response;
    }
}
