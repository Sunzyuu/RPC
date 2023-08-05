package com.github.sunzy.extension;

/**
 * @author sunzy
 * @date 2023/8/5 16:43
 */
public class Holder<T> {
    private volatile T value;

    public  T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}
