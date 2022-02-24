package co.com.bancolombia.account.persistencia.dao;

import co.com.bancolombia.account.persistencia.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO extends CrudRepository<AccountEntity, Long > {
}
