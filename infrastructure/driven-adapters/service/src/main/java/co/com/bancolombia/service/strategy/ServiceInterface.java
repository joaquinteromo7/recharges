package co.com.bancolombia.service.strategy;

import co.com.bancolombia.model.service.Service;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface ServiceInterface {
    Mono<Boolean> execute(Service service);
}
