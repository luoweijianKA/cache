package com.github.cache.api.persist;

/**
 * @author lwj
 * @createTime 2022/5/4 0004 17:53
 * @desc
 */
public class PersistEntry<K,V> {

    /**
     * key
     * @since 0.0.8
     */
    private K key;

    /**
     * value
     * @since 0.0.8
     */
    private V value;

    /**
     * expire
     * @since 0.0.8
     */
    private Long expire;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }
}
