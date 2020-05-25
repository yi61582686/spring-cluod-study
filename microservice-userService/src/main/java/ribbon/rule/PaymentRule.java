package ribbon.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentRule {

    @Bean
    public IRule iRule() {
        return new RetryRule();
    }

}
