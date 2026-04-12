package lk.practice.candidate_recruitment_tacker.web.config;

import lk.practice.candidate_recruitment_tacker.web.interceptors.RoleBasedInterceptor;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    //inject role base interceptor
    private final RoleBasedInterceptor roleBasedInterceptor;

    @Override
    public void addInterceptors(
            @NonNull InterceptorRegistry registry
    ){
        //register interceptor and define which routes allocates interceptor
        registry.addInterceptor(roleBasedInterceptor)
                .addPathPatterns("/api/v1/candidates/**");
    }
}