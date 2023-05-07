package com.springboot.advanced_jpa;

import org.junit.jupiter.api.*;

public class testLifeCycle {
    @BeforeAll
    static void beforeAll(){
        System.out.println("## before all Annotation 호출 ##\n");
    }
    @AfterAll
    static void afterAll(){
        System.out.println("## after all Annotation 호출 ##\n");
    }
    @BeforeEach
    void beforeEach(){
        System.out.println("## beforeEach Annotation 호출 ##\n");
    }
    @AfterEach
    void afterEach(){
        System.out.println("## afterEach Annotation 호출 ##\n");
    }

    @Test
    void test1(){
        System.out.println("==test 1 start==\n");
    }
    @Test
    void test2(){
        System.out.println("==test 2 start==\n");
    }
    @Test
    void test3(){
        System.out.println("==test 3 start==\n");
    }
}
