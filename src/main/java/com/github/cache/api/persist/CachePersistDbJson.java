package com.github.cache.api.persist;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.github.cache.api.cache.ICache;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lwj
 * @createTime 2022/5/4 0004 17:37
 * @desc
 */
public class CachePersistDbJson<K,V> implements ICachePersist<K,V> {
    private final String dbPath;

    public CachePersistDbJson(String dbPath) {
        this.dbPath = dbPath;
    }


    @Override
    public void persist(ICache<K, V> cache) {
        Set<Map.Entry<K, V>> entrySet = cache.entrySet();
        List<String> persistList = new ArrayList<>();
        for(Map.Entry<K,V> entry : entrySet) {
            K key = entry.getKey();
//            Long expireTime = cache.expire().expireTime(key);
            PersistEntry<K,V> persistEntry = new PersistEntry<>();
            persistEntry.setKey(key);
            persistEntry.setValue(entry.getValue());
            persistEntry.setExpire(1L);

            persistList.add(JSON.toJSONString(persistEntry));
        }
        FileUtil.writeLines(persistList , dbPath , "UTF-8");
    }
}
