package com.github.cache.api.evict;

/**
 * @author lwj
 * @createTime 2022/5/2 0002 22:42
 * @desc 丢弃策略
 */
public final class CacheEvicts {
    private CacheEvicts(){};

    /**
     * 先进先出驱除策略
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> ICacheEvict<K,V> fifo() {
        return new CacheEvictFIFO<>();
    }
}
