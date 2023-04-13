package com.ruoyi.potenza.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
/**
 * @author 木子
 * @version 1.0
 * @description: TODO
 * @date 2023/1/15 20:49
 */
public class AverageCapitalUtils {
//    /**
//     * 等额本息计算获取还款方式为等额本息的每月偿还本金和利息
//     *
//     * 公式：每月偿还本息=〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
//     *
//     * @param invest
//     *            总借款额（贷款本金）
//     * @param yearRate
//     *            年利率
//     * @param month
//     *            还款总月数
//     * @return 每月偿还本金和利息,不四舍五入，直接截取小数点最后两位
//     */
//    public static BigDecimal getPerMonthPrincipalInterest(double invest, double yearRate, int totalmonth) {
//        double monthRate = yearRate / 12;
//        BigDecimal monthIncome = new BigDecimal(invest)
//                .multiply(new BigDecimal(monthRate * Math.pow(1 + monthRate, totalmonth)))
//                .divide(new BigDecimal(Math.pow(1 + monthRate, totalmonth) - 1), 2, BigDecimal.ROUND_UP);
//        //return monthIncome.doubleValue();
//        return monthIncome;
//    }
//
//    /**
//     * 等额本息计算获取还款方式为等额本息的每月偿还利息
//     *
//     * 公式：每月偿还利息=贷款本金×月利率×〔(1+月利率)^还款月数-(1+月利率)^(还款月序号-1)〕÷〔(1+月利率)^还款月数-1〕
//     *
//     * @param invest
//     *            总借款额（贷款本金）
//     * @param yearRate
//     *            年利率
//     * @param month
//     *            还款总月数
//     * @return 每月偿还利息
//     */
//    public static Map<Integer, BigDecimal> getPerMonthInterest(double invest, double yearRate, int totalmonth) {
//        Map<Integer, BigDecimal> map = new HashMap<Integer, BigDecimal>();
//        double monthRate = yearRate / 12;
//        BigDecimal monthInterest;
//        for (int i = 1; i < totalmonth + 1; i++) {
//            BigDecimal multiply = new BigDecimal(invest).multiply(new BigDecimal(monthRate));
//            BigDecimal sub = new BigDecimal(Math.pow(1 + monthRate, totalmonth))
//                    .subtract(new BigDecimal(Math.pow(1 + monthRate, i - 1)));
//            monthInterest = multiply.multiply(sub).divide(new BigDecimal(Math.pow(1 + monthRate, totalmonth) - 1), 2,
//                    BigDecimal.ROUND_DOWN);
//            monthInterest = monthInterest.setScale(2, BigDecimal.ROUND_DOWN);
//            map.put(i, monthInterest);
//        }
//        return map;
//    }
//
//    /**
//     * 等额本息计算获取还款方式为等额本息的每月偿还本金
//     *
//     * @param invest
//     *            总借款额（贷款本金）
//     * @param yearRate
//     *            年利率
//     * @param month
//     *            还款总月数
//     * @return 每月偿还本金
//     */
//    public static Map<Integer, BigDecimal> getPerMonthPrincipal(double invest, double yearRate, int totalmonth) {
//        double monthRate = yearRate / 12;
//        BigDecimal monthIncome = new BigDecimal(invest)
//                .multiply(new BigDecimal(monthRate * Math.pow(1 + monthRate, totalmonth)))
//                .divide(new BigDecimal(Math.pow(1 + monthRate, totalmonth) - 1), 2, BigDecimal.ROUND_DOWN);
//        Map<Integer, BigDecimal> mapInterest = getPerMonthInterest(invest, yearRate, totalmonth);
//        Map<Integer, BigDecimal> mapPrincipal = new HashMap<Integer, BigDecimal>();
//
//        for (Map.Entry<Integer, BigDecimal> entry : mapInterest.entrySet()) {
//            mapPrincipal.put(entry.getKey(), monthIncome.subtract(entry.getValue()));
//        }
//        return mapPrincipal;
//    }
//
//    /**
//     * 等额本息计算获取还款方式为等额本息的总利息
//     *
//     * @param invest
//     *            总借款额（贷款本金）
//     * @param yearRate
//     *            年利率
//     * @param month
//     *            还款总月数
//     * @return 总利息
//     */
//    public static double getInterestCount(double invest, double yearRate, int totalmonth) {
//        BigDecimal count = new BigDecimal(0);
//        Map<Integer, BigDecimal> mapInterest = getPerMonthInterest(invest, yearRate, totalmonth);
//
//        for (Map.Entry<Integer, BigDecimal> entry : mapInterest.entrySet()) {
//            count = count.add(entry.getValue());
//        }
//        return count.doubleValue();
//    }
//
//    /**
//     * 应还本金总和
//     *
//     * @param invest
//     *            总借款额（贷款本金）
//     * @param yearRate
//     *            年利率
//     * @param month
//     *            还款总月数
//     * @return 应还本金总和
//     */
//    public static double getPrincipalInterestCount(double invest, double yearRate, int totalmonth) {
//        double monthRate = yearRate / 12;
//        BigDecimal perMonthInterest = new BigDecimal(invest)
//                .multiply(new BigDecimal(monthRate * Math.pow(1 + monthRate, totalmonth)))
//                .divide(new BigDecimal(Math.pow(1 + monthRate, totalmonth) - 1), 2, BigDecimal.ROUND_DOWN);
//        BigDecimal count = perMonthInterest.multiply(new BigDecimal(totalmonth));
//        count = count.setScale(2, BigDecimal.ROUND_DOWN);
//        return count.doubleValue();
//    }
//
//    /**
//     * @param args
//     */
//    public static void main(String[] args) {
//        double invest = 38988; // 本金
//        int month = 12;
//        double yearRate = 0.15; // 年利率
//        BigDecimal perMonthPrincipalInterest = getPerMonthPrincipalInterest(invest, yearRate, month);
//        System.out.println("等额本息---每月还款本息：" + perMonthPrincipalInterest);
//        System.out.println("等额本息---每月还款本息：" + getPerMonthPrincipalInterest(invest, yearRate, 3));
//        System.out.println("等额本息---每月还款本息：" + getPerMonthPrincipalInterest(invest, yearRate, 6));
//        System.out.println("等额本息---每月还款本息：" + getPerMonthPrincipalInterest(invest, yearRate, 9));
//        System.out.println("等额本息---每月还款本息：" + getPerMonthPrincipalInterest(invest, yearRate, 12));
//        System.out.println("等额本息---每月还款本息：" + getPerMonthPrincipalInterest(invest, yearRate, 15));
//        System.out.println("等额本息---每月还款本息：" + getPerMonthPrincipalInterest(invest, yearRate, 18));
//        /*Map<Integer, BigDecimal> mapInterest = getPerMonthInterest(invest, yearRate, month);
//        System.out.println("等额本息---每月还款利息：" + mapInterest);
//        Map<Integer, BigDecimal> mapPrincipal = getPerMonthPrincipal(invest, yearRate, month);
//        System.out.println("等额本息---每月还款本金：" + mapPrincipal);
//        double count = getInterestCount(invest, yearRate, month);
//        System.out.println("等额本息---总利息：" + count);
//        double principalInterestCount = getPrincipalInterestCount(invest, yearRate, month);
//        System.out.println("等额本息---应还本息总和：" + principalInterestCount);*/
//    }


