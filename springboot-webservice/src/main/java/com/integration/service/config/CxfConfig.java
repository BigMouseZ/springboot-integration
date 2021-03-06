package com.integration.service.config;

import com.integration.service.HiKWebService;
import com.integration.service.ServerNameSpaceInterceptor;
import com.integration.service.impl.HiKWebServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.xml.ws.Endpoint;



//@Configuration
public class CxfConfig {

    @Bean
    public ServletRegistrationBean dispatcherServlet() {
        return new ServletRegistrationBean(new CXFServlet(),"/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }
    @Bean
    public HiKWebService demoService() {
        return new HiKWebServiceImpl();
    }
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), demoService());
       endpoint.publish("/ThirdBayonetService");
        // endpoint.publish("/services/ThirdBayonetService");
        endpoint.getInInterceptors().add(new ServerNameSpaceInterceptor());
        return endpoint;
    }

}