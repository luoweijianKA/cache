package com.github.cache.api.expire;

import java.util.Collection;

/**
 * @author lwj
 * @createTime 2022/5/3 0003 22:34
 * @desc
 */
public interface ICacheExpire<K,V> {
    /**
     * 指定时间过期
     * @param key
     * @param expireAt
     */
    void expire(final K key , final long expireAt);

    void refreshExpire(Collection<K> keyList);
}
