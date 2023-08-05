package com.github.sunzy.utils;

import java.util.Collection;

/**
 * @author sunzy
 * @date 2023/8/5 16:02
 */
public class CollectionUtil {
    public static boolean isEmpty(Collection c) {
        return c == null || c.isEmpty();
    }
}
