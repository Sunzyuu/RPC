package com.github.test.serializtion;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author sunzy
 * @date 2023/8/2 10:15
 */
public class KryoSerialize {
    public static void main(String[] args) throws FileNotFoundException {
        Kryo kryo = new Kryo();
        kryo.register(SomeClass.class);
        SomeClass object = new SomeClass();
        object.value = "hello kryo";
        Output output = new Output(new FileOutputStream("E:\\Sunzh\\java\\maven\\RPC\\src\\main\\resources\\bin"));

        kryo.writeObject(output, object);
        output.close();
        Input input = new Input(new FileInputStream("E:\\Sunzh\\java\\maven\\RPC\\src\\main\\resources\\bin"));
        SomeClass object2 = kryo.readObject(input, SomeClass.class);
        input.close();
        System.out.println(object2.value);
    }

    static public class SomeClass {
        String value;
    }

}
