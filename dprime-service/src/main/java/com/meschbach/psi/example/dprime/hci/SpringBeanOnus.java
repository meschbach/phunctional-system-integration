/*
 * Copyright 2010 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/hci/SpringBeanOnus.java $
 * $Id: SpringBeanOnus.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.hci;

import org.apache.wicket.model.LoadableDetachableModel;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author "Mark Eschbach" (meschbach@gmail.com)
 */
public class SpringBeanOnus<T> extends LoadableDetachableModel<T> {

    String beanName;
    Class<T> klass;

    public SpringBeanOnus(String beanName, Class<T> klass) {
        this.beanName = beanName;
        this.klass = klass;
    }

    @Override
    protected T load() {
        DPrimeApplication dpa = (DPrimeApplication)DPrimeApplication.get();
        WebApplicationContext wac = dpa.getSpringContext();
        return wac.getBean(beanName, klass);
    }

    @Override
    public void setObject(T object) {
        throw new UnsupportedOperationException("Not supported.");
    }
}
