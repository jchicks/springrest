package springrest.config;

import javax.annotation.PostConstruct;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration 
@EnableWebMvc 
@ComponentScan(basePackages = "springrest.web.mvc.*")
public class WebConfig extends WebMvcConfigurerAdapter {
  
  @PostConstruct
  public void init() {
    BasicConfigurator.configure();
    PatternLayout layout = new PatternLayout("%d{ISO8601} [%t] %-5p %c %x - %m%n");
    Logger springLogger  = Logger.getLogger("org.springframework");
    Logger rootLogger    = Logger.getRootLogger();
    
    rootLogger.setLevel(Level.TRACE);    
    rootLogger.addAppender(new ConsoleAppender(layout));
    rootLogger.info("sup +++++");
    
    springLogger.setLevel(Level.TRACE);
  }
  
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //Allows us to serve static files from within the webapp.
    registry.addResourceHandler("**").addResourceLocations("/");
  }
}
