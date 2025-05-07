package ru.vsu.cs.framework.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.charset.StandardCharsets;

public class GsonBodySerializer implements BodySerializer {
    public static final GsonBodySerializer INSTANCE = new GsonBodySerializer();

    private GsonBodySerializer() { }

    private static final Gson GSON = new GsonBuilder()
            .create();

    @Override
    public byte[] serialize(Object body) {
        var json = GSON.toJson(body, body.getClass());
        return json.getBytes(StandardCharsets.UTF_8);
    }
}
