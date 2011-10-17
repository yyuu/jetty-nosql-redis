package org.eclipse.jetty.nosql.redis;

import org.eclipse.jetty.nosql.memcached.AbstractMemcachedRemoveSessionTest;
import org.eclipse.jetty.server.session.AbstractTestServer;

public class RemoveSessionTest extends AbstractMemcachedRemoveSessionTest
{ 
    @Override
    public AbstractTestServer createServer(int port, int max, int scavenge)
    {
        return new RedisTestServer(port,max,scavenge);
    }
}
