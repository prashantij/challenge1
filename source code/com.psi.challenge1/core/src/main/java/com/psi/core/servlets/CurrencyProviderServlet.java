/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.psi.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

/**
 * Servlet  provides currency data fetching from repository.
 */
@Component(service=Servlet.class,
           property={
                   Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
                   "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                   "sling.servlet.resourceTypes="+ "challenge1/components/structure/page",
                   "sling.servlet.paths=/bin/challenge1",
                   "sling.servlet.selectors="+ "currenciesdata",
                   "sling.servlet.extensions=" + "json"
           })
public class CurrencyProviderServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;
    private static final String resourcePath = "/content/challenge1/en/testcurrency_page/jcr:content/root/responsivegrid/helloworld";
    private static final String propertyName =  "jsondata";
    
    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
        final Resource resource = req.getResource();
        Resource hellowrold =  resource.getResourceResolver().getResource(resourcePath);
         resp.setContentType("application/json");
         String jsonSrting = "";
         if(hellowrold!=null && hellowrold.getValueMap().containsKey(propertyName)) {
        	 jsonSrting= (String) hellowrold.getValueMap().get(propertyName);
         }else {
        	 jsonSrting = null;
         }
         resp.getWriter().write( jsonSrting);
    }
}
