package com.ruoyi.common.datasource.mybatis.gen;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.List;

/**
 * @author Alan Scipio
 * created on 2024/2/6
 */
public class RyasMyBatisDynamicPlugin extends PluginAdapter {

    // 校验插件配置的正确性
    @Override
    public boolean validate(List<String> warnings) {
        // 插件使用前提是targetRuntime为MyBatis3DynamicSql
        if (StringUtility.stringHasValue(context.getTargetRuntime()) && !"MyBatis3DynamicSql".equalsIgnoreCase(context.getTargetRuntime())) {
            warnings.add("Ryas MyBatisDynamic Plugin: " + this.getClass().getTypeName() + "Required targetRuntime must be 'MyBatis3DynamicSql' !");
            return false;
        }
        return true;
    }

    // int insert(UnitInfo row)
    @Override
    public boolean clientInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        setForInsert(method, interfaze);
        return true;
    }

    // int insertMultiple(Collection<UnitInfo> records)
    @Override
    public boolean clientInsertMultipleMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        setForInsertMultiple(method, interfaze);
        return true;
    }

    // int insertSelective(UnitInfo row)
    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        setForInsert(method, interfaze);
        return true;
    }

    // UpdateDSL<UpdateModel> updateSelectiveColumns(UnitInfo row, UpdateDSL<UpdateModel> dsl)
    @Override
    public boolean clientUpdateSelectiveColumnsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        setForUpdate(method, interfaze);
        return true;
    }

    // int updateByPrimaryKey(UnitInfo row)
    @Override
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        setForUpdate(method, interfaze);
        return true;
    }

    // int updateByPrimaryKeySelective(UnitInfo row)
    @Override
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        setForUpdate(method, interfaze);
        return true;
    }

    //===============================================================================================================================

    private void setForInsert(Method method, Interface interfaze) {
        addSecurityUtilsImport(interfaze);
        List<String> bodyLines = method.getBodyLines();
        bodyLines.addFirst("row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());");
    }

    private void setForInsertMultiple(Method method, Interface interfaze) {
        addSecurityUtilsImport(interfaze);
        List<String> bodyLines = method.getBodyLines();
        //从头插入，所以需要倒着
        bodyLines.addFirst("}");
        bodyLines.addFirst("row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());");
        bodyLines.addFirst("for (UnitInfo row : records) {");
    }

    private void setForUpdate(Method method, Interface interfaze) {
        addSecurityUtilsImport(interfaze);
        List<String> bodyLines = method.getBodyLines();
        bodyLines.addFirst("row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());");
    }

    private void addSecurityUtilsImport(Interface interfaze) {
        interfaze.addImportedType(new FullyQualifiedJavaType("com.ruoyi.common.security.utils.SecurityUtilsExt"));
    }

}
