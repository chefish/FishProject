package com.fish.lib.fishlib.util.system;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.util.List;

/**
 * Created by fish on 15/6/8.
 */
public class CopyUtil {
    /**
     * @param src 一个序列化类型的List，不能是subList
     *            注意：List.subList()返回的是一个RandomAccessSubList实例,该类型没有实现序列化
     *            如果想要要用到subList，可以这么用
     *            new ArrayList(list.subList(0, 2))
     * @return
     * @throws java.io.IOException
     * @throws ClassNotFoundException
     */
    public static List deepCopyListBySerialize(List src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        List dest = (List) in.readObject();
        return dest;
    }

    public Object deepClone(Object obj) throws IOException,
            OptionalDataException, ClassNotFoundException {
        //将对象写到流里
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(obj);
        //从流里读出来
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return (oi.readObject());
    }

}
