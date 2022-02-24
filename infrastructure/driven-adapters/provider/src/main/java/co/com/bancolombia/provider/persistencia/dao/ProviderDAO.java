package co.com.bancolombia.provider.persistencia.dao;

import co.com.bancolombia.provider.persistencia.entity.ProviderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderDAO extends CrudRepository<ProviderEntity, Integer> {
}
