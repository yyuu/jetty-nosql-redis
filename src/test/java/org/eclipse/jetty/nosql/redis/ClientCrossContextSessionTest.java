package org.eclipse.jetty.nosql.redis;

import org.eclipse.jetty.nosql.memcached.AbstractMemcachedClientCrossContextSessionTest;
import org.eclipse.jetty.server.session.AbstractTestServer;

public class ClientCrossContextSessionTest extends AbstractMemcachedClientCrossContextSessionTest
{
    @Override
    public AbstractTestServer createServer(int port)
    {
        return new RedisTestServer(port);
    }
}
