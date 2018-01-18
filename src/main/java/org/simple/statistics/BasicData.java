package org.simple.statistics;

import lombok.Data;

/**
 * @Date 2018/1/8      @Author Simba
 * @Description: 基础数据
 */
@Data
public class BasicData {
    private int errorTimes;
    private int failTimes;
    private int successTimes;

    public void errorTimesIncr() {
        errorTimes++;
    }

    public void failTimesIncr() {
        failTimes++;
    }

    public void successTimesIncr() {
        successTimes++;
    }
}