package com.github.cache.api.evict;

/**
 * @author lwj
 * @createTime 2022/5/2 0002 22:25
 * @desc 驱除策略
 */
public interface ICacheEvict<K,V> {
    void evict(final ICacheEvictContext<K,V> context);

//    void updateKey(final K key);
//
//    void removeKey(final K key);
}
