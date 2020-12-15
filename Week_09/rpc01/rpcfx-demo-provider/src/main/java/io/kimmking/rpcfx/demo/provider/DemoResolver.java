package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.api.RpcfxResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DemoResolver implements RpcfxResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public <T> T resolve(Class<T> serviceClass) {
        return this.applicationContext.getBean(serviceClass);
    }

    @Override
    public Object resolve(String serviceClass) throws ClassNotFoundException {
        return this.applicationContext.getBean(serviceClass);
    }
}
