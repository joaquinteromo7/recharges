package co.com.bancolombia.usecase.serviceservice;

import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.model.account.gateways.AccountRepository;
import co.com.bancolombia.model.service.Service;
import co.com.bancolombia.model.service.gateways.ServiceRepository;
import co.com.bancolombia.usecase.accountservice.AccountServiceUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ServiceServiceUseCaseTest {

    @InjectMocks
    ServiceServiceUseCase serviceServiceUseCase;

    @Mock
    ServiceRepository serviceRepository;


    @Test
    public void get() {

        when(serviceRepository.get(0))
                .thenReturn(
                        Mono.just(Optional.of(Service.builder()
                                .build())
                        )
                );

        Mono<Optional<Service>> response = serviceServiceUseCase.get(0);

        StepVerifier.create(response)
                .expectNext(Optional.of(Service.builder().build()))
                .verifyComplete();

    }

    @Test
    public void getAll() {

        List<Service> listService = new ArrayList<>();
        listService.add(Service.builder().build());

        when(serviceRepository.getAll())
                .thenReturn(
                        Flux.fromIterable(listService)
                );

        Flux<Service> response = serviceServiceUseCase.getAll();

        StepVerifier.create(response)
                .expectNext(Service.builder().build())
                .verifyComplete();
    }

    @Test
    public void delete() {

        when(serviceRepository.delete(0))
                .thenReturn(
                        Mono.just(false)
                );

        Mono<Boolean> response = serviceServiceUseCase.delete(0);

        StepVerifier.create(response)
                .expectNext(false)
                .verifyComplete();

    }

    @Test
    public void create() {

        when(serviceRepository.create(Service.builder().build()))
                .thenReturn(
                        Mono.just(Service.builder()
                                .build()
                        )
                );

        Mono<Service> response = serviceServiceUseCase.create(Service.builder().build());

        StepVerifier.create(response)
                .expectNext(Service.builder().build())
                .verifyComplete();

    }

    @Test
    public void update() {

        when(serviceRepository.update(Service.builder().build()))
                .thenReturn(
                        Mono.just(Optional.of(Service.builder()
                                .build())
                        )
                );

        Mono<Optional<Service>> response = serviceServiceUseCase.update(Service.builder().build());

        StepVerifier.create(response)
                .expectNext(Optional.of(Service.builder().build()))
                .verifyComplete();

    }

}