package co.com.bancolombia.provider;

import co.com.bancolombia.model.provider.Provider;
import co.com.bancolombia.provider.persistencia.dao.ProviderDAO;
import co.com.bancolombia.provider.persistencia.entity.ProviderEntity;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProviderAdapterTest{

    @InjectMocks
    ProviderAdapter providerAdapter;

    @Mock
    ProviderDAO providerDAO;

    @Test
    public void create() {

        when(providerDAO.save(ProviderEntity.builder().build()))
                .thenReturn(
                        ProviderEntity.builder()
                                .build()
                );

        Mono<Provider> response = providerAdapter.create(Provider.builder().build());

        StepVerifier.create(response)
                .expectNext(Provider.builder().build())
                .verifyComplete();

    }

    @Test
    public void getAll() {

        List<ProviderEntity> listProvider = new ArrayList<>();
        listProvider.add(ProviderEntity.builder().state(true).build());
        listProvider.add(ProviderEntity.builder().state(false).build());

        when(providerDAO.findAll())
                .thenReturn(
                        listProvider
                );

        Flux<Provider> response = providerAdapter.getAll();

        StepVerifier.create(response)
                .expectNext(Provider.builder().state(true).build())
                .expectNext(Provider.builder().state(false).build())
                .verifyComplete();

    }

    @Test
    public void get() {

        when(providerDAO.findById(0))
                .thenReturn(
                        Optional.of(ProviderEntity.builder()
                                .build())
                );

        Mono<Optional<Provider>> response = providerAdapter.get(0);

        StepVerifier.create(response)
                .expectNext(Optional.of(Provider.builder().build()))
                .verifyComplete();

    }

    @Test
    public void update() {

        when(providerDAO.findById(0))
                .thenReturn(
                        Optional.of(ProviderEntity.builder()
                                .build())
                );

        when(providerDAO.save(ProviderEntity.builder().build()))
                .thenReturn(
                        ProviderEntity.builder()
                                .build()
                );

        Mono<Optional<Provider>> response = providerAdapter.update(Provider.builder().build());

        StepVerifier.create(response)
                .expectNext(Optional.of(Provider.builder().build()))
                .verifyComplete();

    }

    @Test
    public void delete() {

        doNothing().when(providerDAO).deleteById(0);

        Mono<Boolean> response = providerAdapter.delete(0);

        StepVerifier.create(response)
                .expectNext(true)
                .verifyComplete();

    }

    @Test
    public void deleteException() {

        doThrow(new RuntimeException("Add operation not implemented"))
                .when(providerDAO).deleteById(0);

        Mono<Boolean> response = providerAdapter.delete(0);

        StepVerifier.create(response)
                .expectNext(false)
                .verifyComplete();
    }

}