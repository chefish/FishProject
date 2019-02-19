package com.example.fish.serialdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * 序列化demo
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testSerialObject();
        try {
            testSerialArray();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void testSerialArray() throws IOException, ClassNotFoundException {
        Professor p1=new Professor("wangwu",50);
        Student s1=new Student("zhangsan",18,p1);

        Professor p2=new Professor("ww",60);
        Student s2=new Student("lisi",20,p2);


        ArrayList<Student> ss=new ArrayList<>();
        ss.add(s1);
        ss.add(s2);

        //将对象写到流里
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(ss);
        //从流里读出来
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        List dest = (List)oi.readObject();
        int x=10;
        x++;

    }

    private void testSerialObject() {
        Professor p=new Professor("wangwu",50);
        Student s1=new Student("zhangsan",18,p);
        Student s2= null;
        try {
            s2 = (Student)s1.deepClone();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        s2.p.name="lisi";
        s2.p.age=30;
        System.out.println("name="+s1.p.name+","+"age="+s1.p.age); //学生1的教授不改变。

    }


}
