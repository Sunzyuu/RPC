package com.github.sunzy.serialize;

import com.github.sunzy.extension.SPI;

/**
 * @author sunzy
 * date 2023-08-13
 */
@SPI
public interface Serializer {
    /**
     * serializes
     * @param obj
     * @return
     */
    byte[] serialize(Object obj);

    /**
     * deserializes
     * @param bytes
     * @param clazz
     * @return
     * @param <T>
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz);
}
