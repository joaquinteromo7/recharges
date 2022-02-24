package co.com.bancolombia.model.service.gateways;

import co.com.bancolombia.model.recarga.Recarga;
import co.com.bancolombia.model.service.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface ServiceRepository {

    Mono<Boolean> creditAmountToService(Recarga recarga);

    Mono<Service> create(Service service);

    Flux<Service> getAll();

    Mono<Optional<Service>> get(int number);

    Mono<Optional<Service>> update(Service service);

    Mono<Boolean> delete(int number);

}
