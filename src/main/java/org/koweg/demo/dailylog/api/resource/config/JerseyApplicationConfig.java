package org.koweg.demo.dailylog.api.resource.config;

import java.util.Set;

import javax.ws.rs.core.Configurable;

import org.koweg.demo.dailylog.api.resource.impl.RegistrationsResourceImpl;
import org.koweg.demo.resources.impl.DailyLogsResourceImpl;
import org.koweg.demo.rest.BaseJerseyApplicationConfig;

public class JerseyApplicationConfig extends BaseJerseyApplicationConfig {

    public JerseyApplicationConfig() {
        // packages(RegistrationsResourceImpl.class.getPackage().getName(),
        // DailyLogsResourceImpl.class.getPackage().getName());
    }

    @Override
    protected void registerResources(Configurable<?> config) {
        System.out.println("############### LOADING RESOURCES ###############");
        super.registerResources(config);
        // config.register(RegistrationsResourceImpl.class);
        // config.register(DailyLogsResourceImpl.class);
        register(RegistrationsResourceImpl.class);
        register(DailyLogsResourceImpl.class);
        // packages(true, "org.koweg.demo.dailylog.api.resource");
    }

    // @Override
    // public Set<Class<?>> getClasses() {
    // System.out.println("###################################### LOADING
    // RESOURCE SERVLETS ###############");
    // final Set<Class<?>> classes = new HashSet<Class<?>>();
    // classes.add(DailyLogsResourceImpl.class);
    // classes.add(RegistrationsResourceImpl.class);
    // return classes;
    // }

}
