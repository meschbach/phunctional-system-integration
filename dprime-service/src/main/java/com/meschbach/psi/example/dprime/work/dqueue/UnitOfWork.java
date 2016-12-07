/*
 * Copyright 2010 Mark Eschbach.
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/dprime-service/src/main/java/com/meschbach/psi/example/dprime/work/dqueue/UnitOfWork.java $
 * $Id: UnitOfWork.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.psi.example.dprime.work.dqueue;

import com.meschbach.psi.example.dprime.work.DistributedWork;
import com.meschbach.psi.example.dprimecore.ipc.DivisionRequest;
import com.meschbach.psi.example.dprimecore.ipc.DivisionResponse;
import com.meschbach.psi.example.dprimecore.ipc.MultiplicationRequest;
import com.meschbach.psi.example.dprimecore.ipc.MultiplicationResponse;
import com.meschbach.psi.example.dprimecore.ipc.Respondable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author "Mark Eschbach" &lt;meschbach@gmail.com&gt;
 */
public class UnitOfWork implements Runnable {

    DistributedWork work;

    public UnitOfWork(DistributedWork work) {
        this.work = work;
    }

    public DistributedWork getWork() {
        return work;
    }

    public void setWork(DistributedWork work) {
        this.work = work;
    }

    public void run() {
        try {
            Object result = work.createRequest();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JAXB.marshal(result, baos);
            byte[] message = baos.toByteArray();
            /*
             * Create our stream
             */
            ByteArrayInputStream bais = new ByteArrayInputStream(message);
            /*
             * Grab the JAXB context
             */
            JAXBContext ctx = JAXBContext.newInstance(MultiplicationRequest.class, DivisionRequest.class);
            Unmarshaller mctx = ctx.createUnmarshaller();
            Respondable r = (Respondable) mctx.unmarshal(bais);
            Object calculatedRespnse = r.buildResponse();
            /*
             * Write  the response
             */
            ByteArrayOutputStream calculatedRespnseStream = new ByteArrayOutputStream();
            JAXB.marshal(calculatedRespnse, calculatedRespnseStream);
            /*
             * Unmarshall back into our normal objects
             */
            JAXBContext responseCtx = JAXBContext.newInstance(DivisionResponse.class, MultiplicationResponse.class);
            Unmarshaller responseUM = responseCtx.createUnmarshaller();
            Object responseObject = responseUM.unmarshal(new ByteArrayInputStream(calculatedRespnseStream.toByteArray()));
            work.responseRecieved(responseObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