    /**
     * 等额本金计算获取还款方式为等额本金的每月偿还本金和利息
     *
     * 公式：每月偿还本金=(贷款本金÷还款月数)+(贷款本金-已归还本金累计额)×月利率
     *
     * @param invest
     *            总借款额（贷款本金）
     * @param yearRate
     *            年利率
     * @param month
     *            还款总月数
     * @return 每月偿还本金和利息,不四舍五入，直接截取小数点最后两位
     */
    public static Map<Integer, Double> getPerMonthPrincipalInterest(double invest, double yearRate, int totalMonth) {
        Map<Integer, Double> map = new HashMap<Integer, Double>();
        // 每月本金
        double monthPri = getPerMonthPrincipal(invest, totalMonth);
        // 获取月利率
        double monthRate = yearRate / 12;
        monthRate = new BigDecimal(monthRate).setScale(6, BigDecimal.ROUND_DOWN).doubleValue();
        for (int i = 1; i <= totalMonth; i++) {
            double monthRes = monthPri + (invest - monthPri * (i - 1)) * monthRate;
            monthRes = new BigDecimal(monthRes).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            map.put(i, monthRes);
        }
        return map;
    }

    /**
     * 等额本金计算获取还款方式为等额本金的每月偿还利息
     *
     * 公式：每月应还利息=剩余本金×月利率=(贷款本金-已归还本金累计额)×月利率
     *
     * @param invest
     *            总借款额（贷款本金）
     * @param yearRate
     *            年利率
     * @param month
     *            还款总月数
     * @return 每月偿还利息
     */
    public static Map<Integer, Double> getPerMonthInterest(double invest, double yearRate, int totalMonth) {
        Map<Integer, Double> inMap = new HashMap<Integer, Double>();
        double principal = getPerMonthPrincipal(invest, totalMonth);
        Map<Integer, Double> map = getPerMonthPrincipalInterest(invest, yearRate, totalMonth);
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            BigDecimal principalBigDecimal = new BigDecimal(principal);
            BigDecimal principalInterestBigDecimal = new BigDecimal(entry.getValue());
            BigDecimal interestBigDecimal = principalInterestBigDecimal.subtract(principalBigDecimal);
            interestBigDecimal = interestBigDecimal.setScale(2, BigDecimal.ROUND_DOWN);
            inMap.put(entry.getKey(), interestBigDecimal.doubleValue());
        }
        return inMap;
    }

    /**
     * 等额本金计算获取还款方式为等额本金的每月偿还本金
     *
     * 公式：每月应还本金=贷款本金÷还款月数
     *
     * @param invest
     *            总借款额（贷款本金）
     * @param yearRate
     *            年利率
     * @param month
     *            还款总月数
     * @return 每月偿还本金
     */
    public static double getPerMonthPrincipal(double invest, int totalMonth) {
        BigDecimal monthIncome = new BigDecimal(invest).divide(new BigDecimal(totalMonth), 2, BigDecimal.ROUND_DOWN);
        return monthIncome.doubleValue();
    }

    /**
     * 等额本金计算获取还款方式为等额本金的总利息
     *
     * @param invest
     *            总借款额（贷款本金）
     * @param yearRate
     *            年利率
     * @param month
     *            还款总月数
     * @return 总利息
     */
    public static double getInterestCount(double invest, double yearRate, int totalMonth) {
        BigDecimal count = new BigDecimal(0);
        Map<Integer, Double> mapInterest = getPerMonthInterest(invest, yearRate, totalMonth);

        for (Map.Entry<Integer, Double> entry : mapInterest.entrySet()) {
            count = count.add(new BigDecimal(entry.getValue()));
        }
        return count.doubleValue();
    }

    public static double getPrincipalInterestCount(double invest, double yearRate, int totalmonth) {
        double monthRate = yearRate / 12;
        BigDecimal perMonthInterest = new BigDecimal(invest)
                .multiply(new BigDecimal(monthRate * Math.pow(1 + monthRate, totalmonth)))
                .divide(new BigDecimal(Math.pow(1 + monthRate, totalmonth) - 1), 2, BigDecimal.ROUND_DOWN);
        BigDecimal count = perMonthInterest.multiply(new BigDecimal(totalmonth));
        count = count.setScale(2, BigDecimal.ROUND_DOWN);
        return count.doubleValue();
    }

        /**
         * @param args
         */
        public static void main() {
            double invest = 10000; // 本金
            int month = 12;
            double yearRate = 0.015; // 年利率
            Map<Integer, Double> getPerMonthPrincipalInterest = getPerMonthPrincipalInterest(invest, yearRate, month);
            System.out.println("等额本金---每月本息：" + getPerMonthPrincipalInterest);
            double benjin = getPerMonthPrincipal(invest, month);
            System.out.println("等额本金---每月本金:" + benjin);
            Map<Integer, Double> mapInterest = getPerMonthInterest(invest, yearRate, month);
            System.out.println("等额本金---每月利息:" + mapInterest);
            double count = getInterestCount(invest, yearRate, month);
            System.out.println("等额本金---总利息：" + count);
            double principalInterestCount = getPrincipalInterestCount(invest, yearRate, month);
            System.out.println("等额本金---应还本息总和："+principalInterestCount);
        }
    }
