package com.sj4j.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BaseClass {
    static final String TAG = "clazz";
    @SerializedName(TAG)
    private String clazz;

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getClazz() {
        return clazz;
    }

    public BaseClass(String clazz) {
        this.clazz = clazz;
    }
}

class DerivedClassA extends BaseClass {
    @SerializedName("FieldA")
    private String fieldA = "This is a derived class.";

    public DerivedClassA() {
        super("ClassA");
    }
}

class DerivedClassB extends BaseClass {
    @SerializedName("FieldB")
    private String fieldB = "This is ANOTHER derived class.";

    @SerializedName("IntValue")
    private int intValue = 10;

    public DerivedClassB() {
        super("ClassB");
    }
}

class ToSerializeClass {
    @SerializedName("TestString")
    private String testString = "TestStringValue";

    @SerializedName("DerivedClasses")
    private List<BaseClass> derivedClasses;

    public ToSerializeClass(List<BaseClass> derivedClasses) {
        this.derivedClasses = derivedClasses;
    }
}

class ClassDeserializerAdapter implements JsonDeserializer<BaseClass> {
    private String typeName;
    private Gson gson;
    private Map<String, Class<? extends BaseClass>> classTypeRegistry;

    ClassDeserializerAdapter(String typeName) {
        this.typeName = typeName;
        gson = new Gson();
        classTypeRegistry = new HashMap<>();
    }

    void registerClassType(String classTypeName, Class<? extends BaseClass> classType) {
        // registering Types to Strings
        classTypeRegistry.put(classTypeName, classType);
    }

    @Override
    public BaseClass deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement typeElement = jsonObject.get(typeName);
        String method = typeElement.getAsString();
        Class<? extends BaseClass> classType = classTypeRegistry.get(method);
        BaseClass result = gson.fromJson(json, classType);
        return result;
    }
}

public class JsonExampleTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testcase_001() throws Exception {
        // https://stackoverflow.com/questions/33857091/deserialize-list-of-base-classes-to-derived-ones-using-gson

        // **SERIALIZATION PART** (nothing special, simple Gson serialization)
        // Creating a list to pass as parameter to the container class
        List<BaseClass> derivedClasses = new ArrayList<>();
        derivedClasses.add(new DerivedClassA());
        derivedClasses.add(new DerivedClassB());
        derivedClasses.add(new BaseClass("Base"));

        // Creating the container class to be serialized
        ToSerializeClass serializeClass = new ToSerializeClass(derivedClasses);

        Gson gson = new Gson();

        String json = gson.toJson(serializeClass);
        // json = {"TestString":"TestStringValue","DerivedClasses":[{"FieldA":"This is a derived class.","Method":"ClassA"},{"FieldB":"This is ANOTHER derived class.","IntValue":10,"Method":"ClassB"}]}


        // **DESERIALIZATION PART** (with custom deserializer)
        // creating the custom deserializer, which will find the derived class' type as the class' "Method" field value. With that value, it can resolve the type.. see below
        ClassDeserializerAdapter deserializer = new ClassDeserializerAdapter(BaseClass.TAG);

        // registering each Type into the Deserializer's HashMap (key-value pair), where the key (String) must be carried by the object (you can find it in the BaseClass, called "Method")
        deserializer.registerClassType("ClassA", DerivedClassA.class);
        deserializer.registerClassType("ClassB", DerivedClassB.class);
        deserializer.registerClassType("Base", BaseClass.class);
        Gson gsonB = new GsonBuilder().registerTypeAdapter(BaseClass.class, deserializer).create();

        // deserializing
        ToSerializeClass deserialized = gsonB.fromJson(json, ToSerializeClass.class); // CORRECT!
        System.out.println("deserialized: " + deserialized);
    }
}