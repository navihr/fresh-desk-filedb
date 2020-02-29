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

import com.navi.filedb.model.request.Action;
import com.navi.filedb.model.request.NodeRequest;
import com.navi.filedb.model.response.Response;
import com.navi.filedb.service.CreateNodeService;
import com.navi.filedb.service.DeleteNodeService;
import com.navi.filedb.service.ReadNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Package Name : com.navi.filedb.controller,
 * Class Name   : FreshworksFileDBController,
 * Created By   : navi,
 * Created Time : 12/02/20 12:01 AM
 */
@RestController
@RequestMapping(value = "/filedb")
public class FreshworksFileDBController {

    @Autowired
    private CreateNodeService createNodeService;

    @Autowired
    private ReadNodeService readNodeService;

    @Autowired
    private DeleteNodeService deleteNodeService;
    /**
     * Currently we are differentiate user using userid. Future we use session or token to differentiate user.
     */
    @PostMapping(value = "/{key}")
    public ResponseEntity<Response<Map<String, Object>>> createFileData(@PathVariable(value = "key", required = true) String key,
                                                                        @RequestBody(required = true) Map<String, Object> data,
                                                                        @RequestHeader(required = true) String userId) {
        NodeRequest request = new NodeRequest();
        request.setAction(Action.CREATE)
            .setKey(key)
            .setUserId(userId)
            .setNode(data);
        return new ResponseEntity<>(createNodeService.process(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{key}")
    public ResponseEntity<Response<Map<String, Object>>> getFileData(@PathVariable(value = "key", required = true) String key,
                                         @RequestHeader(required = true) String userId) {
        NodeRequest request = new NodeRequest();
        request.setAction(Action.READ)
                .setKey(key)
                .setUserId(userId);
        return new ResponseEntity<>(readNodeService.process(request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{key}")
    public ResponseEntity<Response<Map<String, Object>>> deleteFileData(@PathVariable(value = "key", required = true) String key,
                                         @RequestHeader(required = true) String userId) {
        NodeRequest request = new NodeRequest();
        request.setAction(Action.DELETE)
                .setKey(key)
                .setUserId(userId);
        return new ResponseEntity<>(deleteNodeService.process(request), HttpStatus.ACCEPTED);
    }

}
