package co.com.bancolombia.service.strategy;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceStrategyFactory {

    private Map<String, ServiceInterface> strategies;


    public ServiceStrategyFactory(){
        strategies = new HashMap<>();
        initStrategies();
    }

    private void initStrategies(){
        strategies.put("1", new ClaroAdapter());
        strategies.put("2", new MovistarAdapter());
    }

    public ServiceInterface getStrategy(String type){
        return strategies.get(type);
    }


}
