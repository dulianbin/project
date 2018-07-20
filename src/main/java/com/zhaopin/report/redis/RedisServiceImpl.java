package com.zhaopin.report.redis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaopin.report.util.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service(value = "redisService")
public class RedisServiceImpl implements RedisService{
	private static final Logger logger = Logger.getLogger(RedisServiceImpl.class);
	@Autowired
	private JedisPool jedisPool;
	
	public Jedis getJedis(JedisPool jedisPool) {
		Jedis jedis = jedisPool.getResource();
		jedis.select(4);
		return jedis;
	}
	public void returnResource(Jedis jedis) {
		jedisPool.returnResource(jedis);
	}

	public void returnBrokenResource(Jedis jedis) {
		jedisPool.returnBrokenResource(jedis);
	}
	
	
	public void setString(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			jedis.set(key, value);
		} catch (Exception e) {
			logger.error("调用setString key="+key+",value="+value+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
	}
	
	
	public String getString(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			return jedis.get(key);
		} catch (Exception e) {
			logger.error("调用getString key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
			return null;
		}finally {
			returnResource(jedis);
		}
	}
	
	public long rpushList(String key,byte[] value) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			return jedis.rpush(key.getBytes(), value);
		} catch (Exception e) {
			logger.error("调用rpushList(保存byte数组时) key="+key+"value="+value+"时异常:"+e);
			returnBrokenResource(jedis);
			return 0;
		}finally {
			returnResource(jedis);
		}
	}
	
	public List<byte[]> lrangeList(String key, int start, int end) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			return jedis.lrange(key.getBytes(),start,end);
		} catch (Exception e) {
			logger.error("调用lrangeList key="+key+"start="+start+"end="+end+"时异常:"+e);
			returnBrokenResource(jedis);
			return null;
		}finally {
			returnResource(jedis);
		}
	}
	
	public void setString(String key, byte[] value) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			jedis.set(key.getBytes(), value);
		} catch (Exception e) {
			logger.error("调用setString(保存byte数组时) key="+key+",value="+value+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
	}

	
	public byte[] getbyteString(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			return jedis.get(key.getBytes());
		} catch (Exception e) {
			logger.error("调用getbyteString key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
			return null;
		}finally {
			returnResource(jedis);
		}
	}

	
	public <T> List<T> getList(String key) {
		try {
			byte[] bytes = getbyteString(key);
			List<T> list = SerializeUtil.unserializeList(bytes);
			return list;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public <T> T pushObjectToList(String key, T t) {
		rpushList(key, SerializeUtil.serialize(t));
		return null;
	}
	
	public <T> List<T> lrangeObjectList(String key, int start, int end) {
		List<T> tlist = null;
		List<byte[]> bytelist = lrangeList(key, start, end);
		if(null!=bytelist)
		{
			tlist = new ArrayList<T>();
			for (byte[] bs : bytelist) {
	    		@SuppressWarnings("unchecked")
				T t = (T) SerializeUtil.unserialize(bs);
	    		tlist.add(t);
			}
		}
		
		return tlist;
	}
	
	public <T> T setList(String key, List<T>list) {
		setString(key, SerializeUtil.serializeList(list));
		return null;
	}
	
	public <T> T setObject(String key, T t) {
		setString(key, SerializeUtil.serialize(t));
		return null;
	}
	
	public <T> T getObject(String key) {
		byte[] bytes = getbyteString(key);
		if(bytes==null)
		{
			return null;
		}
		@SuppressWarnings("unchecked")
		T t = (T) SerializeUtil.unserialize(bytes);
		return t;
	}

	/**
	 * 设置指定秒就失效的string值</br>
	 * @param key
	 * @param value
	 * @param seconds 有效时间，单位：秒
	 */
	
	public void setex(String key, String value, int seconds) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
			logger.error("调用setex key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
	}
	
	public void delKey(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			jedis.del(key);
		} catch (Exception e) {
			logger.error("调用delKey key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
	}
	
	public void lpushNum(String key, float df) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			jedis.lpush(key, df+"");
		} catch (Exception e) {
			logger.error("调用delKey key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
	}
	
	public List<String> lrangeDFList(String key) {
		Jedis jedis = null;
		List<String> list=null;
		try {
			jedis = getJedis(jedisPool);
			list = jedis.lrange(key, 0, -1);
		} catch (Exception e) {
			logger.error("调用delKey key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
		return list;
	}
	
	public void setString(String key, float value) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			jedis.set(key, value+"");
		} catch (Exception e) {
			logger.error("调用setString key="+key+",value="+value+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
	}
	
	public float getString_float(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			return jedis.get(key)==null?-1:Float.valueOf(jedis.get(key));
		} catch (Exception e) {
			logger.error("调用getString key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
			return -1;
		}finally {
			returnResource(jedis);
		}
	}
	
	public void hset(String key, String field, float value) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			jedis.hset(key, field, String.valueOf(value));
		} catch (Exception e) {
			logger.error("调用hset key="+key+",field="+field+",value="+value+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
	}
	
	public Map<String, String> hgetAllMap(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			return jedis.hgetAll(key);
		} catch (Exception e) {
			logger.error("调用hgetAllFloatMap key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
			return null;
		}finally {
			returnResource(jedis);
		}
	}
	
	public void zdd(String key, String member, double score) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			jedis.zadd(key, score, member);
		} catch (Exception e) {
			logger.error("调用zdd key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
	}
	
	public String zrangeSortSET(String key, long start, long end) {
		Jedis jedis = null;
		String result =null;
		try {
			jedis = getJedis(jedisPool);
			Set<String> set = jedis.zrange(key, start, end);
			Iterator<String> interator = set.iterator();
			while(interator.hasNext())
			{
				result = interator.next();
			}
		} catch (Exception e) {
			logger.error("调用zdd key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
			result = null;
		}finally {
			returnResource(jedis);
		}
		return result;
	}
	
	public int hget(String key, String field) {
		Jedis jedis = null;
		int resultcode = 0;
		try {
			jedis = getJedis(jedisPool);
			String result = jedis.hget(key, field);
			if(result!=null)
			{
				double xx = Double.valueOf(result);
				resultcode = (int)xx;
			}
		} catch (Exception e) {
			logger.error("调用hget key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
		return resultcode;
	}
	
	public void hset(String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			jedis.hset(key, field, value);
		} catch (Exception e) {
			logger.error("调用hset key="+key+",field="+field+",value="+value+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
	}
	
	public List<String> hmget(String key, String... fields) {
		Jedis jedis = null;
		List<String> list=null;
		try {
			jedis = getJedis(jedisPool);
			list = jedis.hmget(key, fields);
		} catch (Exception e) {
			logger.error("调用hmget key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
		return list;
	}
	
	public void lpushNum(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			jedis.lpush(key, value);
		} catch (Exception e) {
			logger.error("调用delKey key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
	}
	
	public void rpush(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			jedis.rpush(key, value);
		} catch (Exception e) {
			logger.error("调用rpush key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
	}
	
	public void lremListValue(String key, int count, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			jedis.lrem(key, count, value);
		} catch (Exception e) {
			logger.error("调用lrem key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
	}
	
	public void saddSetValue(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			jedis.sadd(key, value);
		} catch (Exception e) {
			logger.error("调用sadd key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
	}
	
	public String spop(String key) {
		Jedis jedis = null;
		String resultCode=null;
		try {
			jedis = getJedis(jedisPool);
			resultCode = jedis.spop(key);
			jedis.del(key);
		} catch (Exception e) {
			logger.error("调用spop key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
		return resultCode;
	}
	
	public double zcore(String key, String member) {
		Jedis jedis = null;
		Double resultCode=0.0;
		try {
			jedis = getJedis(jedisPool);
			resultCode = jedis.zscore(key, member);
			if(resultCode==null)
			{
				resultCode = 0.0;
			}
		} catch (Exception e) {
			logger.error("调用spop key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
		return resultCode;
	}
	
	public String rpoplpush(String srckey, String deskey) {
		Jedis jedis = null;
		String resultCode=null;
		try {
			jedis = getJedis(jedisPool);
			resultCode = jedis.rpoplpush(srckey, deskey);
		} catch (Exception e) {
			logger.error("调用rpoplpush srckey="+srckey+"deskey="+deskey+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
		return resultCode;
	}
	
	public int getSortSet(String key) {
		Jedis jedis = null;
		int  result =0;
		try {
			jedis = getJedis(jedisPool);
			Set<String> set = jedis.zrange(key, 0, -1);
			result = set.size();
		} catch (Exception e) {
			logger.error("调用zdd key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
			result = 0;
		}finally {
			returnResource(jedis);
		}
		return result;
	}
	
	public List<String> zrangeSortSet(String key, long start, long stop) {
		Jedis jedis = null;
		String result =null;
		List<String> list = new ArrayList<String>();
		try {
			jedis = getJedis(jedisPool);
			Set<String> set = jedis.zrange(key, start, stop);
			Iterator<String> interator = set.iterator();
			while(interator.hasNext())
			{
				result = interator.next();
				list.add(result);
			}
		} catch (Exception e) {
			logger.error("调用zdd key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
			result = null;
		}finally {
			returnResource(jedis);
		}
		return list;
	}
	
	public void expireKey(String key, int seconds) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			jedis.expire(key, seconds);
		} catch (Exception e) {
			logger.error("调用expireKey key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
	}
	
	public Set<String> smembers(String key) {
		Jedis jedis = null;
		Set<String> set = null;
		try {
			jedis = getJedis(jedisPool);
			set = jedis.smembers(key);
		} catch (Exception e) {
			logger.error("调用smembers key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
		return set;
	}
	
	public boolean sismember(String key, String member) {
		Jedis jedis = null;
		boolean flag = false;
		try {
			jedis = getJedis(jedisPool);
			flag = jedis.sismember(key, member);
		} catch (Exception e) {
			logger.error("调用smembers key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
		return flag;
	}
	
	public void srem(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = getJedis(jedisPool);
			jedis.srem(key, member);
		} catch (Exception e) {
			logger.error("调用srem key="+key+"时异常:"+e);
			returnBrokenResource(jedis);
		}finally {
			returnResource(jedis);
		}
	}
}
