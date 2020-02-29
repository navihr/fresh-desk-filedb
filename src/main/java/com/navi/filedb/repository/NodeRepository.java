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

/**
 * Package Name   : com.navi.filedb.repository,
 * Class Name : NodeRepository,
 * Created By     : navi,
 * Created Time   : 20/02/20 5:09 PM
 */

public class NodeRepository extends FileRepository {
    public NodeRepository(String userId, FileDBConfig fileDBConfig) {
        super(userId, fileDBConfig);
    }
}
