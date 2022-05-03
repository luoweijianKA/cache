package com.github.cache.api.evict;

import com.github.cache.api.cache.ICache;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lwj
 * @createTime 2022/5/2 0002 22:26
 * @desc 基于LinkedList实现的FIFO缓存
 */
public class CacheEvictFIFO<K,V> implements ICacheEvict<K,V> {
    private Queue<K> queue = new LinkedList<>();

    @Override
    public void evict(ICacheEvictContext<K, V> context) {
        final ICache<K,V> cache = context.cache();
        if (cache.size() >= context.size()) {
            K evictKey = queue.remove();
            cache.remove(evictKey);
        }

        final K key = context.key();
        queue.add(key);
    }
}
