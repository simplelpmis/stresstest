package org.simple.statistics;

import lombok.Data;
import org.simple.config.AppConfig;

import java.util.Collections;
import java.util.List;

/**
 * @Date 2018/1/8      @Author Simba
 * @Description: 基础数据
 */
@Data
public final class CalculateUtil {

    public final static CalculateData calData(AppConfig config, long totalTime, List<Long> resultList, List<BasicData> basicDataList) {
        CalculateData data = new CalculateData();
        Collections.sort(resultList);

        long sum = resultList.stream().reduce(0L, Long::sum);
        data.setMinTime(resultList.get(0));
        data.setMaxTime(resultList.get(config.getThreadCount() - 1));
        data.setAvgTime(sum / config.getThreadCount());
        data.setTimeDetail(resultList.toString());
        data.setInvokeDetail(basicDataList.toString());

        data.setSuccessTimes(basicDataList.stream().map(d -> d.getSuccessTimes()).reduce(0, Integer::sum));
        data.setFailTimes(basicDataList.stream().map(d -> d.getFailTimes()).reduce(0, Integer::sum));
        data.setErrorTimes(basicDataList.stream().map(d -> d.getErrorTimes()).reduce(0, Integer::sum));
        data.setTotalRequest(config.getRequestCount() * config.getThreadCount());
        data.setTotalTime(totalTime);
        data.setTps(data.getSuccessTimes() * 1000 / totalTime);
        return data;
    }

}