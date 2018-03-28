package com.sgo.depanalyze.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;
import org.apache.log4j.Logger;

import com.sgo.depanalyze.util.intf.IByteCodeAgent;
import com.sgo.depanalyze.util.intf.IByteCodeRepository;

public class ByteCodeRepository implements IByteCodeRepository {
    private Map<Class<?>, IByteCodeAgent> BYTE_CODE_AGENT_MAP = new ConcurrentHashMap<Class<?>, IByteCodeAgent>();
    private final int MAX_SIZE = 500;
    /** The logger. */
    private static Logger logger = Logger.getLogger(ByteCodeRepository.class);

    // private Object lockClearCache = new Object();
    static class SingletonHolder {
        static final ByteCodeRepository INSTANCE = new ByteCodeRepository();
    }

    public static ByteCodeRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public IByteCodeAgent loadByteCodeAgent(Class<?> clazz) throws ClassNotFoundException {
        // synchronized (ByteCodeRepository.class) {}
        IByteCodeAgent byteCodeAgent = BYTE_CODE_AGENT_MAP.get(clazz);
        if (byteCodeAgent == null) {
            byteCodeAgent = buildByteCodeAgent(clazz);
            BYTE_CODE_AGENT_MAP.put(clazz, byteCodeAgent);
        }
        return byteCodeAgent;
    }

    @Override
    public synchronized void clearCache() {
        Repository.clearCache();
        BYTE_CODE_AGENT_MAP.clear();
        logger.info("Repository cache cleared by Thread: " + Thread.currentThread().getName());
    }

    private synchronized JavaClass lookupClass(Class<?> clazz) throws ClassNotFoundException {
        if (BYTE_CODE_AGENT_MAP.size() >= MAX_SIZE) {
            clearCache();
        }
        return Repository.lookupClass(clazz);
    }

    private IByteCodeAgent buildByteCodeAgent(Class<?> clazz) throws ClassNotFoundException {
        return new ByteCodeAgent(lookupClass(clazz));
    }
}
