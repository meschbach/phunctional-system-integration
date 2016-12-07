/*
 * Copyright 2010 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-web-node/src/main/java/com/meschbach/psi/examples/dprime/wnode/WicketHCI.java $
 * $Id: WicketHCI.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.examples.dprime.wnode;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class WicketHCI extends WebApplication {


    @Override
    public Class<? extends Page> getHomePage() {
        return DashboardPage.class;
    }
}
