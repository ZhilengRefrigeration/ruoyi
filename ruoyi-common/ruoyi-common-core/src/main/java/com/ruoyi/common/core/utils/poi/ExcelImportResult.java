package com.ruoyi.common.core.utils.poi;



import java.util.List;

/**
 * Excel导入结果
 * errorList: 验证不通过的错误消息
 * result: 导入的数据
 * @author 艾纯禹
 */
public class ExcelImportResult<T> {
    private List<String> errorList;

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    private List<T> result;
}
