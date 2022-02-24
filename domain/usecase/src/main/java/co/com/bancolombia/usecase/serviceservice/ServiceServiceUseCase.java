package co.com.bancolombia.usecase.serviceservice;

import co.com.bancolombia.model.service.Service;
import co.com.bancolombia.model.service.gateways.ServiceRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@AllArgsConstructor
public class ServiceServiceUseCase {

    private ServiceRepository serviceRepository;

    public Mono<Optional<Service>> get(int number) {
        return serviceRepository.get(number);
    }

    public Flux<Service> getAll() {
        return serviceRepository.getAll();
    }

    public Mono<Service> create(Service service) {
        return serviceRepository.create(service);
    }

    public Mono<Optional<Service>> update(Service service) {
        return serviceRepository.update(service);
    }

    public Mono<Boolean> delete(int accountNumber) {
        return serviceRepository.delete(accountNumber);
    }

}
