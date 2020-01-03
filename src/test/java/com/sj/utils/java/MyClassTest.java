package com.sj.utils.java;

import static org.junit.Assert.*;

public class MyClassTest {

    private MyClass obj;

    @org.junit.Before
    public void setUp() throws Exception {
        obj = new MyClass();
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void dump() {
        obj.dump();
    }
}