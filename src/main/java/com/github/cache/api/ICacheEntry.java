package com.github.cache.api;

/**
 * @author lwj
 * @createTime 2022/5/2 0002 22:32
 * @desc 缓存明细
 */
public interface ICacheEntry<K,V> {
    K key();

    V value();
}
