/*
 * Copyright 2010 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-web-node/src/main/java/com/meschbach/psi/examples/dprime/wnode/DPWNSession.java $
 * $Id: DPWNSession.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.examples.dprime.wnode;

import org.apache.wicket.Application;
import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebSession;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class DPWNSession extends WebSession {

    public DPWNSession(Request request) {
        super(request);
    }

    public DPWNSession(WebApplication application, Request request) {
        super(application, request);
    }

    public DPWNSession(Application application, Request request) {
        super(application, request);
    }


}
