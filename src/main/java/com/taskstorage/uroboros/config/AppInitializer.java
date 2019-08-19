package com.taskstorage.uroboros.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.Filter;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Конфиг для Spring ContextLoaderListener
    @Override
    protected Class <?> [] getRootConfigClasses() {
        return new Class<?>[] { SecurityConfig.class, DbConfig.class, EncryptionConfig.class};
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

//    Doesn't work with spring security
//    @Override
//    protected Filter[] getServletFilters() {
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        return new Filter[] { characterEncodingFilter};
//    }
}