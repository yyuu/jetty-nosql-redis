package org.eclipse.jetty.nosql.redis;

import org.eclipse.jetty.nosql.memcached.AbstractMemcachedLastAccessTimeTest;
import org.eclipse.jetty.server.session.AbstractTestServer;

public class LastAccessTimeTest extends AbstractMemcachedLastAccessTimeTest
{
    @Override
    public AbstractTestServer createServer(int port, int max, int scavenge)
    {
        return new RedisTestServer(port,max,scavenge);
    }
}
