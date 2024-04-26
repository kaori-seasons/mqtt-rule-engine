import com.github.ltsopensource.spring.boot.annotation.EnableTaskTracker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableTaskTracker
@ComponentScan("com.bifromq.mqtt")
public class IotReTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotReTaskApplication.class, args);
    }

}
