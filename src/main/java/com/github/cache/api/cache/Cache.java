package com.github.cache.api.cache;

import cn.hutool.core.collection.CollUtil;
import com.github.cache.api.evict.CacheEvictContext;
import com.github.cache.api.evict.ICacheEvict;
import com.github.cache.api.expire.CacheExpire;
import com.github.cache.api.expire.ICacheExpire;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author lwj
 * @createTime 2022/5/2 0002 23:09
 * @desc
 */
public class Cache<K,V> implements ICache<K,V>{
    private final Map<K,V> map;

    private final int sizeLimit;

    private final ICacheEvict<K,V> cacheEvict;

    private final ICacheExpire<K,V> cacheExpire;

    public Cache(CacheContext context) {
        this.map = context.map();
        this.sizeLimit = context.size();
        this.cacheEvict = context.cacheEvict();
        this.cacheExpire = new CacheExpire<>(this);
    }


    @Override
    public ICache<K, V> expire(K key, long timeInMills) {
        long expireTime = System.currentTimeMillis() + timeInMills;
        return this.expireAt(key , expireTime);
    }

    @Override
    public ICache<K, V> expireAt(K key, long timeInmMlls) {
        this.cacheExpire.expire(key , timeInmMlls);
        return this;
    }



    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        K genericKey = (K) key;
        this.cacheExpire.refreshExpire(Collections.singletonList(genericKey));
        return map.get(key);
    }

    @Override
    public V put(K key, V value) {
        // 尝试驱除
        CacheEvictContext<K,V> context = new CacheEvictContext<>();
        context.key(key)
                .cache(this)
                .size(sizeLimit);
        cacheEvict.evict(context);
        // 驱除后仍无法装入，抛异常
        if (isSizeLimit()) {
            throw new RuntimeException("当前队列已满，数据添加失败");
        }
        return map.put(key , value);
    }

    private boolean isSizeLimit() {
        return this.size() >= this.sizeLimit;
    }

    @Override
    public V remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<K> keySet() {
        this.refreshExpireAllKeys();
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    /**
     * 刷新懒过期处理所有的 keys
     * @since 0.0.3
     */
    private void refreshExpireAllKeys() {
        this.cacheExpire.refreshExpire(map.keySet());
    }
}
