package com.github.sunzy.utils;

/**
 * @author sunzy
 * @date 2023/8/5 16:03
 */
public class RuntimeUtil {
    public static int cpus() {
        return Runtime.getRuntime().availableProcessors();
    }
}
