package ryas.gen;

import com.ruoyi.common.datasource.mybatis.gen.MyBatisGenerationHelper;

/**
 * MyBatis代码生成
 *
 * @author Alan Scipio
 * created on 2024/2/1
 */
public class GenWms {

    public static void main(String[] args) {
        try {
            MyBatisGenerationHelper.generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
