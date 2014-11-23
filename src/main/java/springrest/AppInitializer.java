package springrest;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import springrest.config.WebConfig;

public class AppInitializer implements WebApplicationInitializer {
  
  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext rootContext = 
      new AnnotationConfigWebApplicationContext();
    rootContext.register(WebConfig.class);

    Dynamic dispatcher = servletContext.addServlet(
        "dispatcher", new DispatcherServlet(rootContext));
    
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/*");
    
    servletContext.addListener(new ContextLoaderListener(rootContext));
  }  
}
