package org.simple;

import org.simple.config.AppConfig;
import org.simple.config.AppConfigUtil;
import org.simple.invoke.TestCaseFactory;
import org.simple.invoke.TestTask;
import org.simple.statistics.BasicData;
import org.simple.statistics.CalculateData;
import org.simple.statistics.CalculateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Date 2017/12/12      @Author Simba
 * @Description: 程序入口类
 */
public class Main {

    private final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        // load config
        AppConfig config = AppConfigUtil.getAppConfig();

        // prepare task and data
        CyclicBarrier cb = new CyclicBarrier(config.getThreadCount());
        CountDownLatch cdl = new CountDownLatch(config.getThreadCount());

        List<TestTask> tasks = new ArrayList<>(config.getThreadCount());
        for (int i = 0; i < config.getThreadCount(); i++) {
            tasks.add(new TestTask(cb, cdl, config.getRequestCount(), TestCaseFactory.newTestCase(config)));
        }

        ExecutorService executor = Executors.newFixedThreadPool(config.getThreadCount());

        // execute
        logger.info("successfully prepare test data with config={}", config);
        long start = System.currentTimeMillis();
        List<Future<Long>> invokeList = executor.invokeAll(tasks);
        cdl.await();
        long totalTime = System.currentTimeMillis() - start;

        //clean up
        executor.shutdown();

        // statistic result
        List<Long> resultList = new ArrayList<>(config.getThreadCount());
        for (int i = 0; i < config.getThreadCount(); i++) {
            long taskResult = invokeList.get(i).get();
            resultList.add(taskResult);
        }

        List<BasicData> basicDataList = tasks.parallelStream().map(t -> t.getBasicData()).collect(Collectors.toList());
        CalculateData result = CalculateUtil.calData(config, totalTime, resultList, basicDataList);
        logger.info("finish test with result={}", result.toSimpleString());
    }
}