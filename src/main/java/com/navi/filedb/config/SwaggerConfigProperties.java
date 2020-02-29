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

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Package Name : com.navi.filedb.config,
 * Class Name   : SwaggerConfigProperties,
 * Created By   : navi,
 * Created Time : 28/7/19 11:22 PM
 */
@Data
@Configuration
public class SwaggerConfigProperties {
    @Value("${filedb.version}")
    private String apiVersion;
    @Value("${filedb.swagger.enabled}")
    private String enabled = "false";
    @Value("${filedb.swagger.title}")
    private String title;
    @Value("${filedb.swagger.description}")
    private String description;
    @Value("${filedb.swagger.useDefaultResponseMessages}")
    private String useDefaultResponseMessages;
    @Value("${filedb.swagger.enableUrlTemplating}")
    private String enableUrlTemplating;
    @Value("${filedb.swagger.deepLinking}")
    private String deepLinking;
    @Value("${filedb.swagger.defaultModelsExpandDepth}")
    private String defaultModelsExpandDepth;
    @Value("${filedb.swagger.defaultModelExpandDepth}")
    private String defaultModelExpandDepth;
    @Value("${filedb.swagger.displayOperationId}")
    private String displayOperationId;
    @Value("${filedb.swagger.displayRequestDuration}")
    private String displayRequestDuration;
    @Value("${filedb.swagger.filter}")
    private String filter;
    @Value("${filedb.swagger.maxDisplayedTags}")
    private String maxDisplayedTags;
    @Value("${filedb.swagger.showExtensions}")
    private String showExtensions;
}
