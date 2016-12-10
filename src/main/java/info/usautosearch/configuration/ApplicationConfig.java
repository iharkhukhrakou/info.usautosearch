package info.usautosearch.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.apache.commons.dbcp.BasicDataSource;

import info.usautosearch.dao.VehicleDAO;
import info.usautosearch.dao.JDBCTemplateVehicleDAO;
import info.usautosearch.dao.CustomerDAO;
import info.usautosearch.dao.JDBCTemplateCustomerDAO;
import info.usautosearch.security.UserDetailsServiceImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "info.usautosearch")
@PropertySource(value = { "classpath:jdbc.properties" })
public class ApplicationConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private Environment environment;

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/bootstrap_3.2.0/");
        registry.addResourceHandler("/gallery/**").addResourceLocations("/resources/unitegallery/");
        registry.addResourceHandler("/site/**").addResourceLocations("/resources/stylesheets/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
        registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/");
    }


    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:locales/message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public BasicDataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        if (System.getProperty("com.google.appengine.runtime.version").startsWith("Google App Engine/")) {
            dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.runtime.driverClassName"));
            dataSource.setUrl(environment.getRequiredProperty("jdbc.runtime.url"));
        } else {
            dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
            dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        }
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public VehicleDAO getVehicleDAO() {
        return new JDBCTemplateVehicleDAO(getDataSource());
    }

    @Bean
    public CustomerDAO getCustomerDAO() {
        return new JDBCTemplateCustomerDAO(getDataSource());
    }

    @Bean
    SearchKeys searchKeys() {
        SearchKeys searchKeys;
        searchKeys = getVehicleDAO().initSearchKeys();
        return searchKeys;
    }
}
