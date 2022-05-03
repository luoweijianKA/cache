package com.github.cache.api.bs;

import com.github.cache.api.cache.Cache;
import com.github.cache.api.cache.CacheContext;
import com.github.cache.api.cache.ICache;
import com.github.cache.api.evict.CacheEvicts;
import com.github.cache.api.evict.ICacheEvict;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author lwj
 * @createTime 2022/5/2 22:22
 * @desc 缓存引导类
 */
public final class CacheBs<K,V> {
    // 单例
    private CacheBs(){}

    /**
     * 对象实例化
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> CacheBs<K,V> getInstance(){
        return new CacheBs();
    }

    private Map<K,V> map = new HashMap<>();


    private int size = Integer.MAX_VALUE;

    private ICacheEvict<K,V> evict = CacheEvicts.fifo();

    public CacheBs<K,V> map(Map<K,V> map) {
        Optional.ofNullable(map).orElseThrow(() -> new IllegalArgumentException("map is null"));
        this.map = map;
        return this;
    }


    public CacheBs<K,V> size(int size) {
        Optional.ofNullable(size).filter(u -> size > 0).orElseThrow(() -> new IllegalArgumentException("size can not less than or equal 0."));
        this.size = size;
        return this;
    }

    public CacheBs<K,V> evict(ICacheEvict<K,V> evict) {
        this.evict = evict;
        return this;
    }

    public ICache<K,V> build() {
        CacheContext<K,V> context = new CacheContext<>();
        context.cacheEvict(evict)
                .map(map)
                .size(size);
        return new Cache<>(context);
    }

}
