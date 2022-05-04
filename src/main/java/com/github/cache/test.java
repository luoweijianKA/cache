package com.github.cache;

import com.github.cache.api.bs.CacheBs;
import com.github.cache.api.cache.ICache;

import java.util.concurrent.TimeUnit;

/**
 * @author lwj
 * @createTime 2022/5/2 0002 23:23
 * @desc
 */
public class test {
    public static void main(String[] args) throws InterruptedException {
        ICache<String,String> cache = CacheBs.<String,String>getInstance()
                .size(2)
                .build();
        cache.put("1" , "1");
        cache.expire("1" , 10);
        System.out.println(cache.keySet());
        TimeUnit.MILLISECONDS.sleep(50);
        System.out.println(cache.get("1"));
        System.out.println(cache.keySet());
    }
}
