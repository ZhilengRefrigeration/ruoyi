package com.xjs.consts;

/**
 * 工作流常用常量
 * @author xiejs
 * @since 2022-04-18
 */
public class WorkflowConst {

    public enum WorkflowEnum {
        ING(0,"进行中"),
        SUCCESS(1,"成功"),
        ERROR(2,"失败");

        private Integer code;
        private String desc;

        WorkflowEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


}
