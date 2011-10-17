package org.eclipse.jetty.nosql.redis;

import org.eclipse.jetty.nosql.memcached.AbstractMemcachedOrphanedSessionTest;
import org.eclipse.jetty.server.session.AbstractTestServer;

public class OrphanedSessionTest extends AbstractMemcachedOrphanedSessionTest
{
    @Override
    public AbstractTestServer createServer(int port, int max, int scavenge)
    {
       return new RedisTestServer(port,max,scavenge);
    }
}
