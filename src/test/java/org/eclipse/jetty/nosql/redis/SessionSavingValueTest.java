package org.eclipse.jetty.nosql.redis;

import org.eclipse.jetty.nosql.memcached.AbstractMemcachedSessionSavingValueTest;
import org.eclipse.jetty.server.session.AbstractTestServer;

public class SessionSavingValueTest extends AbstractMemcachedSessionSavingValueTest
{
    @Override
    public AbstractTestServer createServer(int port, int max, int scavenge)
    {
        return new RedisTestServer(port,max,scavenge,true);
    }
}
