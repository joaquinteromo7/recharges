package co.com.bancolombia.service.strategy;

import co.com.bancolombia.model.service.Service;
import co.com.bancolombia.service.ServiceAdapter;
import reactor.core.publisher.Mono;


public class ClaroAdapter implements ServiceInterface {

    private ServiceAdapter serviceAdapter = new ServiceAdapter();

    @Override
    public Mono<Boolean> execute(Service service) {
        // reglas de negocio para Claro

        return Mono.just(true);
    }
}
