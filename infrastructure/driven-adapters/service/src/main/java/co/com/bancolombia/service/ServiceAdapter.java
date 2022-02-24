package co.com.bancolombia.service;

import co.com.bancolombia.model.recarga.Recarga;
import co.com.bancolombia.model.service.Service;
import co.com.bancolombia.model.service.gateways.ServiceRepository;
import co.com.bancolombia.service.persistencia.dao.ServiceDAO;
import co.com.bancolombia.service.persistencia.entity.ServiceEntity;
import co.com.bancolombia.service.strategy.ServiceStrategyFactory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@org.springframework.stereotype.Service
public class ServiceAdapter implements ServiceRepository {

    private static final Logger logger = LoggerFactory.getLogger(ServiceAdapter.class);

    @Autowired
    private ServiceDAO serviceDAO;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Mono<Boolean> creditAmountToService(Recarga recarga) {
        ServiceStrategyFactory serviceStrategyFactory = new ServiceStrategyFactory();
        Service service = new Service();

        return Mono.just(serviceDAO.findById(recarga.getType()))
                .flatMap(p -> {

                    if (!p.isEmpty() && p.get().getType() != 0) {
                        try {
                            service.setIdCliente(p.get().getIdCliente());
                            service.setCreditAmount(p.get().getCreditAmount() + recarga.getCreditAmount());
                            service.setIdProvider(p.get().getIdProvider());
                            service.setType(p.get().getType());
                            return serviceStrategyFactory.getStrategy(String.valueOf(recarga.getIdProvider())).execute(service)
                                    .flatMap(resp -> {
                                        if (resp) {
                                            this.update(service);
                                            return Mono.just(true);
                                        } else {
                                            return Mono.just(false);
                                        }
                                    });
                        } catch (Exception e) {
                            return Mono.just(false);
                        }
                    } else {
                        try {
                            service.setIdCliente(recarga.getAccountNumber());
                            service.setCreditAmount(recarga.getCreditAmount());
                            service.setIdProvider(recarga.getIdProvider());
                            service.setType(recarga.getType());
                            return serviceStrategyFactory.getStrategy(String.valueOf(recarga.getIdProvider())).execute(service)
                                    .flatMap(resp -> {
                                        if (resp) {
                                            this.create(service);
                                            return Mono.just(true);
                                        } else {
                                            return Mono.just(false);
                                        }
                                    });
                        } catch (Exception e) {
                            return Mono.just(false);
                        }

                    }
                });

    }


    @Override
    public Mono<Service> create(Service service) {
        return Mono.just(modelMapper.map(serviceDAO.save(modelMapper.map(service, ServiceEntity.class)), Service.class));
    }

    @Override
    public Flux<Service> getAll() {
        return Flux.fromIterable(stream(serviceDAO.findAll().spliterator(), false).map(serviceSql -> modelMapper.map(serviceSql, Service.class)).collect(Collectors.toList()));
    }

    @Override
    public Mono<Optional<Service>> get(int number) {
        return Mono.just(serviceDAO.findById(number).
                map(serviceSql ->  modelMapper.map(serviceSql, Service.class)));
    }

    @Override
    public Mono<Optional<Service>> update(Service service) {
        return Mono.just(serviceDAO.findById(service.getType())
                .map(p -> {
                    p.setIdCliente(service.getIdCliente());
                    p.setCreditAmount(service.getCreditAmount());
                    p.setType(service.getType());
                    p.setIdProvider(service.getIdProvider());
                    return serviceDAO.save(modelMapper.map(p, ServiceEntity.class));
                }).map(serviceSql -> modelMapper.map(serviceSql, Service.class)));
    }

    @Override
    public Mono<Boolean> delete(int number) {
        try {
            serviceDAO.deleteById(number);
            return Mono.just(true);
        } catch (Exception e) {
            logger.error("Error", e);
            return Mono.just(false);
        }
    }


}
