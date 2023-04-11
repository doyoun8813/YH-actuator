package hello.order.V3;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import hello.order.OrderService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderServiceV3 implements OrderService {

    private final MeterRegistry registry;
    private AtomicInteger stock = new AtomicInteger(100);

    public OrderServiceV3(MeterRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void order() {
        final Timer timer = Timer.builder("my.order")
            .tag("class", this.getClass().getName())
            .tag("method", "order")
            .description("order")
            .register(registry);

        timer.record(() -> {
            log.info("주문");
            stock.decrementAndGet();
            sleep(500);
        });
    }

    @Override
    public void cancel() {
        final Timer timer = Timer.builder("my.order")
            .tag("class", this.getClass().getName())
            .tag("method", "cancel")
            .description("order")
            .register(registry);

        timer.record(() -> {
            log.info("취소");
            stock.incrementAndGet();
            sleep(200);
        });
    }

    private void sleep(int l) {
        try {
            Thread.sleep(l + new Random().nextInt(200));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}
