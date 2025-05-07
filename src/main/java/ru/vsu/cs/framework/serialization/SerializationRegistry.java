package ru.vsu.cs.framework.serialization;

import java.util.HashMap;
import java.util.Map;

public class SerializationRegistry {
    public static final SerializationRegistry INSTANCE = new SerializationRegistry();

    private final Map<Class<?>, BodySerializer> serializers = new HashMap<>();

    private static final BodySerializer DEFAULT_SERIALIZER = new GsonBodySerializer();

    private SerializationRegistry() { }

    public void registerSerializer(final Class<?> clazz, final BodySerializer serializer) {
        serializers.put(clazz, serializer);
    }

    public BodySerializer getSerializerForClass(Class<?> clazz) {
        var serializer = serializers.get(clazz);
        return (serializer == null) ? DEFAULT_SERIALIZER : serializer;
    }

    public BodySerializer getSerializerForObject(Object object) {
        return getSerializerForClass(object.getClass());
    }
}
