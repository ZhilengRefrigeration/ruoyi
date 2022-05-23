package com.xjs;

import com.xjs.service.HelloService;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @author xiejs
 * @since 2022-05-23
 */
public class DubboAdaptiveMain {

    public static void main(String[] args) {
        URL url = URL.valueOf("test://localhost/hello?hello.service=dog");

        HelloService helloService = ExtensionLoader.getExtensionLoader(HelloService.class).getAdaptiveExtension();


        String s = helloService.sayHello(url);

        System.out.println(s);


    }

}
