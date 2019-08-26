package com.taskstorage.uroboros.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

@PropertySource("classpath:application.properties")
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Value("${upload.path}")
    private String uploadPath;

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

    //Для загрузки файлов через multipart/form-data нужен multipartResolver() в MvcConfig и этот конфиг
    @Override
    protected void customizeRegistration(Dynamic registration) {
        //Parameters:-
        //   location - the directory location where files will be stored
        //   maxFileSize - the maximum size allowed for uploaded files
        //   maxRequestSize - the maximum size allowed for multipart/form-data requests
        //   fileSizeThreshold - the size threshold after which files will be written to disk
        MultipartConfigElement multipartConfig = new MultipartConfigElement(uploadPath, 10485760,
                10485760, 0);
        registration.setMultipartConfig(multipartConfig);
    }

}