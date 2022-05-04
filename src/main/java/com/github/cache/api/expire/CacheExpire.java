package com.github.cache.api.expire;

import cn.hutool.core.collection.CollUtil;
import com.github.cache.api.cache.ICache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author lwj
 * @createTime 2022/5/3 0003 22:37
 * @desc
 */
public class CacheExpire<K,V> implements ICacheExpire<K,V>{
    /**
     * 配置值，每次定期删除的键数量
     */
    private static final int LIMIT = 100;

    private final ICache<K,V> cache;

    /**
     * 空间换时间，存下过期的key，定期删除
     */
    private final Map<K,Long> expireMap = new HashMap<>();

    public CacheExpire(ICache<K, V> cache) {
        this.cache = cache;
        this.init();
    }

    @Override
    public void expire(K key, long expireAt) {
        expireMap.put(key , expireAt);
    }


    @Override
    public void refreshExpire(Collection<K> keyList) {
        if (CollUtil.isEmpty(keyList)) {
            return;
        }
        if (keyList.size() <= expireMap.size()) {
            for(K key : keyList) {
                expireKey(key);
            }
        } else {
            for (Map.Entry<K,Long> entry :expireMap.entrySet()) {
                this.expireKey(entry);
            }

        }
    }

    private class ExpireThread implements Runnable {

        @Override
        public void run() {
            if (CollUtil.isEmpty(expireMap)) {
                return;
            }
            int haveDealCnt = 0;
            for (Map.Entry<K,Long> entry : expireMap.entrySet()) {
                if (haveDealCnt >= LIMIT) {
                    return;
                }
                expireKey(entry);
                haveDealCnt++;
            }
        }
    }

    private static final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();

    private void init() {
        EXECUTOR_SERVICE.scheduleAtFixedRate(new ExpireThread() , 100 , 100 , TimeUnit.MILLISECONDS);
    }

    private void expireKey(Map.Entry<K,Long> entry) {
        final K key = entry.getKey();
        final Long expireAt =entry.getValue();

        long currentTime = System.currentTimeMillis();
        if (currentTime >= expireAt) {
            expireMap.remove(key);
            cache.remove(key);
        }
    }

    private void expireKey(final K key) {
        Long expireAt = expireMap.get(key);
        if (expireAt == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis >= expireAt) {
            expireMap.remove(key);
            cache.remove(key);
        }
    }
}
