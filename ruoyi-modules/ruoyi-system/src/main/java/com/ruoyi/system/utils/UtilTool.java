package com.ruoyi.system.utils;


import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <B>系统名称：CAAS系统</B><BR>
 * <B>模块名称：MATERIAL</B><BR>
 * <B>中文类名：工具类</B><BR>
 * <B>概要说明：</B><BR>
 * <B>@version：v1.0</B><BR>
 * <B>版本 修改人 备注</B><BR>
 * @author : YANGWENYE
 * @date : 2018年4月25日
 */
public class UtilTool {
    /**
     * 初始化一次
     */
  /*  static Validator validator = null;
    static {
        validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
    }*/

    private static Pattern chinesePattern = Pattern.compile("[\u4e00-\u9fa5]");
    /**
     * 拆分前端传过来的Ids(id,id,id) <B>方法名称：getIds</B><BR>
     * <B>概要说明：</B><BR>
     * <B>版本 修改人 备注</B><BR>
     * @param Ids
     * @return
     */
    public static List<Long> getIds(String Ids)
    {
        ArrayList<Long> idArr = new ArrayList<Long>();
        if (StringUtils.isBlank(Ids))
        {
            return idArr;
        }
        if (Ids.contains(","))
        {
            String[] split = StringUtils.split(Ids, ",");
            for (String id : split)
            {
                if (StringUtils.isNotBlank(id))
                {
                    long idParse = Long.parseLong(id.trim());
                    if (!idArr.contains(idParse))
                    {
                        idArr.add(idParse);
                    }
                }
            }
        }
        else
        {
            idArr.add(Long.parseLong(Ids.trim()));
        }
        return idArr;
    }

    /**
     * 拆分前端传过来的codes(code,code,code) <B>方法名称：getCodes</B><BR>
     * <B>概要说明：</B><BR>
     * <B>版本 修改人 备注</B><BR>
     * @param codes
     * @return
     */
    public static List<String> getCodes(String codes)
    {
        ArrayList<String> codeArr = new ArrayList<String>();
        if (StringUtils.isBlank(codes))
        {
            return codeArr;
        }

        if (codes.contains(","))
        {
            String[] split = StringUtils.split(codes, ",");
            for (String code : split)
            {
                if (StringUtils.isNotBlank(code) && !codeArr.contains(code.trim()))
                {
                    codeArr.add(code.trim());
                }
            }
        }
        else
        {
            codeArr.add(codes.trim());
        }

        return codeArr;
    }

    /**
     * @param obj
     * @return boolean 为空返回true,否则返回false 创建人：生活家.杨青见 创建时间：2016年7月31日 下午5:05:26
     * @Title：isNull
     * @Description：判断对象是否为空 序号 修改人： 修改时间： 备注 001 生活家.杨青见 2016年7月31日 下午5:05:26
     *                       001 生活家.杨青见 2017年7月10日 上午 10:34:25
     *                       增加coolection.map.set的空判断
     * @version
     */
    public static boolean isNull(Object... obj)
    {
        if (null == obj || obj.length == 0)
        {
            return true;
        }
        for (Object object : obj)
        {
            if (object == null || object.toString().trim().length() == 0)
            {
                return true;
            }

            if (object instanceof Collection)
            {
                if (((Collection) object).isEmpty() || ((Collection) object).size() == 0)
                {
                    return true;
                }
            }

            if (object instanceof Map)
            {
                if (((Map) object).isEmpty() || ((Map) object).size() == 0)
                {
                    return true;
                }
            }

            if (object instanceof Set)
            {
                if (((Set) object).isEmpty() || ((Set) object).size() == 0)
                {
                    return true;
                }
            }
        }
        return false;
    }
  

    public static boolean isNotNull(Object... obj)
    {
        return !isNull(obj);
    }



