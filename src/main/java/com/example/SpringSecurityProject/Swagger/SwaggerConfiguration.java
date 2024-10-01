package com.example.SpringSecurityProject.Swagger;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.SecurityConfig;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
////Bu anotasyon, sınıfı Spring için bir yapılandırma sınıfı olarak tanımlar. Spring, bu sınıfın içerisinde
//// tanımlanan bean'leri otomatik olarak tanır ve yönetir.
@Configuration
//
////Bu anotasyon, Swagger 2 desteğini etkinleştirir. Spring Boot uygulamasında
//// Bu sayede Swagger, API'larını otomatik olarak tarar ve belgeler.
//@EnableSwagger2
public class SwaggerConfiguration {
//
//    //Bu anotasyon, metodu bir Spring bean olarak tanımlar. Bu bean, Spring'in IoC  konteyneri tarafından yönetilecektir.
//    @Bean
//
//    //Bu metod, Swagger için ana yapılandırmayı temsil eden bir Docket nesnesi döndürür.
//    // Docket, Swagger'ın API belgelerini nasıl oluşturacağını belirleyen bir yapı taşıdır.
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select() //hangi API'ların ve yolların (endpoints) dokümantasyona dahil edileceğini belirlemeye başlar.
//                .apis(RequestHandlerSelectors.any()) //apis() metodu, Swagger belgelerinde yer alacak API'ları seçmek için kullanılır. RequestHandlerSelectors.any() seçimi, projenin içindeki tüm API'ları dahil eder.
//                .paths(PathSelectors.any()) //Swagger dokümantasyonunda yer alacak URL yollarını seçmek için kullanılır. PathSelectors.any() seçimi, tüm (URL) yolları (endpoint'leri) dahil eder.
//                .build() //Bu metod, Docket nesnesinin yapılandırmasını tamamlar ve oluşturur.
//                .pathMapping("/"); //API yollarının temel yolunun kök (/) olacağını belirtir.
//    }


    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder() //GroupedOpenApi: springdoc-openapi için bir yapılandırma sınıfıdır ve Swagger/OpenAPI belgelerinin nasıl oluşturulacağını belirtir.
                .group("public") // Bu grup adı Swagger UI'da görünür
                .pathsToMatch("/**") //Tüm yolları (endpoints) belgelemeyi seçer.
                .build();
//
//                .addOpenApiCustomizer(openApi -> openApi
//                .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
//                .components(new Components()
//                        .addSecuritySchemes("basicAuth", new SecurityScheme()
//                                .type(SecurityScheme.Type.HTTP)
//                                .scheme("basic"))))
//                .build();


                
    }




}
