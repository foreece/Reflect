package com.reece.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by foreece@gmail.com on 17-2-22.
 */
public class ReflectTest {
    public static void main(String[] args) {
        try {
            test1();
            test2();
            test3();
            test4();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Book中的私有属性isEBook,输出的值是构造方法传入的值true;
     * 通过field.set(),属性设置为false，并通过反射获取新的值输出，此时输出为false.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static void test1() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("test1()");
        Book book = new Book(true, 1000);
        Field isEBookField = Book.class.getDeclaredField("isEBook");
        isEBookField.setAccessible(true);
        Boolean filedValue = (Boolean) isEBookField.get(book);
        System.out.println("filedValue:"+filedValue);//true
        System.out.println("filed class name:"+filedValue.getClass().getSimpleName());//自动装箱，Boolean
        isEBookField.setBoolean(book, false);
        Boolean newFiledValue = (Boolean) isEBookField.get(book);
        System.out.println("newFiledValue:"+newFiledValue);//false
    }

    /**
     * 构造方法传入prize为1000，通过反射调用私有方法setPrize(float),
     * 从而修改了prize的值为0.1，然后反射得到修改后的私有属性prize输出，为0.1
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private static void test2() throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, NoSuchFieldException {
        System.out.println("\ntest2()");
        Book book = new Book(true, 1000);
        //调用私有的方法
        Method method = Book.class.getDeclaredMethod("setPrize", float.class);
        method.setAccessible(true);
        method.invoke(book, 0.1f);
        //通过反射拿到prize
        Field prizeField = Book.class.getDeclaredField("prize");
        prizeField.setAccessible(true);
        Float newPrize = (Float) prizeField.get(book);
        System.out.println("newPrize:"+newPrize);//0.1
    }

    /**
     * 调用Book的私有构造方法，设置参数值，最后输出
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    private static void test3() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println("\ntest3()");
        Constructor<Book> constructor = Book.class.getDeclaredConstructor(boolean.class, float.class, int.class);
        constructor.setAccessible(true);
        Book book = constructor.newInstance(true, 100, 5);
        System.out.println("book:"+book.toString());
    }

    /**
     * 获取父类的属性
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void test4() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("\ntest4()");
        SubBook subBook = new SubBook(true, 10.0f);
        Field prizeField = SubBook.class.getSuperclass().getDeclaredField("prize");
        prizeField.setAccessible(true);
        Object prize = prizeField.get(subBook);
        System.out.println("prize:"+prize);//10.0
    }
}
