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

/**
 * Package Name   : com.navi.filedb.service,
 * Interface Name : FileDBService,
 * Created By     : navi,
 * Created Time   : 22/02/20 9:31 PM
 */

public interface FileDBService<I,O> {
    O process(I input);
}
