package com.github.cache.api.cache;

import com.github.cache.api.evict.ICacheEvict;

import java.util.Map;

/**
 * @author lwj
 * @createTime 2022/5/2 0002 22:58
 * @desc
 */
public interface ICacheContext<K,V> {
    Map<K,V> map();

    int size();

    ICacheEvict<K,V> cacheEvict();
}
