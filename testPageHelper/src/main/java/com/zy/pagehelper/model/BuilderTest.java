package com.zy.pagehelper.model;

import org.junit.Test;

/**
 * @author Administrator
 * @date 2020/11/17 10:18
 **/

public class BuilderTest {

    @lombok.Builder
    @lombok.Data
    private static class Builder {
        @lombok.Builder.Default//使用builder注解，如果给对象赋默认值，需要使用@lombok.Builder.Default注解
        private String name = "1232";
    }

    @Test
    public void test() {
        Builder builder = Builder.builder().build();
        System.out.println(builder.getName());
    }

}
