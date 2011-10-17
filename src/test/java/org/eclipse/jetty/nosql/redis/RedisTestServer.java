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

import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.nosql.memcached.MemcachedTestServer;
import org.eclipse.jetty.nosql.redis.RedisSessionIdManager;
import org.eclipse.jetty.nosql.redis.RedisSessionManager;
import org.eclipse.jetty.server.SessionIdManager;
import org.eclipse.jetty.server.SessionManager;

/**
 * @version $Revision$ $Date$
 */
public class RedisTestServer extends MemcachedTestServer
{
    public RedisTestServer(int port)
    {
        super(port);
    }

    public RedisTestServer(int port, int maxInactivePeriod, int scavengePeriod)
    {
        super(port, maxInactivePeriod, scavengePeriod);
    }
    
    
    public RedisTestServer(int port, int maxInactivePeriod, int scavengePeriod, boolean saveAllAttributes)
    {
        super(port, maxInactivePeriod, scavengePeriod, saveAllAttributes);
    }

    @Override
    public SessionIdManager newSessionIdManager(String config)
    {
        if (config == null) {
            config = "127.0.0.1:6379";
        }
        if ( _idManager != null )
        {
            try
            {
                _idManager.stop();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
            _idManager.setScavengePeriod((int) TimeUnit.SECONDS.toMillis(_scavengePeriod));
            _idManager.setWorkerName("node0");
            
            try
            {
                _idManager.start();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
            return _idManager;
        }
        
        try
        {
            System.err.println("RedisTestServer:SessionIdManager:" + _maxInactivePeriod + "/" + _scavengePeriod);
            _idManager = new RedisSessionIdManager(_server, config);
            
            _idManager.setScavengePeriod((int)TimeUnit.SECONDS.toMillis(_scavengePeriod));
            _idManager.setKeyPrefix("RedisTestServer::");
            
            return _idManager;
        }
        catch (Exception e)
        {
            throw new IllegalStateException();
        }
    }

    public SessionManager newSessionManager()
    {
        RedisSessionManager manager;
        try
        {
            manager = new RedisSessionManager();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        
        manager.setSavePeriod(1);
        manager.setStalePeriod(0);
        manager.setSaveAllAttributes(_saveAllAttributes);
        //manager.setScavengePeriod((int)TimeUnit.SECONDS.toMillis(_scavengePeriod));
        return manager;
    }
}
