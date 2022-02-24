package co.com.bancolombia.config;

import co.com.bancolombia.model.account.gateways.AccountRepository;
import co.com.bancolombia.model.provider.gateways.ProviderRepository;
import co.com.bancolombia.model.service.gateways.ServiceRepository;
import co.com.bancolombia.usecase.accountservice.AccountServiceUseCase;
import co.com.bancolombia.usecase.provideservice.ProvideServiceUseCase;
import co.com.bancolombia.usecase.rechargeservice.RechargeServiceUseCase;
import co.com.bancolombia.usecase.serviceservice.ServiceServiceUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {
        @Bean
        public RechargeServiceUseCase rechargeServiceUseCase(AccountRepository accountRepository, ProviderRepository providerRepository, ServiceRepository serviceRepository){
                return  new RechargeServiceUseCase(accountRepository,providerRepository,serviceRepository);
        }

        @Bean
        public AccountServiceUseCase accountServiceUseCase(AccountRepository accountRepository){
                return  new AccountServiceUseCase(accountRepository);
        }

        @Bean
        public ProvideServiceUseCase provideServiceUseCase(ProviderRepository providerRepository){
                return  new ProvideServiceUseCase(providerRepository);
        }

        @Bean
        public ServiceServiceUseCase serviceServiceUseCase(ServiceRepository serviceRepository){
                return  new ServiceServiceUseCase(serviceRepository);
        }

        @Bean
        public ModelMapper modelMapper() {
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper;
        }
}
