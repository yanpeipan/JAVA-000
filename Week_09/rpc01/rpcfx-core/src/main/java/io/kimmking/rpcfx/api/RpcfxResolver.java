package io.kimmking.rpcfx.api;

import java.lang.reflect.Method;

public interface RpcfxResolver {
    <T> T resolve(Class<T> serviceClass) throws ClassNotFoundException;

    Object resolve(String serviceClass) throws ClassNotFoundException;
}
