package com.wepinit.wepinit_shop.config;

import com.wepinit.wepinit_shop.xmall.common.exception.CustomSimpleMappingExceptionResolver;
import com.wepinit.wepinit_shop.xmall.common.interceptor.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.PathExtensionContentNegotiationStrategy;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.view.*;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * <pre>
     * media type enum
     * TODO - 상수 모음 객체로 옮기기
     * </pre>
     */
    private enum MEDIA_TYPE {
        XLS("application/vnd.ms-excel"),
        XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
        XLSXS("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
        ;

        MEDIA_TYPE(String str) { this.value = str; }

        private final String value;
        public String value() { return value; }
    }

    // ===================== Interceptor =====================
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
        url 패턴
        한글자 "/front/?"
        1뎁스 "/front/*"
        2뎁스 이상 "/front/**"
         */
        registry.addInterceptor(getWebContentInterceptor())
            .addPathPatterns("/**")
            .order(1)
            ;
        registry.addInterceptor(getInterceptor())
            .addPathPatterns("/**")
            .order(2)
        ;
    }

    // interceptor 객체 관리
    // new 인스턴스 생성시 spring에서 관리 안하므로 의존성 주입 불가
    // Bean 어노테이션으로 설정
    @Bean
    public WebContentInterceptor getWebContentInterceptor() {
        WebContentInterceptor interceptor = new WebContentInterceptor();
        interceptor.setCacheSeconds(0);
        interceptor.setUseExpiresHeader(true);
        interceptor.setUseCacheControlHeader(true);
        interceptor.setUseCacheControlNoStore(true);

        return interceptor;
    }

    @Bean
    public Interceptor getInterceptor() {
        return new Interceptor();
    }


    // ===================== View Name Resolver =====================
    @Bean
    public DefaultRequestToViewNameTranslator viewNameTranslator() {
        return new DefaultRequestToViewNameTranslator();
    }


    // ===================== View Resolver =====================
    /**
     * 1. ContentNegotiatingViewResolver
     */
    public ContentNegotiationManager getContentNegotiationManager() {
        Map<String, MediaType> mediaTypeMap = new HashMap<>();
        mediaTypeMap.put("dojson", MediaType.APPLICATION_JSON);
        mediaTypeMap.put("doxml", MediaType.APPLICATION_XML);
        mediaTypeMap.put("doajax", MediaType.APPLICATION_JSON);
        mediaTypeMap.put("doxls", MediaType.valueOf(MEDIA_TYPE.XLS.value));
        mediaTypeMap.put("doxlsx", MediaType.valueOf(MEDIA_TYPE.XLSX.value));
        mediaTypeMap.put("doxlsxs", MediaType.valueOf(MEDIA_TYPE.XLSXS.value));

        PathExtensionContentNegotiationStrategy strategy = new PathExtensionContentNegotiationStrategy(mediaTypeMap);

        return new ContentNegotiationManager(strategy);
    }

    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setOrder(1);
        resolver.setContentNegotiationManager(getContentNegotiationManager());

        List<View> views = new ArrayList<>();
        views.add(doAjax());
        views.add(doJson());
        resolver.setDefaultViews(views);

        return resolver;
    }

    @Bean(name = "dojson")
    public MappingJackson2JsonView doJson() {
        return new MappingJackson2JsonView();
    }
    @Bean(name = "doajax")
    public MappingJackson2JsonView doAjax() {
        return new MappingJackson2JsonView();
    }

    /**
     * 2. BeanNameViewResolver
     */
    @Bean(name = "viewResolver")
    public BeanNameViewResolver beanNameViewResolver() {
        BeanNameViewResolver viewResolver = new BeanNameViewResolver();
        viewResolver.setOrder(2);

        return viewResolver;
    }

    /**
     * 4. jsp, 우선순위 최하위
     */
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setOrder(Ordered.LOWEST_PRECEDENCE);

        return viewResolver;
    }


    // ===================== Custom Exception Resolver =====================
    @Bean
    public CustomSimpleMappingExceptionResolver exceptionResolver() {
        CustomSimpleMappingExceptionResolver resolver = new CustomSimpleMappingExceptionResolver();

        // exception mapping
        Properties prop = new Properties();
        prop.setProperty("com.wepinit.wepinit_shop.xmall.common.exception.BizException", "error/exception/bizError");

        resolver.setAjaxErrorView("");
        resolver.setAjaxDefaultErrorMessage("");
        resolver.setAjaxShowTechMessage(true);
        resolver.setDefaultExceptionCode("common.00001");
        resolver.setDefaultErrorView("error/exception/error");
        resolver.setExceptionMappings(prop);

        return resolver;
    }


    // ===================== Multipart Resolver =====================
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(999000000);

        return resolver;
    }

    @Bean(name = "uploadPath")
    public String uploadPath() {
        return "C:/eGovFrameDev-3.9.0-64bit/workspace/Ritzmall/src/main/webapp";
    }



}
