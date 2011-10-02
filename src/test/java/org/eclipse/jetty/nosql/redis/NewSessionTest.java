package org.eclipse.jetty.nosql.redis;

// ========================================================================
// Copyright (c) 1996-2009 Mort Bay Consulting Pty. Ltd.
// ------------------------------------------------------------------------
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// and Apache License v2.0 which accompanies this distribution.
// The Eclipse Public License is available at 
// http://www.eclipse.org/legal/epl-v10.html
// The Apache License v2.0 is available at
// http://www.opensource.org/licenses/apache2.0.php
// You may elect to redistribute this code under either of these licenses. 
// ========================================================================

import org.eclipse.jetty.server.session.AbstractNewSessionTest;
import org.eclipse.jetty.server.session.AbstractTestServer;
import org.junit.Test;

/**
 * NewSessionTest
 */
public class NewSessionTest extends AbstractNewSessionTest
{

    public AbstractTestServer createServer(int port, int max, int scavenge)
    {
        return new RedisTestServer(port,max,scavenge);
    }

    @Test
    public void testNewSession() throws Exception
    {
        super.testNewSession();
    }
}
