package com.nsergey.basejava;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.nsergey.basejava.model.Resume;

public class MainReflection {

    public static void main(String[] args)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println("Field name: " + field.getName());
        System.out.println("Field value: " + field.get(r));
        field.set(r, "new_uuid");

        // invocation r.toString() via reflection
        Method toStringMethod = r.getClass().getDeclaredMethod("toString");
        System.out.println(toStringMethod.invoke(r));
    }
}
