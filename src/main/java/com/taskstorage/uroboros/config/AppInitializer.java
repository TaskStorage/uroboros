package com.taskstorage.uroboros.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Конфиг для Spring ContextLoaderListener
    @Override
    protected Class <?> [] getRootConfigClasses() {
        return new Class<?>[] { DbConfig.class};
    }
    // Конфиг для Dispatcher Servlet
    @Override
    protected Class <?> [] getServletConfigClasses() {
        return new Class<?>[] { MvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}