package yxinfo.yjh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author jn
 * @date 2017/9/14 10:29
 **/
@SpringBootApplication
@ComponentScan( basePackages = "yxinfo.**" )
@EnableCaching
@EnableScheduling
public class Application {

    public static void main( String[] args ) {
        SpringApplication.run( Application.class, args );
    }
}
