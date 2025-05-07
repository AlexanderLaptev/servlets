package ru.vsu.cs.framework.serialization;

import java.util.HashMap;
import java.util.Map;

public class SerializationRegistry {
    private final Map<Class<?>, BodySerializer> serializers = new HashMap<>();

    private BodySerializer defaultSerializer = GsonBodySerializer.INSTANCE;

    public SerializationRegistry() { }

    public SerializationRegistry(BodySerializer defaultSerializer) {
        this.defaultSerializer = defaultSerializer;
    }

    public BodySerializer getDefaultSerializer() {
        return defaultSerializer;
    }

    public void setDefaultSerializer(BodySerializer defaultSerializer) {
        this.defaultSerializer = defaultSerializer;
    }

    public void registerSerializer(final Class<?> clazz, final BodySerializer serializer) {
        serializers.put(clazz, serializer);
    }

    public BodySerializer getSerializerForClass(Class<?> clazz) {
        var serializer = serializers.get(clazz);
        return (serializer == null) ? defaultSerializer : serializer;
    }

    public BodySerializer getSerializerForObject(Object object) {
        return getSerializerForClass(object.getClass());
    }
}
