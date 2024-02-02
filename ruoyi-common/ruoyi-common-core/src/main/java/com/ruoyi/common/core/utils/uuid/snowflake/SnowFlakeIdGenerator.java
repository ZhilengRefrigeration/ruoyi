package com.ruoyi.common.core.utils.uuid.snowflake;

import java.util.HashMap;
import java.util.Map;

/**
 * 雪花算法下的ID生成器
 *
 * @author Alan Scipio
 * @since 2022/7/15
 */
public class SnowFlakeIdGenerator {

    //默认id生成器（不指定机器id和数据中心id）
    private static final String DEFAULT_SEQ = "defaultSeq";
    private static final Map<String, Sequence> SEQUENCE_MAP = new HashMap<>();

    private SnowFlakeIdGenerator() {
    }

    /**
     * 本项目的基础设定
     */
    private static SequenceBuilder defaultBuilder() {
        return SequenceBuilder.builder()
                .setTwepoch(1657864986440L) //起始时间戳
                .setTimestampBits(40L)      //时间戳的bit位数
                .setDatacenterIdBits(1L)    //数据中心的bit位数
                .setWorkerIdBits(2L)        //机器id的bit位数
                .setSequenceBits(4L);       //序列号的bit位数
    }

    /**
     * 雪花算法下，生成ID
     */
    public static long nextIdLong() {
        Sequence sequence = SEQUENCE_MAP.computeIfAbsent(DEFAULT_SEQ, key -> defaultBuilder().build());
        return sequence.nextId();
    }

    /**
     * 雪花算法下，生成ID
     *
     * @param workerId     机器ID
     * @param datacenterId 数据中心ID
     */
    public static long nextIdLong(long workerId, long datacenterId) {
        Sequence sequence = SEQUENCE_MAP.computeIfAbsent(workerId + "-" + datacenterId, key -> defaultBuilder()
                .setWorkerId(workerId)
                .setDatacenterId(datacenterId)
                .build());
        return sequence.nextId();
    }

    public static String nextId() {
        return nextIdLong() + "";
    }

    public static String nextId(long workerId, long datacenterId) {
        return nextIdLong(workerId, datacenterId) + "";
    }

}
