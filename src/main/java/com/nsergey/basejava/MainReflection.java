package com.nsergey.basejava;

import java.lang.reflect.Field;

import com.nsergey.basejava.model.Resume;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println("Field name: " + field.getName());
        System.out.println("Field value: " + field.get(r));
        field.set(r, "new_uuid");
        // TODO : invoke r.toString via reflection
        System.out.println(r);
    }
}
