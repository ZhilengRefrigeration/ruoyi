package com.xjs.area.factory;

import com.xjs.XjsOpenApiApp;
import com.xjs.area.domain.Area;
import com.xjs.weather.domain.RequestBody;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author xiejs
 * @since 2022-03-22
 */
@SpringBootTest(classes = XjsOpenApiApp.class)
class GaodeAreaFactoryImplTest {


    @Autowired
    private AreaFactory<List<Area>, RequestBody> areaFactory;

    @Test
    void getArea() {
        List<Area> area = areaFactory.getArea(new RequestBody());
        System.out.println(area);
    }
}
