package com.xjs.oneenglish.factory;

import com.xjs.XjsOpenApiApp;
import com.xjs.config.TianXingProperties;
import com.xjs.copywriting.domain.CopyWriting;
import com.xjs.copywriting.mapper.CopyWritingMapper;
import com.xjs.copywriting.service.CopyWritingService;
import com.xjs.oneenglish.domain.ApiEnglish;
import com.xjs.oneenglish.domain.RequestBody;
import com.xjs.oneenglish.factory.impl.TianXingOneEnglishFactory;
import com.xjs.weather.domain.IPInfoVo;
import com.xjs.weather.factory.impl.SpeedTestIPFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-31
 */
@SpringBootTest(classes = XjsOpenApiApp.class)
class TianXingOneEnglishFactoryTest {

    @Autowired
    TianXingOneEnglishFactory tianXingOneEnglishFactory;

    @Autowired
    TianXingProperties tianXingProperties;

    @Autowired
    private CopyWritingService copyWritingService;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private SpeedTestIPFactory speedTestIPFactory;

    @Test
    void getOneEnglish() {
        RequestBody requestBody = new RequestBody();
        requestBody.setKey(tianXingProperties.getKey());
        ApiEnglish oneEnglish = tianXingOneEnglishFactory.getOneEnglish(requestBody);
        System.out.println(oneEnglish);
    }

    @Test
    void ip() {
        IPInfoVo ipInfoVo = speedTestIPFactory.ipApi();
        System.out.println(ipInfoVo.toString());
    }

    @Test
    void insert() {
        /*CopyWriting copyWriting = new CopyWriting();
        copyWriting.setSource("xx");
        copyWriting.setContent("既然看不到早上的夕阳，那就追求傍晚到日落。");
        copyWriting.setType(1);
        for (int i = 0; i < 100000; i++) {
            copyWritingService.save(copyWriting);
        }*/

        /*ArrayList<CopyWriting> copyWritings = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            CopyWriting copyWriting = new CopyWriting();
            copyWriting.setSource("xx");
            copyWriting.setContent("既然看不到早上的夕阳，那就追求傍晚到日落。");
            copyWriting.setType(1);
            copyWritings.add(copyWriting);
        }
        copyWritingService.saveBatch(copyWritings,30000);*/
        SqlSession sqlSession=null;
        try {
            sqlSession= sqlSessionFactory.openSession(ExecutorType.BATCH);

            CopyWritingMapper mapper = sqlSession.getMapper(CopyWritingMapper.class);

            CopyWriting copyWriting = new CopyWriting();
            copyWriting.setSource("xx");
            copyWriting.setContent("既然看不到早上的夕阳，那就追求傍晚到日落。");
            copyWriting.setType(1);

            for (int i = 0; i < 100000; i++) {
                mapper.insert(copyWriting);
            }

            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

    }
}