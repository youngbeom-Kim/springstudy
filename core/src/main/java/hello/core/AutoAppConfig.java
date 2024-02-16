package hello.core;

import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(
//        basePackages = "hello.core.member",
//        basePackageClasses = AutoAppConfig.class, // default > hello.core package 하위 패키지 모두
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    //스프링부트 자체 내에서 체크를 함
//    @Bean(name = "memoryMemberRepository")
//    MemoryMemberRepository memoryMemberRepository() {
//        return new MemoryMemberRepository();
//    }

}
