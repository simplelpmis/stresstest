package org.simple.statistics;

import lombok.Data;

/**
 * @Date 2018/1/8      @Author Simba
 * @Description: 基础数据
 */
@Data
public class CalculateData {

    private int successTimes;
    private int failTimes;
    private int errorTimes;
    private long totalRequest;
    private long totalTime;
    private long tps;

    private long minTime;
    private long maxTime;
    private long avgTime;
    private String timeDetail;
    private String invokeDetail;

    public String toSimpleString() {
        return "CalculateData{" +
                "successTimes=" + successTimes +
                ", failTimes=" + failTimes +
                ", errorTimes=" + errorTimes +
                ", totalRequest=" + totalRequest +
                ", totalTime=" + totalTime +
                ", tps=" + tps +
                ", minTime=" + minTime +
                ", maxTime=" + maxTime +
                ", avgTime=" + avgTime +
                '}';
    }
}