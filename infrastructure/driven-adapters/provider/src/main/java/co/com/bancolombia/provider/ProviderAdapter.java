package co.com.bancolombia.provider;

import co.com.bancolombia.model.provider.Provider;
import co.com.bancolombia.model.provider.gateways.ProviderRepository;
import co.com.bancolombia.provider.persistencia.dao.ProviderDAO;
import co.com.bancolombia.provider.persistencia.entity.ProviderEntity;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Service
public class ProviderAdapter implements ProviderRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProviderAdapter.class);

    @Autowired
    private ProviderDAO providerDAO;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Mono<Provider> create(Provider provider) {
        return Mono.just(modelMapper.map(providerDAO.save(modelMapper.map(provider, ProviderEntity.class)), Provider.class));
    }

    @Override
    public Flux<Provider> getAll() {
        return Flux.fromIterable(stream(providerDAO.findAll().spliterator(), false).map(providerSql -> modelMapper.map(providerSql, Provider.class)).collect(Collectors.toList()));
    }

    @Override
    public Mono<Optional<Provider>> get(int number) {
        return Mono.just(providerDAO.findById(number).
                map(providerSql -> modelMapper.map(providerSql, Provider.class)));
    }

    @Override
    public Mono<Optional<Provider>> update(Provider provider) {
        return Mono.just(providerDAO.findById(provider.getId())
                .map(p -> {
                    p.setDescription(provider.getDescription());
                    p.setState(provider.isState());
                    p.setType(provider.getType());
                    return providerDAO.save(modelMapper.map(p, ProviderEntity.class));
                }).map(providerSql -> modelMapper.map(providerSql, Provider.class)));
    }

    @Override
    public Mono<Boolean> delete(int number) {
        try {
            providerDAO.deleteById(number);
            return Mono.just(true);
        } catch (Exception e) {
            logger.error("Error", e);
            return Mono.just(false);
        }
    }

}
