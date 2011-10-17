package org.eclipse.jetty.nosql.redis;

import org.eclipse.jetty.nosql.memcached.AbstractMemcachedServerCrossContextSessionTest;
import org.eclipse.jetty.server.session.AbstractTestServer;

public class ServerCrossContextSessionTest extends AbstractMemcachedServerCrossContextSessionTest
{
    @Override
    public AbstractTestServer createServer(int port)
    {
        return new RedisTestServer(port);
    }
}
