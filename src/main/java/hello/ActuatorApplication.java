package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import hello.order.V2.OrderConfigV2;
import hello.order.V3.OrderConfigV3;
import hello.order.v0.OrderConfigV0;
import hello.order.v0.OrderServiceV0;
import hello.order.v1.OrderConfigV1;

//@Import(OrderConfigV0.class)
//@Import(OrderConfigV1.class)
// @Import(OrderConfigV2.class)
@Import(OrderConfigV3.class)
@SpringBootApplication(scanBasePackages = "hello.controller")
public class ActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActuatorApplication.class, args);
    }

    @Bean
    public InMemoryHttpExchangeRepository inMemoryHttpExchangeRepository() {
        return new InMemoryHttpExchangeRepository();
    }

}
