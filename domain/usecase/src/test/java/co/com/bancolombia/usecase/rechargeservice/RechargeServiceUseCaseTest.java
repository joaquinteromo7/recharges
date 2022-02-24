package co.com.bancolombia.usecase.rechargeservice;


import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.model.account.gateways.AccountRepository;
import co.com.bancolombia.model.provider.Provider;
import co.com.bancolombia.model.provider.gateways.ProviderRepository;
import co.com.bancolombia.model.recarga.Recarga;
import co.com.bancolombia.model.service.Service;
import co.com.bancolombia.model.service.gateways.ServiceRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Optional;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.Silent.class)
public class RechargeServiceUseCaseTest {


    @InjectMocks
    RechargeServiceUseCase rechargeServiceUseCase;

    @Mock
    AccountRepository accountRepository;
    @Mock
    ProviderRepository providerRepository;
    @Mock
    ServiceRepository serviceRepository;

    @Test
    public void init() {

        Assert.assertTrue(true);
    }

    @Test
    public void creditAmount() {

        when(accountRepository.get(0))
                .thenReturn(
                        Mono.just(Optional.of(Account.builder()
                                .state(true)
                                .build())
                        )
                );

        when(providerRepository.get(0))
                .thenReturn(
                        Mono.just(Optional.of(Provider.builder()
                                .state(true)
                                .build())
                        )
                );

        when(accountRepository.debitAccount(Account.builder()
                .state(true)
                .build(), 0.0))
                .thenReturn(
                        Mono.just(true)
                );

        when(serviceRepository.creditAmountToService(Recarga.builder().build()))
                .thenReturn(
                        Mono.just(true)
                );


        Mono<Boolean> response = rechargeServiceUseCase.recharge(
                Recarga.builder().build()
        );

        StepVerifier.create(response)
                .expectNext(true)
                .expectComplete()
                .verify();
    }

    @Test
    public void creditNoAmount() {

        when(accountRepository.get(0))
                .thenReturn(
                        Mono.just(Optional.of(Account.builder()
                                .state(true)
                                .build())
                        )
                );

        when(providerRepository.get(0))
                .thenReturn(
                        Mono.just(Optional.of(Provider.builder()
                                .state(true)
                                .build())
                        )
                );

        when(accountRepository.debitAccount(Account.builder()
                .state(true)
                .build(), 0.0))
                .thenReturn(
                        Mono.just(true)
                );

        when(serviceRepository.creditAmountToService(Recarga.builder().build()))
                .thenReturn(
                        Mono.just(false)
                );


        Mono<Boolean> response = rechargeServiceUseCase.recharge(
                Recarga.builder().build()
        );

        StepVerifier.create(response)
                .expectNext(false)
                .expectComplete()
                .verify();
    }

    @Test
    public void rechargeNoDebit() {

        when(accountRepository.get(0))
                .thenReturn(
                        Mono.just(Optional.of(Account.builder()
                                .state(true)
                                .build())
                        )
                );

        when(providerRepository.get(0))
                .thenReturn(
                        Mono.just(Optional.of(Provider.builder()
                                .state(true)
                                .build())
                        )
                );

        when(accountRepository.debitAccount(Account.builder()
                .state(true)
                .build(), 0.0))
                .thenReturn(
                        Mono.just(false)
                );

        Mono<Boolean> response = rechargeServiceUseCase.recharge(
                Recarga.builder().build()
        );

        StepVerifier.create(response)
                .expectNext(false)
                .expectComplete()
                .verify();
    }

    @Test
    public void rechargeNoProvider() {

        when(accountRepository.get(0))
                .thenReturn(
                        Mono.just(Optional.of(Account.builder()
                                .state(true)
                                .build())
                        )
                );

        when(providerRepository.get(0))
                .thenReturn(
                        Mono.just(Optional.of(Provider.builder()
                                .state(false)
                                .build())
                        )
                );

        Mono<Boolean> response = rechargeServiceUseCase.recharge(
                Recarga.builder().build()
        );

        StepVerifier.create(response)
                .expectNext(false)
                .expectComplete()
                .verify();
    }

    @Test
    public void rechargeNoAccount() {

        when(accountRepository.get(0))
                .thenReturn(
                        Mono.just(Optional.of(Account.builder()
                                .state(false)
                                .build())
                        )
                );

        Mono<Boolean> response = rechargeServiceUseCase.recharge(
                Recarga.builder().build()
        );

        StepVerifier.create(response)
                .expectNext(false)
                .expectComplete()
                .verify();
    }

}