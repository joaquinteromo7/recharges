package co.com.bancolombia.service.strategy;

import co.com.bancolombia.model.service.Service;
import reactor.core.publisher.Mono;

public class MovistarAdapter implements  ServiceInterface{
    @Override
    public Mono<Boolean> execute(Service service) {
        // reglas de negocio para Movistar
        return Mono.just(false);
    }
}
