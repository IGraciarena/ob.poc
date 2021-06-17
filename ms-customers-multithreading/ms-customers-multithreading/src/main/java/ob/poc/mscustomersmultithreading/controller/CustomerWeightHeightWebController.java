package ob.poc.mscustomersmultithreading.controller;

import lombok.extern.slf4j.Slf4j;
import ob.poc.mscustomersmultithreading.domain.CustomerInfo;
import ob.poc.mscustomersmultithreading.domain.CustomerDto;
import ob.poc.mscustomersmultithreading.domain.CustomersRequest;
import ob.poc.mscustomersmultithreading.domain.CustomersResponse;
import ob.poc.mscustomersmultithreading.service.CustomerWeightHeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class CustomerWeightHeightWebController {

    private final CustomerWeightHeightService service;

    @Autowired
    public CustomerWeightHeightWebController(CustomerWeightHeightService service) {
        this.service = service;
    }

    @GetMapping
    public CustomerInfo getCustomerWeightHeight(@RequestBody CustomerDto customerDto) {
        log.info("CustomerWeightHeightWebController.getCustomerWeightHeight CustomerDto={}", customerDto);
        return service.getCustomerWeightHeight(customerDto);
    }


    @PostMapping("/sequential")
    public CustomersResponse getCustomersWeightHeightSequential(@RequestBody CustomersRequest request) {
//        log.info("CustomerWeightHeightWebController.getCustomersWeightHeightSequential CustomerRequest={}", request);
        return service.getCustomersWeightHeightSequential(request);
    }

    @PostMapping("/parallel/forkjoin")
    public CustomersResponse getCustomersWeightHeightForkJoin(@RequestBody CustomersRequest request) {
        log.info("CustomerWeightHeightWebController.getCustomersWeightHeightForkJoin " + "CustomersRequest={}", request);
        return service.getCustomersWeightHeightForkJoin(request);
    }

    @PostMapping("/parallel/executor")
    public CustomersResponse getCustomersWeightHeightExecutorService(@RequestBody CustomersRequest request) {
        log.info("CustomerWeightHeightWebController.getCustomersWeightHeightExecutorService " + "CustomersRequest={}", request);
        return service.getCustomersWeightHeightExecutorService(request);
    }

}
