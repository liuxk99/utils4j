package com.sj4j.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DerivedClassTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testcase_001() throws Exception {
        List<DerivedClass> objList = new ArrayList<>();
        objList.add(new DerivedClass("Thomas", 30));
        objList.add(new DerivedClass("Jet", 7));

        dump(objList);
    }

    private void dump(List<?> baseClassList) {
        for (Object baseClass: baseClassList) {
            System.out.println(baseClass);
        }
    }

    @Test
    public void testcase_002() throws Exception {
        List<?> objectList = genList();
        dump(objectList);
    }

    private List<?> genList() {
        List<DerivedClass> objList = new ArrayList<>();
        objList.add(new DerivedClass("Thomas", 30));
        objList.add(new DerivedClass("Jet", 7));

        return objList;
    }

    @Test
    public void testcase_003() throws Exception {
        List<BaseClass> objectList = (List<BaseClass>) genList();
        dump(objectList);
    }
}