package co.com.bancolombia.api;

import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.model.provider.Provider;
import co.com.bancolombia.model.recarga.Recarga;
import co.com.bancolombia.model.service.Service;
import co.com.bancolombia.usecase.accountservice.AccountServiceUseCase;
import co.com.bancolombia.usecase.provideservice.ProvideServiceUseCase;
import co.com.bancolombia.usecase.rechargeservice.RechargeServiceUseCase;
import co.com.bancolombia.usecase.serviceservice.ServiceServiceUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping(value = "/recharge", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private final RechargeServiceUseCase rechargeServiceUseCase;
    private final AccountServiceUseCase accountServiceUseCase;
    private final ProvideServiceUseCase provideServiceUseCase;
    private final ServiceServiceUseCase serviceServiceUseCase;


    @PostMapping(path = "/recharge")
    public Mono<Boolean> recharge(@RequestBody Recarga recarga) {
        return  rechargeServiceUseCase.recharge(recarga);
    }

    @GetMapping(path = "account/{id}")
    public Mono<Optional<Account>> findByIdAccount(@PathVariable long id) {
        return  accountServiceUseCase.get(id);
    }

    @GetMapping(path = "account")
    public Flux<Account> findAllAccounts() {
        return  accountServiceUseCase.getAll();
    }

    @PostMapping(path = "account/create")
    public ResponseEntity<Object> createAccount(@RequestBody Account account) {
        return new ResponseEntity<Object>(accountServiceUseCase.create(account), HttpStatus.OK);
    }

    @PutMapping(path = "account/update")
    public Mono<Optional<Account>> updateAccount(@RequestBody Account account) {
        return accountServiceUseCase.update(account);
    }

    @DeleteMapping(path = "account/delete/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable long id) {
        return new ResponseEntity<Object>(accountServiceUseCase.delete(id), HttpStatus.OK);
    }

    @GetMapping(path = "provider/{id}")
    public Mono<Optional<Provider>> findByIdProvider(@PathVariable int id) {
        return  provideServiceUseCase.get(id);
    }

    @GetMapping(path = "provider")
    public Flux<Provider> findAllProviders() {
        return  provideServiceUseCase.getAll();
    }

    @PostMapping(path = "provider/create")
    public ResponseEntity<Object> createProvider(@RequestBody Provider provider) {
        return new ResponseEntity<Object>(provideServiceUseCase.create(provider), HttpStatus.OK);
    }

    @PutMapping(path = "provider/update")
    public Mono<Optional<Provider>> updateProvider(@RequestBody Provider provider) {
        return provideServiceUseCase.update(provider);
    }

    @DeleteMapping(path = "provider/delete/{id}")
    public ResponseEntity<Object> deleteProvider(@PathVariable int id) {
        return new ResponseEntity<Object>(provideServiceUseCase.delete(id), HttpStatus.OK);
    }

    @GetMapping(path = "service/{id}")
    public Mono<Optional<Service>> findByIdService(@PathVariable int id) {
        return  serviceServiceUseCase.get(id);
    }

    @GetMapping(path = "service")
    public Flux<Service> findAllServices() {
        return  serviceServiceUseCase.getAll();
    }

    @PostMapping(path = "service/create")
    public ResponseEntity<Object> createService(@RequestBody Service service) {
        return new ResponseEntity<Object>(serviceServiceUseCase.create(service), HttpStatus.OK);
    }

    @PutMapping(path = "service/update")
    public Mono<Optional<Service>> update(@RequestBody Service service) {
        return serviceServiceUseCase.update(service);
    }

    @DeleteMapping(path = "service/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        return new ResponseEntity<Object>(serviceServiceUseCase.delete(id), HttpStatus.OK);
    }
}
