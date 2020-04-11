package com.sj4j.utils;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GsonStore<T> {
    private final String TAG = getClass().getSimpleName();
    private final String fileName;
    private final Class<T> classOfT;

    private Gson gsonTo = new Gson();

    public GsonStore(String fileName, Class<T> classOfT) {
        this.fileName = fileName;
        this.classOfT = classOfT;
    }

    public T load() {
        T t = null;

        File gsonFile = new File(fileName);
        // from JSON file
        if (gsonFile.exists()) {
            try {
                FileReader fileReader = new FileReader(gsonFile);
                t = load(fileReader, classOfT);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    private T load(FileReader fileReader, Class<T> classOfT) {
        T t;
        {
            Gson fromGson = new Gson();
            t = fromGson.fromJson(fileReader, classOfT);
        }
        return t;
    }

    public void save(T t) {
        System.out.println("save(config, " + fileName + ", " + classOfT + ")");
        File gsonFile = new File(fileName);

        // 1. Java object to JSON file
        FileWriter writer;
        try {
            writer = new FileWriter(gsonFile);
            save(t, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save(T t, FileWriter writer) {
        gsonTo.toJson(t, writer);
    }

}
