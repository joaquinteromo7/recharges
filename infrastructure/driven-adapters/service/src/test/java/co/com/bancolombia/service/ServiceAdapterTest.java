package co.com.bancolombia.service;

import co.com.bancolombia.model.recarga.Recarga;
import co.com.bancolombia.model.service.Service;
import co.com.bancolombia.model.service.gateways.ServiceRepository;
import co.com.bancolombia.service.persistencia.dao.ServiceDAO;
import co.com.bancolombia.service.persistencia.entity.ServiceEntity;
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

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ServiceAdapterTest {

    @InjectMocks
    ServiceAdapter serviceAdapter;

    @Mock
    ServiceDAO serviceDAO;
    @Mock
    ServiceRepository serviceAdapterMock;

    @Test
    public void creditAmountToServiceException() {
        when(serviceDAO.findById(0))
                .thenReturn(
                        Optional.of(ServiceEntity.builder()
                                .build())
                );

        when(serviceAdapterMock.update(Service.builder().build()))
                .thenReturn(
                        Mono.just(Optional.of(Service.builder()
                                .build()))
                );

        Mono<Boolean> response = serviceAdapter.creditAmountToService(Recarga.builder().build().builder().type(0).creditAmount(0).idProvider(0).build());

        StepVerifier.create(response)
                .expectNext(false)
                .verifyComplete();

    }

    @Test
    public void creditAmountToServiceUpdateClaro() {
        when(serviceDAO.findById(0))
                .thenReturn(
                        Optional.of(ServiceEntity.builder().type(1)
                                .build())
                );

        when(serviceAdapterMock.update(Service.builder().build()))
                .thenReturn(
                        Mono.just(Optional.of(Service.builder()
                                .build()))
                );

        Mono<Boolean> response = serviceAdapter.creditAmountToService(Recarga.builder().build().builder().type(0).creditAmount(0).idProvider(1).build());

        StepVerifier.create(response)
                .expectNext(true)
                .verifyComplete();

    }

    @Test
    public void creditAmountToServiceUpdateMovistar() {
        when(serviceDAO.findById(0))
                .thenReturn(
                        Optional.of(ServiceEntity.builder().type(1)
                                .build())
                );

        when(serviceAdapterMock.update(Service.builder().build()))
                .thenReturn(
                        Mono.just(Optional.of(Service.builder()
                                .build()))
                );

        Mono<Boolean> response = serviceAdapter.creditAmountToService(Recarga.builder().build().builder().type(0).creditAmount(0).idProvider(2).build());

        StepVerifier.create(response)
                .expectNext(false)
                .verifyComplete();

    }

    /*@Test
    public void creditAmountToServiceCreate() {
        when(serviceDAO.findById(0))
                .thenReturn(
                        Optional.of(ServiceEntity.builder().build())
                );

        when(serviceAdapterMock.create(Service.builder().build()))
                .thenReturn(
                        Mono.just(Service.builder().idProvider(0).creditAmount(0).type(1).idCliente(0)
                                .build())
                );

        Mono<Boolean> response = serviceAdapter.creditAmountToService(Recarga.builder().build().builder().type(0).creditAmount(0).idProvider(1).build());

        StepVerifier.create(response)
                .expectNext(true)
                .verifyComplete();

    }*/

    @Test
    public void create() {
        when(serviceDAO.save(ServiceEntity.builder().build()))
                .thenReturn(
                        ServiceEntity.builder()
                                .build()
                );

        Mono<Service> response = serviceAdapter.create(Service.builder().type(0).creditAmount(0).idCliente(0).idProvider(0).build());

        StepVerifier.create(response)
                .expectNext(Service.builder().build())
                .verifyComplete();
    }

    @Test
    public void getAll() {
        List<ServiceEntity> listService = new ArrayList<>();
        listService.add(ServiceEntity.builder().type(0).build());
        listService.add(ServiceEntity.builder().type(0).build());

        when(serviceDAO.findAll())
                .thenReturn(
                        listService
                );

        Flux<Service> response = serviceAdapter.getAll();

        StepVerifier.create(response)
                .expectNext(Service.builder().type(0).build())
                .expectNext(Service.builder().type(0).build())
                .verifyComplete();

    }

    @Test
    public void get() {
        when(serviceDAO.findById(0))
                .thenReturn(
                        Optional.of(ServiceEntity.builder()
                                .build())
                );

        Mono<Optional<Service>> response = serviceAdapter.get(0);

        StepVerifier.create(response)
                .expectNext(Optional.of(Service.builder().build()))
                .verifyComplete();

    }

    @Test
    public void update() {
        when(serviceDAO.findById(0))
                .thenReturn(
                        Optional.of(ServiceEntity.builder()
                                .build())
                );

        when(serviceDAO.save(ServiceEntity.builder().build()))
                .thenReturn(
                        ServiceEntity.builder()
                                .build()
                );

        Mono<Optional<Service>> response = serviceAdapter.update(Service.builder().build());

        StepVerifier.create(response)
                .expectNext(Optional.of(Service.builder().build()))
                .verifyComplete();
    }

    @Test
    public void delete() {

        doNothing().when(serviceDAO).deleteById(0);

        Mono<Boolean> response = serviceAdapter.delete(0);

        StepVerifier.create(response)
                .expectNext(true)
                .verifyComplete();

    }

    @Test
    public void deleteException() {

        doThrow(new RuntimeException("Add operation not implemented"))
                .when(serviceDAO).deleteById(0);

        Mono<Boolean> response = serviceAdapter.delete(0);

        StepVerifier.create(response)
                .expectNext(false)
                .verifyComplete();

    }
}