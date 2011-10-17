package org.eclipse.jetty.nosql.redis.jedis;

import java.util.List;
import java.util.ArrayList;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import org.eclipse.jetty.nosql.kvs.AbstractKeyValueStoreClient;
import org.eclipse.jetty.nosql.kvs.KeyValueStoreClientException;

public class JedisClient extends AbstractKeyValueStoreClient {
	private static final int FOREVER = 0;
	private ShardedJedis _connection = null;

	public JedisClient() {
		this("127.0.0.1:6379");
	}

	public JedisClient(String serverString) {
		super(serverString);
	}

	@Override
	public boolean establish() throws KeyValueStoreClientException {
		if (_connection != null) {
			shutdown();
		}
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		for (String s: getServerString().split(" +")) {
			String[] p = s.split(":", 2);
			String host = "127.0.0.1";
			if (0 < p.length) {
				host = p[0];
			}
			int port = 6379;
			if (1 < p.length) {
				try {
					port = Integer.parseInt(p[1]); 
				} catch (NumberFormatException error) {
					//
				}
			}
			JedisShardInfo si = new JedisShardInfo(host, port);
			shards.add(si);
		}
		this._connection = new ShardedJedis(shards);
		return true;
	}

	@Override
	public boolean shutdown() throws KeyValueStoreClientException {
		if (_connection != null) {
			_connection = null;
		}
		return true;
	}

	@Override
	public boolean isAlive() {
		return _connection != null; // TODO: check connections status
	}

	@Override
	public byte[] get(String key) throws KeyValueStoreClientException {
		if (!isAlive()) {
			throw(new KeyValueStoreClientException(new IllegalStateException("client not established")));
		}
		byte[] raw = null;
		try {
			raw = _connection.get(key).getBytes();
		} catch (Exception error) {
			throw(new KeyValueStoreClientException(error));
		}
		return raw;
	}

	@Override
	public boolean set(String key, byte[] raw) throws KeyValueStoreClientException {
		return this.set(key, raw, FOREVER);
	}

	@Override
	public boolean set(String key, byte[] raw, int exp) throws KeyValueStoreClientException {
		if (!isAlive()) {
			throw(new KeyValueStoreClientException(new IllegalStateException("client not established")));
		}
		boolean result = false;
		try {
			String f = _connection.setex(key.getBytes(), exp, raw);
			result = "OK".equals(f);
		} catch (Exception error) {
			throw(new KeyValueStoreClientException(error));
		}
		return result;
	}

	@Override
	public boolean add(String key, byte[] raw) throws KeyValueStoreClientException {
		return this.add(key, raw, FOREVER);
	}

	@Override
	public boolean add(String key, byte[] raw, int exp) throws KeyValueStoreClientException {
		if (!isAlive()) {
			throw(new KeyValueStoreClientException(new IllegalStateException("client not established")));
		}
		boolean result = false;
		try {
			long f = _connection.setnx(key.getBytes(), raw);
			result = 1 == f;
		} catch (Exception error) {
			throw(new KeyValueStoreClientException(error));
		}
		return result;
	}

	@Override
	public boolean delete(String key) throws KeyValueStoreClientException {
		if (!isAlive()) {
			throw(new KeyValueStoreClientException(new IllegalStateException("client not established")));
		}
		boolean result = false;
		try {
			long f = _connection.del(key);
			result = 1 == f;
		} catch (Exception error) {
			throw(new KeyValueStoreClientException(error));
		}
		return result;
	}
}
