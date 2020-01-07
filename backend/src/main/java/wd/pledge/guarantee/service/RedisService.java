package wd.pledge.guarantee.service;

public interface RedisService {
    // set key-value pair
    public void set(String key, Object value);
    // get value
    public Object get(String key);
}
