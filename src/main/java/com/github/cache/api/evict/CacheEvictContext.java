package com.github.cache.api.evict;

import com.github.cache.api.cache.CacheContext;
import com.github.cache.api.cache.ICache;

/**
 * @author lwj
 * @createTime 2022/5/2 0002 23:13
 * @desc
 */
public class CacheEvictContext<K,V> implements ICacheEvictContext{
    private K key;

    private ICache<K,V> cache;

    private int size;


    @Override
    public Object key() {
        return key;
    }

    public CacheEvictContext<K,V> key(K key) {
        this.key = key;
        return this;
    }

    @Override
    public ICache cache() {
        return cache;
    }
    public CacheEvictContext<K,V> cache(ICache<K,V> cache) {
        this.cache = cache;
        return this;
    }

    @Override
    public int size() {
        return size;
    }

    public CacheEvictContext<K,V> size(int size) {
        this.size = size;
        return this;
    }


}
