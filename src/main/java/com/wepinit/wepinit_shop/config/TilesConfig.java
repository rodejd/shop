package com.wepinit.wepinit_shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
public class TilesConfig {
    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer config = new TilesConfigurer();

        config.setDefinitions(new String[] {
//            "classpath:/tiles/tiles-*.xml"
            "classpath:/tiles/tiles-admin.xml",
            "classpath:/tiles/tiles-front.xml",
            "classpath:/tiles/tiles-front-mob.xml",
            "classpath:/tiles/tiles-seller.xml"
        });
        config.setCheckRefresh(true);   // runtime에 def xml 변경시 반영 여부

        return config;
    }

    @Bean
    public TilesViewResolver tilesViewResolver() {
        TilesViewResolver resolver = new TilesViewResolver();
        resolver.setViewClass(TilesView.class);
        resolver.setOrder(3);

        return resolver;
    }
}
