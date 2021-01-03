package me.kazechin;

import static org.junit.Assert.assertTrue;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void tesCglib() {

        for(int i=0; i<10000; i++) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(List.class);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return 0;
                }
            });

            List list = (List) enhancer.create();
            System.out.println(list.size());
        }

    }

    @Test
    public void testProxy() {
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
