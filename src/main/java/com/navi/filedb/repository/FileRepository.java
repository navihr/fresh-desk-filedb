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
package com.navi.filedb.repository;

import com.navi.filedb.config.FileDBConfig;
import com.navi.filedb.config.FileDBException;
import com.navi.filedb.config.FileManager;
import com.navi.filedb.constant.FileDBErrorCode;
import com.navi.filedb.model.entity.Node;

import java.util.Calendar;
import java.util.Map;

/**
 * Package Name : com.navi.filedb.repository,
 * Class Name   : FileRepository,
 * Created By   : navi,
 * Created Time : 20/02/20 5:17 PM
 */
public abstract class FileRepository {

    private FileDBConfig fileDBConfig = null;
    private FileManager fileManager = null;

    public FileRepository(String userId, FileDBConfig fileDBConfig) {
        this.fileDBConfig = fileDBConfig;
        fileManager = new FileManager(userId, this.fileDBConfig);
    }
    public Node save(String id, Node node) {
        if(fileManager.containsKey(id)) {
            throw new FileDBException(FileDBErrorCode.ALREADY_AVAILABLE, "Key already available.");
        }
        Calendar calendar = Calendar.getInstance();
        node.setCreatedTime(calendar.getTimeInMillis());
        node.setExpiryTime(calendar.getTimeInMillis() + fileDBConfig.getExpiryDuration());
        fileManager.putNode(id, node);
        return node;
    }
    public Node findById(String id) {
        if(!fileManager.containsKey(id)) {
            throw new FileDBException(FileDBErrorCode.NOT_FOUND, "Key not found.");
        }
        Node node = fileManager.getNode(id);
        if(node.isExpired()) {
            throw new FileDBException(FileDBErrorCode.NODE_EXPIRED, "Node expired.");
        }
        return node;
    }
    public Map<String, Node> findAll() {
        if(fileManager.getAllNode() == null) {
            throw new FileDBException(FileDBErrorCode.NOT_FOUND, "Nodes not found.");
        }
        return fileManager.getAllNode();
    }
    public boolean existById(String id) {
        return fileManager.containsKey(id);
    }
    public Node deleteById(String id) {
        if(!fileManager.containsKey(id)) {
            throw new FileDBException(FileDBErrorCode.NOT_FOUND, "Key not found.");
        }
        Node node = fileManager.getNode(id);
        if(node.isExpired()) {
            throw new FileDBException(FileDBErrorCode.NODE_EXPIRED, "Node expired.");
        }
        fileManager.deleteNode(id);
        return node;
    }

}
