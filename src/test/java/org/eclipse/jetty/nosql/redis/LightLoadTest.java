package org.eclipse.jetty.nosql.redis;

import org.eclipse.jetty.nosql.memcached.AbstractMemcachedLightLoadTest;
import org.eclipse.jetty.server.session.AbstractTestServer;

public class LightLoadTest extends AbstractMemcachedLightLoadTest
{
    @Override
    public AbstractTestServer createServer(int port)
    {
        return new RedisTestServer(port);
    }
}
