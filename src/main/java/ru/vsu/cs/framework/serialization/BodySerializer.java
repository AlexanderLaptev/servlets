package ru.vsu.cs.framework.serialization;

public interface BodySerializer {
    byte[] serialize(Object body);
}
