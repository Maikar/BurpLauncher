package BurpLauncher.Core;

import BurpLauncher.Shell.Controllers.ShellController;
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
