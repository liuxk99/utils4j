package com.sj4j.utils;

import com.fasterxml.uuid.Generators;
import com.sj4j.utils.MyClass;

import java.util.UUID;

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

    @org.junit.Test
    public void testcase_guid_fasterxml() {
        // Generate time-based UUID
        UUID uuid1 = Generators.timeBasedGenerator().generate();
        dumpUUID(uuid1);

        // Generate random UUID
        UUID uuid2 = Generators.randomBasedGenerator().generate();
        dumpUUID(uuid2);
    }

    private void dumpUUID(UUID uuid) {
        System.out.println("UUID : " + uuid);
        System.out.println("UUID Version : " + uuid.version());
        System.out.println("UUID Variant : " + uuid.variant());
    }

    @org.junit.Test
    public void testcase_guid_java() {
        //initialize uuid
        UUID uuid = UUID.randomUUID();
        dumpUUID(uuid);
    }
}