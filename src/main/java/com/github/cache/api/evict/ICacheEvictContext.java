package com.github.cache.api.evict;

import com.github.cache.api.cache.ICache;

/**
 * @author lwj
 * @createTime 2022/5/2 0002 22:28
 * @desc 驱除策略
 */
public interface ICacheEvictContext<K,V> {
    K key();

    ICache<K,V> cache();

    int size();
}
