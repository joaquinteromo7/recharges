package co.com.bancolombia.usecase.provideservice;


import co.com.bancolombia.model.provider.Provider;
import co.com.bancolombia.model.provider.gateways.ProviderRepository;
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
public class ProvideServiceUseCaseTest {

    @InjectMocks
    ProvideServiceUseCase provideServiceUseCase;

    @Mock
    ProviderRepository providerRepository;


    @Test
    public void get() {

        when(providerRepository.get(1))
                .thenReturn(
                        Mono.just(Optional.of(Provider.builder()
                                .state(true).description("Claro").id(1).type(1)
                                .build())
                        )
                );

        Mono<Optional<Provider>> response = provideServiceUseCase.get(1);

        StepVerifier.create(response)
                .expectNext(Optional.of(Provider.builder().state(true).id(1).description("Claro").type(1).build()))
                .verifyComplete();

    }

    @Test
    public void getAll() {

        List<Provider> listProvider = new ArrayList<>();
        listProvider.add(Provider.builder().state(true).type(1).id(1).description("Claro").build());
        listProvider.add(Provider.builder().state(false).type(1).id(2).description("Movistar").build());

        when(providerRepository.getAll())
                .thenReturn(
                        Flux.fromIterable(listProvider)
                );

        Flux<Provider> response = provideServiceUseCase.getAll();

        StepVerifier.create(response)
                .expectNext(Provider.builder().state(true).type(1).id(1).description("Claro").build())
                .expectNext(Provider.builder().state(false).type(1).id(2).description("Movistar").build())
                .verifyComplete();

    }

    @Test
    public void delete() {

        when(providerRepository.delete(1))
                .thenReturn(
                        Mono.just(true)
                );

        Mono<Boolean> response = provideServiceUseCase.delete(1);

        StepVerifier.create(response)
                .expectNext(true)
                .verifyComplete();

    }

    @Test
    public void create() {

        when(providerRepository.create(Provider.builder().build()))
                .thenReturn(
                        Mono.just(Provider.builder()
                                .build()
                        )
                );

        Mono<Provider> response = provideServiceUseCase.create(Provider.builder().build());

        StepVerifier.create(response)
                .expectNext(Provider.builder().build())
                .verifyComplete();

    }

    @Test
    public void update() {

        when(providerRepository.update(Provider.builder().build()))
                .thenReturn(
                        Mono.just(Optional.of(Provider.builder()
                                .build())
                        )
                );

        Mono<Optional<Provider>> response = provideServiceUseCase.update(Provider.builder().build());

        StepVerifier.create(response)
                .expectNext(Optional.of(Provider.builder().build()))
                .verifyComplete();

    }


}