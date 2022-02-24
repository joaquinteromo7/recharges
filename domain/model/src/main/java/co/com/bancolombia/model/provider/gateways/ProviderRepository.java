package co.com.bancolombia.model.provider.gateways;

import co.com.bancolombia.model.provider.Provider;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface ProviderRepository {

    Mono<Provider> create(Provider provider);

    Flux<Provider> getAll();

    Mono<Optional<Provider>> get(int number);

    Mono<Optional<Provider>> update(Provider provider);

    Mono<Boolean> delete(int number);

}
