package co.com.bancolombia.usecase.rechargeservice;

import co.com.bancolombia.model.account.gateways.AccountRepository;
import co.com.bancolombia.model.provider.gateways.ProviderRepository;
import co.com.bancolombia.model.recarga.Recarga;
import co.com.bancolombia.model.service.gateways.ServiceRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@AllArgsConstructor
public class RechargeServiceUseCase {

    private AccountRepository accountRepository;
    private ProviderRepository providerRepository;
    private ServiceRepository serviceRepository;


    public Mono<Boolean> recharge(Recarga recarga) {

        return accountRepository.get((recarga.getAccountNumber())).flatMap(account -> {
                    if (account.isPresent() && account != null && account.get().isState() && account.get().getBalance() >= recarga.getCreditAmount()) {
                        return providerRepository.get(recarga.getIdProvider()).flatMap(provider -> {
                            if (provider.isPresent() && provider != null && provider.get().isState()) {
                                return accountRepository.debitAccount(account.get(), recarga.getCreditAmount()).flatMap(isDebit -> {
                                    if (isDebit) {
                                        return serviceRepository.creditAmountToService(recarga).flatMap(isCredit -> {
                                            if (isCredit){
                                                return Mono.just(true);
                                            }
                                            account.get().setBalance(account.get().getBalance() + recarga.getCreditAmount());
                                            accountRepository.update(account.get());
                                            return Mono.just(isCredit);
                                        });
                                    }
                                    return Mono.just(false);
                                });
                            }
                            return Mono.just(false);
                        });
                    }
                    return Mono.just(false);
                }
        );
    }
}
