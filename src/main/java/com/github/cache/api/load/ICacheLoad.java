package com.github.cache.api.load;

import com.github.cache.api.cache.ICache;

/**
 * @author lwj
 * @createTime 2022/5/4 0004 17:34
 * @desc
 */
public interface ICacheLoad<K,V> {

    void load(final ICache<K,V> cache);
}
