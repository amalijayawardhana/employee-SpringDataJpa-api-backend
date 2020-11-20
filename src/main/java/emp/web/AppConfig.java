package emp.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@ComponentScan("emp")
@Configuration
@Import(JPAConfig.class)
public class AppConfig {
}
