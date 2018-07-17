package com.xuhailiang5794.ik.config.swagger;

import com.xuhailiang5794.ik.support.cons.AppConstant;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/16
 */
@EnableWebMvc
@EnableSwagger2
@Configuration
public class Swagger2Config extends WebMvcConfigurationSupport {

    @Bean
    public Docket createApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name(AppConstant.JWT_HEADER)
                .description("令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        List<Parameter> operationParameters = new ArrayList<>(1);
        operationParameters.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xuhailiang5794"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("IK Rest API")
                .version("1.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .license("Apache 2.0")
                .description("此API为开发人员，测试人员，UI人员提供方便快捷的API开发，测试体验")
                .build();
    }
}
