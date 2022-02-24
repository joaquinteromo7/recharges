package co.com.bancolombia.usecase.accountservice;

import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.model.account.gateways.AccountRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@AllArgsConstructor
public class AccountServiceUseCase {

    private AccountRepository accountRepository;

    public Mono<Optional<Account>> get(long number) {
        return accountRepository.get(number);
    }

    public Flux<Account> getAll() {
        return accountRepository.getAll();
    }

    public Mono<Account> create(Account account) {
        return accountRepository.create(account);
    }

    public Mono<Optional<Account>> update(Account account) {
        return accountRepository.update(account);
    }

    public Mono<Boolean> delete(long accountNumber) {
         return accountRepository.delete(accountNumber);
    }

}
