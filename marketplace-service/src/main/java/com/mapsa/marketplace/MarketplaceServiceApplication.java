package com.mapsa.marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableSwagger2
public class MarketplaceServiceApplication {
    // TODO: 11/27/2020  @ResponseStatus/Database Tables & relations/spring validations

    public static void main(String[] args) {
        SpringApplication.run(MarketplaceServiceApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/*"))
                .apis(RequestHandlerSelectors.basePackage("com.mapsa.marketplace")).build();
    }
    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }

}
