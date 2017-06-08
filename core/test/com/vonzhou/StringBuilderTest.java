package com.vonzhou;

import org.springframework.util.StopWatch;

/**
 * @version 2017/6/8.
 */
public class StringBuilderTest {
    private static final int CNT = 10000;

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("+=");
        String s = "start";
        for (int i = 0; i < CNT; i++) {
            s += "i";
        }
        stopWatch.stop();

        stopWatch.start("StringBuilder");
        StringBuilder builder = new StringBuilder("start again");
        for (int i = 0; i < CNT; i++) {
            builder.append(i);
        }
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }
}
