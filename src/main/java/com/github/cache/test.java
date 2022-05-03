package com.github.cache;

import com.github.cache.api.bs.CacheBs;
import com.github.cache.api.cache.ICache;

/**
 * @author lwj
 * @createTime 2022/5/2 0002 23:23
 * @desc
 */
public class test {
    public static void main(String[] args) {
        ICache<String,String> cache = CacheBs.<String,String>getInstance()
                .size(2)
                .build();
        cache.put("1" , "1");
        cache.put("2" , "2");
        cache.put("3" , "3");
        System.out.println(cache.keySet());
    }
}
