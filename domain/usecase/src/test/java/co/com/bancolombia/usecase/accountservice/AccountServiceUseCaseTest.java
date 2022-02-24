package co.com.bancolombia.usecase.accountservice;

import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.model.account.gateways.AccountRepository;
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

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountServiceUseCaseTest {

    @InjectMocks
    AccountServiceUseCase accountServiceUseCase;

    @Mock
    AccountRepository accountRepository;


    @Test
    public void get() {

        when(accountRepository.get(0))
                .thenReturn(
                        Mono.just(Optional.of(Account.builder()
                                .build())
                        )
                );

        Mono<Optional<Account>> response = accountServiceUseCase.get(0);

        StepVerifier.create(response)
                .expectNext(Optional.of(Account.builder().build()))
                .verifyComplete();

    }

    @Test
    public void getAll() {

        List<Account> listAccount = new ArrayList<>();
        listAccount.add(Account.builder().state(true).build());
        listAccount.add(Account.builder().state(false).build());

        when(accountRepository.getAll())
                .thenReturn(
                        Flux.fromIterable(listAccount)
                );

        Flux<Account> response = accountServiceUseCase.getAll();

        StepVerifier.create(response)
                .expectNext(Account.builder().state(true).build())
                .expectNext(Account.builder().state(false).build())
                .verifyComplete();
    }

    @Test
    public void delete() {

        when(accountRepository.delete(0))
                .thenReturn(
                        Mono.just(false)
                );

        Mono<Boolean> response = accountServiceUseCase.delete(0);

        StepVerifier.create(response)
                .expectNext(false)
                .verifyComplete();

    }

    @Test
    public void create() {

        when(accountRepository.create(Account.builder().build()))
                .thenReturn(
                        Mono.just(Account.builder()
                                .build()
                        )
                );

        Mono<Account> response = accountServiceUseCase.create(Account.builder().build());

        StepVerifier.create(response)
                .expectNext(Account.builder().build())
                .verifyComplete();

    }

    @Test
    public void update() {

        when(accountRepository.update(Account.builder().build()))
                .thenReturn(
                        Mono.just(Optional.of(Account.builder()
                                .build())
                        )
                );

        Mono<Optional<Account>> response = accountServiceUseCase.update(Account.builder().build());

        StepVerifier.create(response)
                .expectNext(Optional.of(Account.builder().build()))
                .verifyComplete();

    }

}