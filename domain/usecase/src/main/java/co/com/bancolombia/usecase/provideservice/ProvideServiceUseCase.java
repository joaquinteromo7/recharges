package co.com.bancolombia.usecase.provideservice;

import co.com.bancolombia.model.provider.Provider;
import co.com.bancolombia.model.provider.gateways.ProviderRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@AllArgsConstructor
public class ProvideServiceUseCase {

    private ProviderRepository providerRepository;

    public Mono<Optional<Provider>> get(int number) {
        return providerRepository.get(number);
    }

    public Flux<Provider> getAll() {
        return providerRepository.getAll();
    }

    public Mono<Provider> create(Provider provider) {
        return providerRepository.create(provider);
    }

    public Mono<Optional<Provider>> update(Provider provider) {
        return providerRepository.update(provider);
    }

    public Mono<Boolean> delete(int accountNumber) {
        return providerRepository.delete(accountNumber);
    }
}
