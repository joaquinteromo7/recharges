package co.com.bancolombia.service.persistencia.dao;

import co.com.bancolombia.service.persistencia.entity.ServiceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDAO extends CrudRepository<ServiceEntity, Integer> {
}
