package com.github.sunzy.serialize.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.sunzy.remoting.dto.RpcRequest;
import com.github.sunzy.remoting.dto.RpcResponse;
import com.github.sunzy.serialize.Serializer;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author sunzy
 * date 2023-08-13
 */
@Slf4j
public class KryoSerializer implements Serializer {
    /**
     * 因为kryo是线程安全的，所以使用threadloacl 去保存 kryo 对象
     */
    private final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.register(RpcResponse.class);
        kryo.register(RpcRequest.class);
        return kryo;
    });


    @Override
    public byte[] serialize(Object obj) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             Output output = new Output(byteArrayOutputStream)) {
            Kryo kryo = kryoThreadLocal.get();
            kryo.writeObject(output, obj);
            kryoThreadLocal.remove();
            return output.toBytes();
        } catch (IOException e) {
            throw new RuntimeException("Serialization failed");
        }
    }

    /**
     * 在 deserialize 方法中，首先创建一个 ByteArrayInputStream 对象，用于读取字节数组。
     * 然后使用 Input 对象从 ByteArrayInputStream 中读取数据。
     * 接着获取当前线程的 Kryo 实例，从 Input 中反序列化出对象，
     * 并通过 clazz.cast 将其转换为指定类型，并最终返回。
     * @param bytes
     * @param clazz
     * @return
     * @param <T>
     */
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             Input input = new Input(byteArrayInputStream)) {

            Kryo kryo = kryoThreadLocal.get();
            kryo.readObject(input, clazz);
            kryoThreadLocal.remove();
            return clazz.cast(0);
        } catch (IOException e) {
            throw new RuntimeException("Deserialization failed");
        }
    }
}
