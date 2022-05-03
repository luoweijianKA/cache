package com.github.cache.api.cache;

import com.github.cache.api.evict.CacheEvictContext;
import com.github.cache.api.evict.ICacheEvict;

import java.util.Collection;
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

    public Cache(CacheContext context) {
        this.map = context.map();
        this.sizeLimit = context.size();
        this.cacheEvict = context.cacheEvict();
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
}
