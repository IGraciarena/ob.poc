package ob.poc.mscustomersmultithreading.service;

import lombok.extern.slf4j.Slf4j;
import ob.poc.mscustomersmultithreading.client.ApiWeightHeightClient;
import ob.poc.mscustomersmultithreading.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class CustomerWeightHeightService {

    private static final int NUMBER_THREADS = 4;
    private final ApiWeightHeightClient api;

    @Autowired
    public CustomerWeightHeightService(ApiWeightHeightClient api) {
        this.api = api;
    }

    public CustomerInfo getCustomerWeightHeight(CustomerDto customerInfo) {
        log.info("CustomerWeightHeightService.getCustomerWeightHeight");
        return api.getRandomData(customerInfo);
    }

    public CustomersResponse getCustomersWeightHeightSequential(CustomersRequest request) {
        log.info("CustomerWeightHeightService.getCustomersWeightHeightSequential");
        List<CustomerInfo> customerInfoList = request.getCustomers().stream()
                .map(api::getRandomData)
                .collect(toList());
        return CustomersResponse.builder()
                .customers(customerInfoList)
                .build();
    }

    public CustomersResponse getCustomersWeightHeightForkJoin(CustomersRequest request) {
        log.info("CustomerWeightHeightService.getWeatherInfoByCitiesForkJoin");
        ForkJoinPool forkJoinPool = new ForkJoinPool(NUMBER_THREADS);
        List<CustomerInfo> customerInfoList = null;
        try {
            customerInfoList = forkJoinPool
                    .submit(() -> request.getCustomers().parallelStream()
                            .map(api::getRandomData)
                            .collect(toList()))
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error", e);
        }
        return CustomersResponse.builder()
                .customers(customerInfoList)
                .build();
    }

    public CustomersResponse getCustomersWeightHeightExecutorService(CustomersRequest request) {
        log.info("CustomerWeightHeightService.getCustomersWeightHeightExecutorService");
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_THREADS);
        List<CustomerInfo> customerInfoList;
        try {
            List<CompletableFuture<CustomerInfo>> collect = request.getCustomers().stream()
                    .map(customer -> CompletableFuture
                            .supplyAsync(() -> api.getRandomData(customer), executorService))
                    .collect(toList());

            customerInfoList = collect.stream()
                    .map(CompletableFuture::join)
                    .collect(toList());
        } finally {
            executorService.shutdown();
        }
        return CustomersResponse.builder()
                .customers(customerInfoList)
                .build();
    }
}
