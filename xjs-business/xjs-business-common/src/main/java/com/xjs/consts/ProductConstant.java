package com.xjs.consts;

/**
 * 商品常量
 * @since 2022-03-15 09:02:22
 * @author xjejs
 */
public class ProductConstant {


    public enum  AttrEnum{
        ATTR_TYPE_BASE(1,"基本属性"),ATTR_TYPE_SALE(0,"销售属性");
        private int code;
        private String msg;

        AttrEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    public enum  StatusEnum{
        NEW_SPU(0,"新建"),
        DWON_SPU(2,"商品下架"),
        UP_SPU(1,"商品上架");
        private int code;
        private String msg;

        StatusEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
