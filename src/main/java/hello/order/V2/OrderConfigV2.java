package hello.order.V2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class OrderConfigV2 {

    @Bean
    public OrderServiceV2 orderServiceV2(){
        return new OrderServiceV2();
    }

    @Bean
    public CountedAspect countedAspect(MeterRegistry registry){
        return new CountedAspect(registry);
    }
}
