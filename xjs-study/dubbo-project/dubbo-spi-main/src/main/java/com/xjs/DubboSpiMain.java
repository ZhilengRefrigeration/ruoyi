package com.xjs;

import com.xjs.service.HelloService;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.Set;

/**
 * @author xiejs
 * @since 2022-05-23
 */
public class DubboSpiMain {

    public static void main(String[] args) {
        //获取扩展加载器
        ExtensionLoader<HelloService> loader = ExtensionLoader.getExtensionLoader(HelloService.class);

        //遍历所有支持的扩展点
        Set<String> extensions = loader.getSupportedExtensions();

        for (String extension : extensions) {
            HelloService helloService = loader.getExtension(extension);
            System.out.println(helloService.sayHello());
        }
    }

}
