package info.usautosearch.configuration;

import info.usautosearch.dao.JDBCTemplateVehicleDAO;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.commons.dbcp.BasicDataSource;

import info.usautosearch.dao.VehicleDAO;

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
    public BasicDataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public VehicleDAO getVehicleDAO() {
        return new JDBCTemplateVehicleDAO(getDataSource());
    }

    @Bean
    SearchKeys searchKeys() {
        return new SearchKeys();
    }
}
