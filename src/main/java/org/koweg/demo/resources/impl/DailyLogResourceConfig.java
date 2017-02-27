package org.koweg.demo.resources.impl;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
@ApplicationPath("/")
public class DailyLogResourceConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        System.out.println("###################################### LOADING RESOURCE SERVLETS ###############");
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(DailyLogsResourceImpl.class);
        return classes;
    }

}
