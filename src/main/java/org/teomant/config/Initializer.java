package org.teomant.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;


public class Initializer implements WebApplicationInitializer{

    @Override
    public void onStartup( ServletContext servletContext ){
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register( WebAppConfig.class, DateTimeConfiguration.class );
        servletContext.addListener( new ContextLoaderListener( ctx ) );

        ctx.setServletContext( servletContext );

        ServletRegistration.Dynamic servlet =
                servletContext.addServlet( "dispatcher" , new DispatcherServlet( ctx ) );
        servlet.addMapping( "/" );
        servlet.setLoadOnStartup( 1 );
    }
}