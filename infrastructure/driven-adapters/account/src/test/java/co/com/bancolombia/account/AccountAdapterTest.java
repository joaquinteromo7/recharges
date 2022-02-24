package co.com.bancolombia.account;

import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.account.persistencia.dao.AccountDAO;
import co.com.bancolombia.account.persistencia.entity.AccountEntity;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class AccountAdapterTest {

    @InjectMocks
    AccountAdapter accountAdapter;

    @Mock
    AccountDAO accountDAO;
    @Mock
    AccountAdapter accountAdapterMock;



    /*@Test
    public void create() {

        when(accountDAO.save(AccountEntity.builder().build()))
                .thenReturn(
                        AccountEntity.builder()
                                .build()
                );

        Mono<Account> response = accountAdapter.create(Account.builder().numberAccount(1).state(true).balance(1).type(1).build());

        StepVerifier.create(response)
                .expectNext(Account.builder().build())
                .verifyComplete();

    }*/

    @Test
    public void getAll() {

        List<AccountEntity> listAccount = new ArrayList<>();
        listAccount.add(AccountEntity.builder().state(true).build());
        listAccount.add(AccountEntity.builder().state(false).build());

        when(accountDAO.findAll())
                .thenReturn(
                        listAccount
                );

        Flux<Account> response = accountAdapter.getAll();

        StepVerifier.create(response)
                .expectNext(Account.builder().state(true).build())
                .expectNext(Account.builder().state(false).build())
                .verifyComplete();

    }

    @Test
    public void get() {

        when(accountDAO.findById((long) 0))
                .thenReturn(
                        Optional.of(AccountEntity.builder()
                                .build())
                );

        Mono<Optional<Account>> response = accountAdapter.get(0);

        StepVerifier.create(response)
                .expectNext(Optional.of(Account.builder().build()))
                .verifyComplete();
    }

    @Test
    public void update() {

        when(accountDAO.findById((long) 0))
                .thenReturn(
                        Optional.of(AccountEntity.builder()
                                .build())
                );

        when(accountDAO.save(AccountEntity.builder().build()))
                .thenReturn(
                        AccountEntity.builder()
                                .build()
                );

        Mono<Optional<Account>> response = accountAdapter.update(Account.builder().build());

        StepVerifier.create(response)
                .expectNext(Optional.of(Account.builder().build()))
                .verifyComplete();

    }

    @Test
    public void debitAccount() {

        when(accountAdapterMock.update(Account.builder().state(true).build()))
                .thenReturn(
                        Mono.just(Optional.of(Account.builder().state(true)
                                .build()))
                );

        Mono<Boolean> response = accountAdapter.debitAccount(Account.builder().state(true).build(), 0);

        StepVerifier.create(response)
                .expectNext(true)
                .verifyComplete();

    }

    @Test
    public void debitAccountFalse() {

        when(accountAdapterMock.update(Account.builder().state(true).build()))
                .thenReturn(
                        Mono.just(Optional.of(Account.builder().state(true)
                                .build()))
                );

        Mono<Boolean> response = accountAdapter.debitAccount(Account.builder().state(true).build(), 1);

        StepVerifier.create(response)
                .expectNext(false)
                .verifyComplete();

    }

    @Test
    public void delete() {

        doNothing().when(accountDAO).deleteById((long) 0);

        Mono<Boolean> response = accountAdapter.delete(0);

        StepVerifier.create(response)
                .expectNext(true)
                .verifyComplete();

    }

    @Test
    public void deleteException() {

        doThrow(new RuntimeException("Add operation not implemented"))
                .when(accountDAO).deleteById((long) 0);

        Mono<Boolean> response = accountAdapter.delete(0);

        StepVerifier.create(response)
                .expectNext(false)
                .verifyComplete();

    }







}