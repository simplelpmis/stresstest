package org.simple.invoke;

import lombok.Data;
import org.simple.statistics.BasicData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

@Data
public class TestTask implements Callable<Long> {

    private static final Logger logger = LoggerFactory.getLogger(TestTask.class);

    private CyclicBarrier cb;
    private CountDownLatch cdl;
    private int requestCount;
    private TestCase testCase;
    private BasicData basicData;

    public TestTask(CyclicBarrier cb, CountDownLatch cdl, int requestCount, TestCase testCase) {
        this.cb = cb;
        this.cdl = cdl;
        this.requestCount = requestCount;
        this.testCase = testCase;
        this.basicData = new BasicData();
    }

    @Override
    public Long call() throws Exception {
        cb.await();// 线程等待，直到达到并发数
        long start = System.currentTimeMillis();
        for (int i = 0; i < requestCount; i++) {
            try {
                boolean reult = testCase.execute();
                if (reult) {
                    basicData.successTimesIncr();
                } else {
                    basicData.failTimesIncr();
                }
            } catch (Exception e) {
                basicData.errorTimesIncr();
            }
        }
        long result = System.currentTimeMillis() - start;
        cdl.countDown();
//        log.info("thread={}, runtimes={}, time={}", Thread.currentThread().getName(), requestCount, result);
        return result;
    }
}
