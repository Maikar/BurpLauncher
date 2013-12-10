package main.java.burplauncher.core;

import main.java.burplauncher.shell.controllers.ShellController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringApplicationConfig {

    @Bean
    public ShellController shellController()
    {
        return new ShellController();
    }
}
