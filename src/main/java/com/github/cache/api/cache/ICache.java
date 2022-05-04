package com.github.cache.api.cache;

import java.util.Collection;
import java.util.Map;

/**
 * @author lwj
 * @createTime 2022/5/2 22:10
 * @desc 缓存接口
 */
public interface ICache<K,V> extends Map<K,V> {
    /**
     * 多少ms后过期
     * @param key
     * @param timeInMills
     * @return
     */
    ICache<K,V> expire(final K key , final long timeInMills);

    /**
     * 指定时间内过去
     * @param key
     * @param timeInMills
     * @return
     */
    ICache<K,V> expireAt(final K key , final long timeInMills);


}
