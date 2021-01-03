package me.kazechin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {



    public static void main( String[] args ) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        List list = (List) Proxy.newProxyInstance(App.class.getClassLoader(), new Class[]{List.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return 0;
            }
        });

        System.out.println(list.size());

    }
}
