package com.github.cache.api.persist;

import com.github.cache.api.cache.ICache;

/**
 * @author lwj
 * @createTime 2022/5/4 0004 17:36
 * @desc
 */
public interface ICachePersist<K,V> {
    void persist(final ICache<K,V> cache);
}
