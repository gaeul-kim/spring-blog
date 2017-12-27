package xyz.sangsik.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by sangsik on 2017-12-14.
 */
@Configuration
@EnableJpaAuditing
public class DefaultConfig extends WebMvcConfigurerAdapter {

    /* Default PageRequest */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setOneIndexedParameters(true);
        resolver.setFallbackPageable(new PageRequest(0, 7, Sort.Direction.DESC, "id"));
        argumentResolvers.add(resolver);
        super.addArgumentResolvers(argumentResolvers);
    }

}
