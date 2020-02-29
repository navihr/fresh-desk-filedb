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
package com.navi.filedb.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.navi.filedb.constant.FileDBErrorCode;
import com.navi.filedb.model.entity.Node;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Package Name : com.navi.filedb.config,
 * Class Name   : FileManager,
 * Created By   : navi,
 * Created Time : 22/02/20 8:16 PM
 */

public class FileManager {

    private FileDBConfig fileDBConfig = null;

    private String userId = null;
    private File jsonFile = null;
    private Map<String, Node> currentNodes = null;

    public FileManager(String userId, FileDBConfig fileDBConfig) {
        this.fileDBConfig = fileDBConfig;
        this.userId = userId;
        this.jsonFile = collectFile();
        this.currentNodes = readNodes(false);
    }

    private String concatPath(boolean withFile) {
        String path = fileDBConfig.getPath();
        if(this.userId == null) {
            throw new FileDBException(FileDBErrorCode.NOT_FOUND, "User not available.");
        }
        if(!path.endsWith("/")) {
            path = path.concat("/");
        }
        if(userId != null) {
            path = path.concat(userId);
        }
        if(withFile) {
            path = path.concat("/collection.json");
        }
        return path;
    }

    private File collectFile() {
        File file = null;
        if(!isExist(concatPath(true))) {
            createFile();
        }
        file = new File(concatPath(true));
        return file;
    }

    private void createFile() {
        File file = null;
        try {
            if(!isExist(concatPath(false))) {
                file = new File(concatPath(false));
                file.mkdirs();
            }
            if(!isExist(concatPath(true))) {
                file = new File(concatPath(true));
                file.createNewFile();
            }
        }
        catch (IOException e) {
            throw new FileDBException(FileDBErrorCode.CREATE_NODE_ACCESSOR_EXCEPTION, e.getMessage());
        }

    }

    private Map<String, Node> readNodes(boolean withException) {
        Map<String, Node> nodes = null;
        try {
            nodes = new ObjectMapper().readValue(jsonFile, new TypeReference<LinkedHashMap<String, Node>>() {});
        } catch (IOException e) {
            if(withException) {
                throw new FileDBException(FileDBErrorCode.READ_NODE_ACCESSOR_EXCEPTION, e.getMessage());
            }
            else {
                nodes = new LinkedHashMap<>();
            }
            //ToDo LOGGER
        }
        return nodes;
    }

    private synchronized void writeNodes() {
        try {
            new ObjectMapper().writeValue(jsonFile, currentNodes);
        } catch (IOException e) {
            throw new FileDBException(FileDBErrorCode.WRITE_NODE_ACCESSOR_EXCEPTION, e.getMessage());
        }
    }

    private boolean validationNode(String key, Node node) {
        if(key.length() > 32) {
            throw new FileDBException(FileDBErrorCode.KEY_SIZE_EXCEEDED);
        }
        /*Map<String, Object> value = node.getValue();
        long size = InstrumentationAgent.getObjectSize(value);
        if((size/1024) > 16) {
            throw new FileDBException(FileDBErrorCode.VALUE_SIZE_EXCEEDED);
        }*/
        return true;
    }

    public boolean containsKey(String key) {
        return currentNodes.containsKey(key);
    }
    private boolean isExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    public void putNode(String key, Node node) {
        if(validationNode(key, node)) {
            this.currentNodes.put(key,node);
            this.writeNodes();
        }
    }

    public Node getNode(String key) {
        return this.currentNodes.get(key);
    }

    public Map<String, Node> getAllNode() {
        return this.currentNodes;
    }

    public void deleteNode(String key) {
        this.currentNodes.remove(key);
        this.writeNodes();
    }
}
