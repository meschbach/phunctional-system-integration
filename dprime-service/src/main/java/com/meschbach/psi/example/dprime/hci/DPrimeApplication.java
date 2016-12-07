/*
 * Copyright 2010 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/hci/DPrimeApplication.java $
 * $Id: DPrimeApplication.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.hci;

import com.meschbach.psi.example.dprime.hci.pages.DashboardPage;
import com.meschbach.psi.example.dprime.DPrimeService;
import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class DPrimeApplication extends WebApplication {

    IModel<DPrimeService> dprimeService;
    WebApplicationContext springContext;

    @Override
    protected void init() {
        /*
         * 
         */
        super.init();
        /*
         * Find our spring context
         */
        springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        /*
         * Construct a new onus contianing our bean
         */
        dprimeService = new SpringBeanOnus<DPrimeService>("coreService", DPrimeService.class);
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new DPrimeSession(request);
    }

    public IModel<DPrimeService> getDPrimeService() {
        return dprimeService;
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return DashboardPage.class;
    }

    
    public WebApplicationContext getSpringContext() {
        return springContext;
    }
}
