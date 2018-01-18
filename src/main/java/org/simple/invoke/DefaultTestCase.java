package org.simple.invoke;

/**
 * @Date 2018/1/9      @Author Simba
 * @Description:
 */
public class DefaultTestCase implements TestCase{
    @Override
    public boolean execute() {
        try {
            Thread.currentThread().sleep(1);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }
}