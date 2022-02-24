package co.com.bancolombia.account;

import co.com.bancolombia.account.persistencia.dao.AccountDAO;
import co.com.bancolombia.account.persistencia.entity.AccountEntity;
import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.model.account.gateways.AccountRepository;
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
public class AccountAdapter implements AccountRepository {

    private static final Logger logger = LoggerFactory.getLogger(AccountAdapter.class);

    @Autowired
    private AccountDAO accountDAO;
    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public Mono<Account> create(Account account) {
        return Mono.just(modelMapper.map(accountDAO.save(modelMapper.map(account, AccountEntity.class)), Account.class));
    }

    @Override
    public Flux<Account> getAll() {
        return Flux.fromIterable(stream(accountDAO.findAll().spliterator(), false).map(accountSql -> modelMapper.map(accountSql, Account.class)).collect(Collectors.toList()));
    }

    @Override
    public Mono<Optional<Account>> get(long number) {
        return Mono.just(accountDAO.findById(number).
                map(accountSql -> modelMapper.map(accountSql, Account.class)));
    }

    @Override
    public Mono<Optional<Account>> update(Account account) {
        return Mono.just(accountDAO.findById(account.getNumberAccount())
                .map(p -> {
                    p.setBalance(account.getBalance());
                    p.setState(account.isState());
                    p.setType(account.getType());
                    return accountDAO.save(modelMapper.map(p, AccountEntity.class));
                }).map(accountSql -> modelMapper.map(accountSql, Account.class)));
    }

    @Override
    public Mono<Boolean> delete(long number) {
        try {
            accountDAO.deleteById(number);
            return Mono.just(true);
        } catch (Exception e) {
            logger.error("Error", e);
            return Mono.just(false);
        }
    }

    @Override
    public Mono<Boolean> debitAccount(Account account, double amount) {

        return Mono.just(account).flatMap(accountOpt -> {
            if ((accountOpt.getBalance() - amount) >= 0) {
                accountOpt.setBalance(account.getBalance() - amount);
                this.update(account);
                return Mono.just(true);
            }
            return Mono.just(false);
        });
    }


}
