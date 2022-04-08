package com.xjs.mall.product.controller.web;

import com.xjs.mall.product.entity.CategoryEntity;
import com.xjs.mall.product.service.CategoryService;
import com.xjs.mall.product.vo.Catelog2Vo;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 商城-商品首页控制器
 *
 * @author xiejs
 * @since 2022-04-07
 */
@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping({"/", "index.html"})
    public String indexPage(Model model) {
        //查出所有一级分类
        List<CategoryEntity> categoryEntityList = categoryService.getLevel1Categorys();
        model.addAttribute("categorys", categoryEntityList);
        return "index";
    }

    @GetMapping("/index/json/catalog.json")
    @ResponseBody
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        return categoryService.getCatalogJson();
    }


    @ResponseBody
    @GetMapping("hello")
    public String hello() {
        //获取一把锁
        RLock lock = redissonClient.getLock("my-lock");

        //加锁
        lock.lock();    //阻塞时等待，默认假的锁是30s时间
        //锁的自动续期，如果业务超长，运行期间自动给锁续期30s，不用担心业务时间长，锁自动过期被删除
        //加锁的业务只要 运行完成，就不会给当前锁续期，即使不手动解锁，锁默认在30s以后过期


        try {
            System.out.println("加锁成功" + Thread.currentThread().getName());
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("释放锁" + Thread.currentThread().getName());
            lock.unlock();

        }
        return "hello";

    }


}

