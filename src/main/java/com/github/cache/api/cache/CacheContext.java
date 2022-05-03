package com.github.cache.api.cache;

import com.github.cache.api.evict.ICacheEvict;

import java.util.Map;

/**
 * @author lwj
 * @createTime 2022/5/2 0002 23:00
 * @desc
 */
public class CacheContext<K,V> implements ICacheContext<K,V> {
    private Map<K,V> map;

    private int size;

    private ICacheEvict<K,V> cacheEvict;

    @Override
    public Map map() {
        return map;
    }

    @Override
    public int size() {
        return size;
    }

    public CacheContext<K,V> size(int size) {
        this.size = size;
        return this;
    }

    public CacheContext<K,V> map(Map<K,V> map) {
        this.map = map;
        return this;
    }

    public CacheContext<K,V> cacheEvict(ICacheEvict<K,V> cacheEvict) {
        this.cacheEvict = cacheEvict;
        return this;
    }

    @Override
    public ICacheEvict cacheEvict() {
        return cacheEvict;
    }
}
