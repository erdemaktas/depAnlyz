package com.sgo.depanalyze.util.intf;

public interface IByteCodeRepository {
    IByteCodeAgent loadByteCodeAgent(Class<?> clazz) throws ClassNotFoundException;

    void clearCache();
}
