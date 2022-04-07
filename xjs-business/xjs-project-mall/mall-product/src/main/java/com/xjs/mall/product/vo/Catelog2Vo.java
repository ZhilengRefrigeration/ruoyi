package com.xjs.mall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 2级分类vo
 *
 * @author xiejs
 * @since 2022-04-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catelog2Vo {
    /**
     * 1级
     */
    private String catalog1Id;

    /**
     * 3级子分类
     */
    private List<Catelog3Vo> catalog3List;


    /**
     * id
     */
    private String id;

    /**
     * 分类名称
     */
    private String name;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Catelog3Vo{
        /**
         * 父分类，2级分类id
         */
        private String catalog2Id;

        private String id;

        private String name;

    }

}
