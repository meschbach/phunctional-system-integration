/*
 *  Copyright 2010-2011 Mark Eschbach
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
 *
 * $HeadURL: http://phunctional-system-integration.googlecode.com/svn/trunk/cise-oss/src/main/java/com/meschbach/cise/dom/NodeIterator.java $
 * $Id: NodeIterator.java 242 2011-03-07 02:48:48Z marioandrest@gmail.com $
 */
package com.meschbach.cise.dom;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author "Mark Eschbach" meschbach@gmail.com
 */
public class NodeIterator<N extends Node> implements Iterator<N> {

    NodeList nodes;
    int index;

    public NodeIterator(NodeList nodes) {
        this.nodes = nodes;
        index = 0;
    }

    public boolean hasNext() {
        return nodes.getLength() > index;
    }

    public N next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Node n = nodes.item(index);
        index++;
        return (N) n;
    }

    public N getCurrent() {
        return (N) nodes.item(index - 1);
    }

    public void remove() {
        throw new UnsupportedOperationException("Not enough information available (Use RemovableElementIterator).");
    }
}
