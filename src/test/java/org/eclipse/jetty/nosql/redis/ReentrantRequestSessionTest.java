package org.eclipse.jetty.nosql.redis;

import org.eclipse.jetty.nosql.memcached.AbstractMemcachedReentrantRequestSessionTest;
import org.eclipse.jetty.server.session.AbstractTestServer;

public class ReentrantRequestSessionTest extends AbstractMemcachedReentrantRequestSessionTest
{
    @Override
    public AbstractTestServer createServer(int port)
    {
        return new RedisTestServer(port);
    }
}
