package com.sj4j.utils;

import com.fasterxml.uuid.Generators;

import java.io.IOException;
import java.util.Properties;
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

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UUID uuid2 = Generators.timeBasedGenerator().generate();
        dumpUUID(uuid2);

        // Generate random UUID
        UUID uuid3 = Generators.randomBasedGenerator().generate();
        dumpUUID(uuid3);
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

    @org.junit.Test
    public void testcase_uuid_str() {
        // creating UUID
        UUID uuid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");

        // checking UUID value
        dumpUUID(uuid);
    }
}