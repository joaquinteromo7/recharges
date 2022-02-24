package co.com.bancolombia.model.account.gateways;

import co.com.bancolombia.model.account.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface AccountRepository {


    Mono<Account> create(Account account);

    Flux<Account> getAll();

    Mono<Optional<Account>> get(long number);

    Mono<Optional<Account>> update(Account account);

    Mono<Boolean> delete(long number);

    Mono<Boolean> debitAccount(Account account, double amount);


}
