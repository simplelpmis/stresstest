package org.simple.invoke.api;

/**
 * @Date 2018/1/10      @Author Simba
 * @Description:
 */
public class DefaultTestApi implements TestApi {
    @Override
    public boolean execute(String args) {
        try {
            Thread.currentThread().sleep(1);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }
}