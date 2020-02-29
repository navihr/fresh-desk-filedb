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

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Package Name : com.navi.filedb.config,
 * Class Name   : FileConfig,
 * Created By   : navi,
 * Created Time : 20/02/20 6:05 PM
 */
@Getter
@Configuration
@ConfigurationProperties(prefix = "filedb.config")
public class FileDBConfig {
    private String path = "/home/navi/pentagon/test/";
    private int expiryDuration = 360000;
}