    /**
     * 流转String
     * @param is
     * @return
     * @throws IOException
     */
    public static String inputStream2String(InputStream is) throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = is.read()) != -1)
        {
            baos.write(i);
        }
        return baos.toString();
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @param diff
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(long diff)
    {
        long day = diff / (24 * 60 * 60 * 1000);
        long hour = (diff / (60 * 60 * 1000) - day * 24);
        long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }

    /**
     *
     * <B>方法名称：verifyIds</B><BR>
     * <B>概要说明：ids校验规则</B><BR>
     * <B>版本 修改人 备注</B><BR>
     * @param ids
     * @return
     */
    public static boolean verifyIds(String ids)
    {
        return !ids.matches("[\\d*,?]*");
    }


    /**
     *
     * <B>方法名称：getValuessFromList</B><BR>
     * <B>概要说明：得到集合</B><BR>
     * <B>版本		修改人		备注</B><BR>
     * @param results
     * @param property
     * @param srcValue
     * @param equalflag
     * @return
     */
    public static <E> List<E> getValuessFromList(List<E> results, String property, Object srcValue, boolean equalflag) {
        if (UtilTool.isNull(results, property, srcValue)) {
            return Lists.newArrayList();
        }
        List<E> retValues = new ArrayList<E>();
        if (UtilTool.isNull(results)) {
            return retValues;
        }
        try {
            Method method = null;
            if (!results.isEmpty()) {
                method = results.get(0).getClass().getMethod("get" + firstUpperStr(property));
            }
            if (UtilTool.isNull(method)) {
                throw new Exception("未能获取到方法");
            }
            for (E e : results) {
                Object ret_value = method.invoke(e);
                if (UtilTool.isNull(ret_value)) {
                    continue;
                }
                if (equalflag) {
                    if (ret_value.toString().equals(srcValue.toString())) {
                        retValues.add(e);
                    }
                } else {
                    if (ret_value.toString().contains(srcValue.toString())) {
                        retValues.add(e);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retValues;
    }

    /**
     *
     * <B>方法名称：getCountsFromList</B><BR>
     * <B>概要说明：得到数量</B><BR>
     * <B>版本		修改人		备注</B><BR>
     * @param results
     * @param property
     * @param srcValue
     * @param equalflag
     * @return
     */
    public static <E> int getCountsFromList(List<E> results, String property, Object srcValue, boolean equalflag) {
        if(UtilTool.isNull(results)){
            return 0;
        }
        List<E> retValues = getValuessFromList(results, property, srcValue, equalflag);
        if(UtilTool.isNull(retValues)){
            return 0;
        }
        return retValues.size();
    }
    /**
     * 
     * <B>方法名称：getCountsFromList</B><BR>
     * <B>概要说明：</B><BR>
     * <B>版本		修改人		备注</B><BR>
     * @param results
     * @param propertys
     * @param srcValues
     * @param equalflag
     * @return
     */
    public static <E> int getCountsFromList(List<E> results, String[] propertys, Object[] srcValues, boolean equalflag) {
        if(UtilTool.isNull(results)){
            return 0;
        }
        List<E> retValues = getValuessFromList(results, propertys, srcValues, equalflag);
        if(UtilTool.isNull(retValues)){
            return 0;
        }
        return retValues.size();
    }

    /**
     *
     * <B>方法名称：convertValue</B><BR>
     * <B>概要说明：转换</B><BR>
     * <B>版本		修改人		备注</B><BR>
     * @param sourceValue
     * @param clazz
     * @return
     */
    public static Object convertValue(Object sourceValue, Class<?> clazz) {
        if (UtilTool.isNull(sourceValue, clazz)) {
            return null;
        }
        if (String.class.equals(clazz)) {
            return sourceValue.toString();
        } else if (Integer.class.equals(clazz) || int.class.equals(clazz)) {
            return Ints.tryParse(sourceValue.toString());
        } else if (Double.class.equals(clazz) || double.class.equals(clazz)) {
            return Doubles.tryParse(sourceValue.toString());
        } else if (Float.class.equals(clazz) || float.class.equals(clazz)) {
            return Floats.tryParse(sourceValue.toString());
        } else if (Long.class.equals(clazz) || long.class.equals(clazz)) {
            return Longs.tryParse(sourceValue.toString());
        } else if (BigDecimal.class.equals(clazz)) {
            return BigDecimal.valueOf(Doubles.tryParse(sourceValue.toString()));
        } else if (Short.class.equals(clazz) || short.class.equals(clazz)) {
            return Short.valueOf(sourceValue.toString());
        } else if (Boolean.class.equals(clazz) || boolean.class.equals(clazz)) {
            return sourceValue.toString().equals("1") ? true : Boolean.valueOf(sourceValue.toString());
        } else if (Byte.class.equals(clazz) || byte.class.equals(clazz)) {
            return Byte.valueOf(sourceValue.toString());
        } else if (Character.class.equals(clazz) || char.class.equals(clazz)) {
            return Character.valueOf((char) sourceValue);
        } else if (Date.class.equals(clazz)) {
            if (sourceValue instanceof String) {
                //return DateUtil.strToDate(sourceValue.toString());
            }
            return sourceValue;
        } else {
            return sourceValue;
        }
    }

    public static <E> List<E> getValuessFromList(List<E> results, String[] propertys, Object[] srcValues, boolean equalflag) {
        if (UtilTool.isNull(results, propertys, srcValues)) {
            return Lists.newArrayList();
        }
        if (propertys.length != srcValues.length) {
            //throw new SystemException("对比参数不一致");
        }
        List<E> retValues = new ArrayList<E>();
        if (UtilTool.isNull(results)) {
            return retValues;
        }
        try {
            for (E e : results) {
                boolean flag = false;
                for (int i = 0; i < propertys.length; i++) {
                    /**
                     * 判断一下当前的值以及当前的属性是否为空
                     */
                    if(UtilTool.isNull(srcValues[i])) {
                        flag = true;
                        break;
                    }
                    if(UtilTool.isNull(propertys[i])) {
                        flag = true;
                        break;
                    }
                    String prop = propertys[i];
                    Method method = null;
                    if (!results.isEmpty()) {
                        method = results.get(0).getClass().getMethod("get" + firstUpperStr(prop));
                    }
                    if (UtilTool.isNull(method)) {
                        throw new Exception("未能获取到方法");
                    }
                    /**
                     * 只要有一个对象的值为空，就不满足条件
                     */
                    Object ret_value = method.invoke(e);
                    if (UtilTool.isNull(ret_value)) {
                        flag = true;
                        break;
                    }
                    if (equalflag) {
                        if (ret_value.toString().equals(srcValues[i].toString())) {
                            continue;
                        }
                        flag = true;
                        break;
                    } else {
                        if (ret_value.toString().contains(srcValues[i].toString())) {
                            continue;
                        }
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    retValues.add(e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retValues;
    }

    public static void copyIncludeProperty(Object src, Object target, String... inludeFields) {
        if (UtilTool.isNull(src, target, inludeFields)) {
            return;
        }
        try {
            BeanInfo targetBeaninfo = Introspector.getBeanInfo(target.getClass());
            PropertyDescriptor[] targetProps = targetBeaninfo.getPropertyDescriptors();
            BeanInfo srcBeaninfo = Introspector.getBeanInfo(src.getClass());
            PropertyDescriptor[] srcProps = srcBeaninfo.getPropertyDescriptors();
            for (PropertyDescriptor prop : srcProps) {
                try {
                    if (prop.getName().equalsIgnoreCase("class")) {
                        continue;
                    }
                    if (org.apache.commons.lang3.ArrayUtils.contains(inludeFields, prop.getName())) {
                        Object srcPropValue = prop.getReadMethod().invoke(src);
                        PropertyDescriptor targetProp = getPropertyDescriptor(targetProps, prop.getName());
                        try {
                            targetProp.getWriteMethod().invoke(target, convertValue(srcPropValue, targetProp.getPropertyType()));
                        } catch (Exception e) {
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        } catch (Exception e) {
        }
    }

    public static void copyProperty(Object src, Object target, String... exludeFields) {
        if (UtilTool.isNull(src, target)) {
            return;
        }
        try {
            BeanInfo targetBeaninfo = Introspector.getBeanInfo(target.getClass());
            PropertyDescriptor[] targetProps = targetBeaninfo.getPropertyDescriptors();
            BeanInfo srcBeaninfo = Introspector.getBeanInfo(src.getClass());
            PropertyDescriptor[] srcProps = srcBeaninfo.getPropertyDescriptors();
            for (PropertyDescriptor prop : srcProps) {
                try {
                    if (prop.getName().equalsIgnoreCase("class")) {
                        continue;
                    }
                    if (!UtilTool.isNull(exludeFields)) {
                        if (org.apache.commons.lang3.ArrayUtils.contains(exludeFields, prop.getName())) {
                            continue;
                        }
                    }
                    Object srcPropValue = prop.getReadMethod().invoke(src);
                    if (!UtilTool.isNull(srcPropValue)) {
                        PropertyDescriptor targetProp = getPropertyDescriptor(targetProps, prop.getName());
                        try {
                            targetProp.getWriteMethod().invoke(target, convertValue(srcPropValue, targetProp.getPropertyType()));
                        } catch (Exception e) {
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        } catch (Exception e) {
        }
    }


    private static PropertyDescriptor getPropertyDescriptor(PropertyDescriptor[] props, String propertyName) {
        if (UtilTool.isNull(props, propertyName)) {
            return null;
        }
        for (PropertyDescriptor prop : props) {
            if (prop.getName().equals(propertyName)) {
                return prop;
            }
        }
        return null;
    }

    public static <E> BigDecimal sumTotal(List<E> results, String property) {
        if (UtilTool.isNull(property)) {
            return null;
        }
        if (UtilTool.isNull(results)) {
            return null;
        }
        BigDecimal tempValue = new BigDecimal(0L);
        BeanInfo targetBeaninfo = null;
        try {
            targetBeaninfo = Introspector.getBeanInfo(results.get(0).getClass());
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        PropertyDescriptor[] targetProps = targetBeaninfo.getPropertyDescriptors();
        PropertyDescriptor propDescript = getPropertyDescriptor(targetProps, property);
        try {
            for (E e : results) {
                Object value = propDescript.getReadMethod().invoke(e);
                if (!UtilTool.isNull(value)) {
                    tempValue = tempValue.add(BigDecimal.valueOf(Double.valueOf(value.toString())));
                }
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return tempValue;
    }


    public static List getListObjectByField(List<?> lists, String property) {
        List result = new ArrayList();
        if (UtilTool.isNull(lists, property)) {
            return result;
        }
        if (UtilTool.isNull(lists)) {
            return result;
        }
        BeanInfo targetBeaninfo = null;
        PropertyDescriptor prop = null;
        try {
            targetBeaninfo = Introspector.getBeanInfo(lists.get(0).getClass());
            PropertyDescriptor[] targetProps = targetBeaninfo.getPropertyDescriptors();
            prop = getPropertyDescriptor(targetProps, property);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        if (UtilTool.isNull(targetBeaninfo, prop)) {
            return null;
        }
        for (Object object : lists) {
            try {
                result.add(prop.getReadMethod().invoke(object));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //List的去重
    public static  <E>  List<E> getDistinctList(List<E> values){
        if(UtilTool.isNull(values)){
            return new ArrayList<>();
        }
        List<E> returnValues = new ArrayList<>();
        for (E object : values) {
            if(!UtilTool.isNull(object) && !returnValues.contains(object) ){
                returnValues.add(object);
            }
        }
        return returnValues;
    }
    //获取最大值
    public static <T extends Double> T getMaxByList(List<T> list){
        return Collections.max(list);
    }

    //获取最小值
    public static <T extends Double> T getMinByList(List<T> list){
        return Collections.min(list);
    }

    public static String formatDate(Date date, String... format) {
        if (UtilTool.isNull(date)) {
            return "";
        }
        String formatStr = "";
        if (format == null || format.length == 0) {
            formatStr = "yyyy-MM-dd";
        } else {
            if (!UtilTool.isNull(format[0])) {
                formatStr = format[0];
            } else {
                formatStr = "yyyy-MM-dd";
            }
        }
        SimpleDateFormat sp = new SimpleDateFormat(formatStr);
        return sp.format(date);
    }

    private static String firstUpperStr(String property){
        if(null!=property){
            char[] ch = property.toCharArray();
            if (ch[0] >= 'a' && ch[0] <= 'z') {
                ch[0] = (char) (ch[0] - 32);
            }
            return new String(ch);
        }
        return null;
    }

   


    public static <T> void sort(List<T> results, String property, String order) {
        //升序
        if (UtilTool.isNull(results)) {
            return;
        }
        if (UtilTool.isNull(property, order)) {
            return;
        }
        BeanInfo targetBeaninfo = null;
        try {
            targetBeaninfo = Introspector.getBeanInfo(results.get(0).getClass());
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        PropertyDescriptor[] targetProps = targetBeaninfo.getPropertyDescriptors();
        final PropertyDescriptor prop = getPropertyDescriptor(targetProps, property);
        final String order_temp = order;
        Comparator<T> comparator = new Comparator<T>() {
            @Override
            public int compare(T s1, T s2) {
                try {

                    if(s1 == null || s2 == null){
                        if (order_temp.equalsIgnoreCase("asc")) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }

                    Object s1Value = prop.getReadMethod().invoke(s1);
                    Object s2Value = prop.getReadMethod().invoke(s2);
                    if (UtilTool.isNull(s1Value) || UtilTool.isNull(s2Value)) {
                        if (order_temp.equalsIgnoreCase("asc")) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                    if ( s1Value instanceof Number) {
                        Double d1 = Double.valueOf(s1Value.toString());
                        Double d2 = Double.valueOf(s2Value.toString());
                        if (order_temp.equalsIgnoreCase("asc")) {
                            return d1.compareTo(d2);
                        } else {
                            return d2.compareTo(d1);
                        }
                    }else if ( s1Value instanceof Date) {
                        Date d1 =  (Date)s1Value;
                        Date d2 =  (Date)s2Value;
                        if (order_temp.equalsIgnoreCase("asc")) {
                            return d1.compareTo(d2);
                        } else {
                            return d2.compareTo(d1);
                        }
                    }else {
                        if (order_temp.equalsIgnoreCase("asc")) {
                            return s1Value.toString().length() - s2Value.toString().length();
                        } else {
                            return s2Value.toString().length() - s1Value.toString().length();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return 0;
            }
        };
        try{
            Collections.sort(results, comparator);//注意：是根据的
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }


   

    /**
     * 将字符串数组转变为list
     * @param str
     * @return
     */
    public static List<String> getStrListByStrs(String ... str){
    	List<String> result=new ArrayList<>();
    	if(str==null||str.length==0){
    		return null;
    	}else{
    		for(String s:str){
    			if(StringUtils.isNotBlank(s)){
    				result.add(s);
    			}
    		}
    		return result;
    	}
    }

    /**
     * 求两个集合的交集
     * @param ls
     * @param ls2
     * @return
     */
    public static List intersect(List ls, List ls2) {
        List list = new ArrayList(Arrays.asList(new Object[ls.size()]));
        Collections.copy(list, ls);
        list.retainAll(ls2);
        return list;
    }

    /**
     * 求两个集合的差集
     * @param ls
     * @param ls2
     * @return
     */
    public static <E> List diff(List<E> ls, List<E> ls2) {
        List<E> list = new ArrayList(Arrays.asList(new Object[ls.size()]));
        Collections.copy(list, ls);
        list.removeAll(ls2);
        return list;
    }

    /**
     * 获取删除的code数据集合
     * @param oldCodeList
     * @param codeList
     * @return
     */
    public static  <E>  List<E> getRemoveList(List<E> oldCodeList, List<E> codeList) {
        List<E> result = diff(oldCodeList,codeList);
        return result;
    }

    /**
     * 获取新增的code数据集合
     * @param oldCodeList
     * @param codeList
     * @return
     */
    public static <E> List<E> getInsertList(List<E> oldCodeList, List<E> codeList) {
        List<E> result = diff(codeList,oldCodeList);
        return result;
    }

    /**
     * 转义正则特殊字符 （$()*+.[]?\^{},|）
     *
     * @param keyword
     * @return
     */
    public static String escapeExprSpecialWord(String keyword) {
        if (StringUtils.isNotBlank(keyword)) {
            String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }

    public static Integer convertDoule2Integer(Double value){
        if(UtilTool.isNotNull(value)){
            String str = value.toString();
            str = str.substring(0, str.lastIndexOf("."));
            return Integer.valueOf(str);
        }
        return null;
    }

    /**
     *
     * <B>方法名称：getSqlStrByList</B><BR>
     * <B>概要说明：返回SQL查询的字符串（length不能大于1000）</B><BR>
     * <B>版本		修改人		备注</B><BR>
     * @param list
     * @return
     * @throws Exception 
     */
    public static <E> String getSqlStrByList(Collection<E> list) throws Exception{
        if(null == list || list.size() == 0){
            return "";
        }
        if(list.size() > 1000){
            throw new Exception("数组长度大于1000，ORACLE数据库不支持");
        }
        StringBuffer sb = new StringBuffer();
        for (E string : list) {
            if(UtilTool.isNotNull(string)) {
                sb.append("'"+string+"',");
            }
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }

    /**
     *
     * <B>方法名称：getOracleSQLIn</B><BR>
     * <B>概要说明：针对数量超过1000的做法</B><BR>
     * <B>版本		修改人		备注</B><BR>
     * @param ids
     * @param count
     * @param field
     * @return 返回描述：field in (?,?) or field in (?,?)
     * @throws Exception 
     */
    public static String getOracleSQLIn(List<?> ids, int count, String field) throws Exception {
        if(UtilTool.isNull(ids)){
            throw new Exception("ids参数不能为空");
        }
        if(count <=0 ){
            throw new Exception("count参数不能小于等于0");
        }
        if(UtilTool.isNull(field)){
            throw new Exception("field参数不能为空");
        }
        count = Math.min(count, 1000);
        int len = ids.size();
        int size = len % count;
        if (size == 0) {
            size = len / count;
        } else {
            size = (len / count) + 1;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int fromIndex = i * count;
            int toIndex = Math.min(fromIndex + count, len);
            String productId = StringUtils.defaultIfEmpty(StringUtils.join(ids.subList(fromIndex, toIndex), "','"), "");
            if (i != 0) {
                builder.append(" or ");
            }
            builder.append(field).append(" in ('").append(productId).append("')");
        }
        return StringUtils.defaultIfEmpty(builder.toString(), field + " in ('')");
    }
    
    /**
    *
    * <B>方法名称：getOracleSQLNotIn</B><BR>
    * <B>概要说明：针对数量超过1000的做法</B><BR>
    * <B>版本        修改人     备注</B><BR>
    * @param ids
    * @param count
    * @param field
    * @return 返回描述：field not in (?,?) or field not in (?,?)
     * @throws Exception 
    */
   public static String getOracleSQLNotIn(List<?> ids, int count, String field) throws Exception {
       if(UtilTool.isNull(ids)){
           throw new Exception("ids参数不能为空");
       }
       if(count <=0 ){
           throw new Exception("count参数不能小于等于0");
       }
       if(UtilTool.isNull(field)){
           throw new Exception("field参数不能为空");
       }
       count = Math.min(count, 1000);
       int len = ids.size();
       int size = len % count;
       if (size == 0) {
           size = len / count;
       } else {
           size = (len / count) + 1;
       }
       StringBuilder builder = new StringBuilder();
       for (int i = 0; i < size; i++) {
           int fromIndex = i * count;
           int toIndex = Math.min(fromIndex + count, len);
           String productId = StringUtils.defaultIfEmpty(StringUtils.join(ids.subList(fromIndex, toIndex), "','"), "");
           if (i != 0) {
               builder.append(" or ");
           }
           builder.append(field).append(" not in ('").append(productId).append("')");
       }
       return StringUtils.defaultIfEmpty(builder.toString(), field + " not in ('')");
   }

    /**
     * 判断字符串中是否包含中文
     * @param str
     * 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        Matcher m = chinesePattern.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 字符串只包含数字和字母
     * @param str
     * @return
     */
    public static boolean isLetterDigitOrChinese(String str) {
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
        return str.matches(regex);
    }


    public static String convertNullToString(Object o) {
    	if (o == null) {
    		return "";
    	} else {
    		return o.toString();
    	}
    }

    /**
     * 返回对象 List<Long>
     * @param dataList
     * @param field
     * @param <T>
     * @return
     */
    public static <T> List<Long> getLongValue(List<T> dataList, String field){
        if(dataList==null||dataList.size()<=0){
            return Lists.newArrayList();
        }
        return getLongValue(dataList, org.springframework.beans.BeanUtils.findMethod(dataList.get(0).getClass(),"get"+upperCaseFirstLetter(field)));
    }

    public static <T> List<Long> getLongValue(List<T> dataList,Method fieldMethod){
        List<Long> list=Lists.newArrayList();
        if(dataList==null||fieldMethod==null){
            return list;
        }
        for(T t:dataList){
            try{
                Object obj=fieldMethod.invoke(t);
                if(obj==null){
                    continue;
                }
                Long value=Long.valueOf(obj.toString());
                list.add(value);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        Set<Long> set = new HashSet<>(list);
        list = new ArrayList<>(set);
        return list;
    }

    /**
     * 返回对象 List<String>
     * @param dataList
     * @param field
     * @param <T>
     * @return
     */
    public static <T> List<String> getStringValue(List<T> dataList,String field){
        if(dataList==null||dataList.size()<=0){
            return Lists.newArrayList();
        }
        return getStringValue(dataList, org.springframework.beans.BeanUtils.findMethod(dataList.get(0).getClass(),"get"+upperCaseFirstLetter(field)));
    }

    public static <T> List<String> getStringValue(List<T> dataList,Method fieldMethod){
        List<String> list=Lists.newArrayList();
        if(dataList==null||fieldMethod==null){
            return list;
        }
        for(T t:dataList){
            try{
                Object obj=fieldMethod.invoke(t);
                if(obj==null){
                    continue;
                }
                if(StringUtils.isNotBlank(obj.toString())){
                    list.add(obj.toString());
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        Set<String> set = new HashSet<>(list);
        list = new ArrayList<>(set);
        return list;
    }

    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String upperCaseFirstLetter(String str) {
        if(str!=null && str.length()>0) {
            return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
        } else {
            return str;
        }
    }
    /**
     *
     * <B>方法名称：getCurrentDiffHours</B><BR>
     * <B>概要说明：获取小时的时间差，传入的是毫秒</B><BR>
     * <B>版本        修改人     备注</B><BR>
     * @param dateMills
     * @return
     */
    public static double getCurrentDiffHours(long dateMills){
        long currentMills = System.currentTimeMillis();
        //按照分总数来
        BigDecimal diffHours = new BigDecimal((currentMills - dateMills)).divide(new BigDecimal(3600000L),4,BigDecimal.ROUND_HALF_UP);
        return diffHours.doubleValue();
    }

     /**
      *
      * <B>方法名称：encode</B><BR>
      * <B>概要说明：简单的数字转换成字符</B><BR>
      * <B>版本		修改人		备注</B><BR>
      * @param enc
      * @return
      */
    public static String encode(Integer enc){
        StringBuffer sb = new StringBuffer();
        String str = enc.toString();
        for(int i=0;i<str.length();i++){
            String tempStr = str.substring(i,i+1);
            sb.append(numberToLetter(Integer.valueOf(tempStr)));
        }
        return sb.toString();
    }

    /**
     *
     * <B>方法名称：decode</B><BR>
     * <B>概要说明：将字符转换成数字</B><BR>
     * <B>版本		修改人		备注</B><BR>
     * @param dec
     * @return
     */
    public static Integer decode(String dec){
          StringBuffer sb = new StringBuffer();
          for(int i=0;i<dec.length();i++){
              String tempStr = dec.substring(i,i+1);
              sb.append(letterToNumber(tempStr));
          }
          return Integer.valueOf(sb.toString());
    }

    //数字转字母 1-26 ： A-Z
    private static String numberToLetter(int num) {
        if (num < 0) {
            return null;
        }
        if(num == 0){
            return "X";
        }
        String letter = "";
        num--;
        do {
            if (letter.length() > 0) {
                num--;
            }
            letter = ((char) (num % 26 + 'A')) + letter;
            num = (num - num % 26) / 26;
        } while (num > 0);

        return letter;
    }
    //字母转数字  A-Z ：1-26
    public static int letterToNumber(String letter) {
        if(letter.equalsIgnoreCase("X")){
            return 0;
        }
        int length = letter.length();
        int num = 0;
        int number = 0;
        for(int i = 0; i < length; i++) {
            char ch = letter.charAt(length - i - 1);
            num = ch - 'A' + 1 ;
            num *= Math.pow(26, i);
            number += num;
        }
        return number;
    }


    /**
     * 判断字符串是否全部为中文字符组成
     * @param str	检测的文字
     * @return	true：为中文字符串，false:含有非中文字符
     */
    public static boolean isChineseStr(String str){
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
        char c[] = str.toCharArray();
        for(int i=0;i<c.length;i++){
            Matcher matcher = pattern.matcher(String.valueOf(c[i]));
            if(!matcher.matches()){
                return false;
            }
        }
        return true;
    }
    
    private static String units[] = {"","十","百","千","万","十","百","千","亿"};  
    private static String nums[] = {"零","一","二","三","四","五","六","七","八","九","十"}; 
    /**
     * 
     * <B>方法名称：numberToChinese</B><BR>
     * <B>概要说明：把数字转换为文字</B><BR>
     * <B>版本		修改人		备注</B><BR>
     * @param number
     * @return
     */
    public static String numberToChinese(Integer number) { 
        if(null == number) {
            return "";
        }
        String numbers = number +"";
        String out = "";  
        String[] result = new String[numbers.length()];  
        for(int i=0;i<result.length;i++) {  
            result[i] = String.valueOf(numbers.charAt(i));  
        }  
        int back = 0;  
        for(int i=0;i<result.length;i++) {  
            if(!result[i].equals("0")) {  
                back = result.length-i-1;  
                out += nums[Integer.parseInt(result[i])];  
                out += units[back];  
            }else {  
                if(i==result.length-1) {  
                }else {  
                    if(!result[i+1].equals("0")) {  
                        out += nums[0];  
                    }  
                }  
            }  
        }  
        return out;  
    } 
    
	/**
	 * 思路： 整数部分：参考 " 玖仟玖佰玖拾玖亿   玖仟玖佰玖拾玖万   玖仟玖佰玖拾玖圆" 格式 数字补齐，0123 4567
	 * 9999格式，然后分位三部分，进行计算 （9999个位组，4567万位组，0123亿位组）
	 * 
	 * 小数部分：参考 " 玖角玖分" 格式
	 * 
	 * 最小0，最大9999 9999 9999.99
	 * 
	 * @param cl
	 * @return
	 * @throws Exception
	 */
	public static String BigDecimalToChinese(BigDecimal cl) throws Exception{
		// 数字组
		char[] a1 = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };
		// 单位组
		char[] a2 = { '分', '角', '圆' };
		// 量级组
		char[] a3 = { '仟', '佰', '拾', '亿', '仟', '佰', '拾', '万', '仟', '佰', '拾', '\0' };
		// String maxtemp = "玖仟玖佰玖拾玖亿 玖仟玖佰玖拾玖万 玖仟玖佰玖拾玖圆";
		// 9999 9999 9999
		// 数字补齐，0123 4567 9999格式，然后分位三部分，进行计算 （9999个位组，4567万位组，0123亿位组）
		String maxchange = "999999999999";
		if (cl == null || cl.compareTo(BigDecimal.ZERO) < 0) {
			throw new Exception("金额不能为空或者负数");
		}
		// 格式化数字为(n,2)格式的标准金额 ，（四舍无入）,没有小数位则补0
		cl = cl.setScale(2, RoundingMode.HALF_UP);

		// 小于0.01 直接返回零
		if (cl.compareTo(new BigDecimal("0.01")) < 0) {
			StringBuffer sf2 = new StringBuffer();
			sf2.append(a1[0]);
			sf2.append(a2[2]);
			return sf2.toString();
		}
		// 拆分为整数部分，和小数部分
		String sNumericalValue = cl.toPlainString();
		String[] arrayValues = sNumericalValue.split("\\.");
		// 整数部分
		String string0 = arrayValues[0];
		// 小数部分
		String string1 = arrayValues[1];
		Long lg0 = Long.parseLong(string0);
		if (new BigDecimal(string0).compareTo(new BigDecimal(maxchange)) > 0) {
			throw new Exception("金额超出最大计算上限");
		}
		Long lg1 = Long.parseLong(string1);
		// 左边需要补0的数量
		int length = maxchange.length() - arrayValues[0].length();
		char[] cr0 = new char[length];
		for (int r = 0; r < cr0.length; r++) {
			cr0[r] = '0';
		}
		string0 = new String(cr0) + string0;

		// 最终整数部分的结果
		StringBuffer sf = new StringBuffer();
		// 先处理整数的 亿位组
		int startindexY = 0;
		int bgY = BigDecimalToChineseCircle(a1, a3, string0, startindexY, sf, 0);
		// 再处理整数的 万位组
		int startindexW = string0.length() - 8;
		int bgW = BigDecimalToChineseCircle(a1, a3, string0, startindexW, sf, bgY);
		// 再处理整数的 个位组
		int startindex = string0.length() - 4;
		int bgG = BigDecimalToChineseCircle(a1, a3, string0, startindex, sf, bgW);

		if (lg0 > 0) {
			sf.append(a2[2]);
		}

		// 处理小数部分
		if (lg1 > 0) {
			char[] charArray = arrayValues[1].toCharArray();
			if (charArray[0] == '0' && charArray[1] == '0' && lg0 == 0) {
				return String.valueOf(a1[0] + a2[2]);
			}
			if (charArray[0] > '0') {
				String c = String.valueOf(charArray[0]);
				int x = Integer.parseInt(c);
				sf.append(a1[x]);
				sf.append(a2[1]);
			}
			if (charArray[1] > '0') {
				if (charArray[0] == '0' && lg0 > 0) {
					sf.append(a1[0]);
				}
				String c2 = String.valueOf(charArray[1]);
				int x2 = Integer.parseInt(c2);
				sf.append(a1[x2]);
				sf.append(a2[0]);
			}
		}

		return sf.toString();
	}

	/**
	 * 
	 * @param a1
	 *            常量，大写数字组
	 * @param a3
	 *            常量，量级组
	 * @param string0
	 *            补为"9999 9999 9999"格式的待处理数字转换的字符串
	 * @param startindex
	 *            本方法，四位一组，进行处理，该参数为截取起始位置，常有0 4 8
	 * @param sf
	 *            最终转换的大写数字字符串的buffer
	 * @param beforeNumber
	 *            上一组，转换为的数字
	 * @return 返回本组转换的数字
	 */
	private static int BigDecimalToChineseCircle(char[] a1, char[] a3, String string0, int startindex, StringBuffer sf,
			int beforeNumber) {
		char[] cr = new char[20];
		int j = cr.length;
		String substringG = string0.substring(startindex, startindex + 4);
		int intG = Integer.parseInt(substringG);
		if (intG > 0) {
			// 拼接整数部分
			char[] charArray = substringG.toCharArray();
			// 从右至左，挨个处理，当前位=0时，是否需要补大写零的标志
			boolean hasnumlastalong = false;
			// 从右至左，挨个处理，对仟而言，只要佰位不为零则为true
			boolean hasnumlast = false;
			for (int i = charArray.length - 1; i > -1; i--) {
				if (hasnumlast) {
					// hasnumlast表示当前位的地位有值，还需要当前位的高位有值, 即上一组数字大于0
					if (beforeNumber > 0) {
						hasnumlastalong = true;
					}
					/// ,若上一组等于0，则当前位的至少前一位有值
					else if (beforeNumber == 0) {
						for (int z = 0; z < i; z++) {
							// 字符在1-9直接，字符为0时，=48
							if (charArray[z] > 48 && charArray[z] < 58) {
								hasnumlastalong = true;
								break;
							}
						}
						hasnumlastalong = false;
					} else {
						hasnumlastalong = false;
					}
				}
				if (i == 3) {
					j = j - 1;
					cr[j] = (a3[startindex + 3]);
				}
				// 数字转汉字
				String c = String.valueOf(charArray[i]);
				int x = Integer.parseInt(c);
				if (x == 0) {
					if (hasnumlastalong) {
						j = j - 1;
						cr[j] = (a1[x]);
						hasnumlastalong = false;
					}
					// 标记当前位置的值，做为下一轮前一位的数据标记
					hasnumlast = false;
				} else {
					if (i < 3) {
						j = j - 1;
						cr[j] = a3[startindex + i];
					}
					j = j - 1;
					cr[j] = a1[x];

					// 标记当前位置的值，做为下一轮前一位的数据标记
					hasnumlast = true;
				}
			}
		}

		for (int r = 0; r < cr.length; r++) {
			if (cr[r] == '\0') {
				continue;
			}
			sf.append(cr[r]);
		}
		return intG;
	}
    

    

    
    /**
     * 
     * <B>方法名称：convertEmptyStr</B><BR>
     * <B>概要说明：把空字符串转换为null</B><BR>
     * <B>版本		修改人		备注</B><BR>
     * @param src
     */
    public static <T> void convertEmptyStr(T src) {
        if(null == src) {
            return;
        }
        BeanInfo targetBeaninfo = null;
        try {
            targetBeaninfo = Introspector.getBeanInfo(src.getClass());
            PropertyDescriptor[] targetProps = targetBeaninfo.getPropertyDescriptors();
            if(null == targetProps) {
                return;
            }
            for (PropertyDescriptor tempProp : targetProps) {
                try {
                    Object value = tempProp.getReadMethod().invoke(src, null);
                    if(UtilTool.isNull(value)) {
                        tempProp.getWriteMethod().invoke(src, new Object[] {null});
                    }
                }catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * <B>方法名称：checkParamNull</B><BR>
     * <B>概要说明：校验是否为空</B><BR>
     * <B>版本		修改人		备注</B><BR>
     * @param request
     * @param invalidMessage 错误消息
     * @throws Exception
     */
    public static void checkParamNull(Object request,String invalidMessage) throws Exception{
        if(isNull(request)){
            throw new Exception(invalidMessage);
        }
    }
    
    /**
     * 
     * <B>方法名称：checkParamNotNull</B><BR>
     * <B>概要说明：校验不能为空</B><BR>
     * <B>版本        修改人     备注</B><BR>
     * @param request
     * @param invalidMessage 错误消息
     * @throws Exception
     */
    public static void checkParamNotNull(Object request,String invalidMessage) throws Exception{
        if(isNotNull(request)){
            throw new Exception(invalidMessage);
        }
    }


    /**
     * 注释:图片转Base64编码
     * @author erza
     * @date 2019/10/15 17:47
     */
    private static String imageChangeBase64(byte[] data){
        // Base64编码
        return Base64.getEncoder().encodeToString(data);
    }
    
    /**
     * 对象转map
     *
     * @param obj
     * @return
     */
    public static Map ConvertObjToMap(Object obj) {
        Map<String, Object> reMap = new HashMap<String, Object>();
        if (obj == null)
            return null;
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                try {
                    Field f = obj.getClass().getDeclaredField(fields[i].getName());
                    f.setAccessible(true);
                    Object o = f.get(obj);
                    reMap.put(fields[i].getName(), o);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return reMap;
    }

    /**
     * 获取随机数
     * @param num 100000 位数
     * @return
     */
    public static int getRandomNums(int num) {
        return  (int) ((Math.random() * 9 + 1) * num);
    }



    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 下划线转驼峰,正常输出
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    /**
     * @doc 日期转换星期几
     * @param datetime 日期 例:2017-10-17
     * @return String 例:星期二
     * @history 2020年11月17日
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 比较两个日期先后顺序
     * @param beginTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static Boolean compareToDate(String beginTime,Date endTime) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date sd1=df.parse(beginTime.trim());
        return sd1.before(endTime);
    }
    public static String getFileUploadPath(String linuxLocation){
        Date date = new Date();
        String time =  new SimpleDateFormat("yyyy-MM-dd").format(date);
        System.out.println("获取到精确到日的时间格式为"+time);
        String[] str = time.split("-");//根据‘-’进行拆分字符串 拆分出来的日期有，年，日，月，根据年日月创建文件夹
        String datePath="/"+str[0]+"/"+str[1]+"/"+str[2]+"/";
        return linuxLocation+datePath;
    }
    /**
     * 文件byte[]类型转File
     *
     * @param bytes     bytes
     * @param outPath   输出目录
     * @param fileName  文件名
     * @return
     */
    public static File bytesToFile(byte[] bytes, String outPath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File destFile = new File(outPath + File.separator + fileName);
            //文件目录不存在需要先创建
            if(!destFile.getParentFile().exists()){
                destFile.getParentFile().mkdirs();
            }
            file = new File(outPath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }

}
