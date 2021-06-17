package ob.poc.mscustomersmultithreading.client;

import lombok.extern.slf4j.Slf4j;
import ob.poc.mscustomersmultithreading.domain.CustomerDto;
import ob.poc.mscustomersmultithreading.domain.CustomerInfo;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class ApiWeightHeightClient {

    private static final Double RANGE_MIN_WEIGHT = 60D;
    private static final Double RANGE_MIN_HEIGHT = 1.0D;
    private static final Double RANGE_MAX_WEIGHT = 200D;
    private static final Double RANGE_MAX_HEIGHT = 2.51D;
    private static final Integer RANGE_MIN_AGE = 15;
    private static final Integer RANGE_MAX_AGE = 122;
    private static final int LATENCY = 500;
    private final Random r = new Random();


    public CustomerInfo getRandomData(CustomerDto customerDto) {
        try {
            //Simulate latency
            Thread.sleep(LATENCY);
            return CustomerInfo.builder()
                    .name(customerDto.getName())
                    .age(customerDto.getAge())
                    .weight(getRandomWeight())
                    .height(getRandomHeight())
                    .build();
        } catch (InterruptedException e) {
            log.error("Error", e);
            return null;
        }
    }

    private Double getRandomWeight() {
        try {
            //Simulate latency
            Thread.sleep(LATENCY);
            return getRandomDouble(RANGE_MIN_WEIGHT, RANGE_MAX_WEIGHT);
        } catch (InterruptedException e) {
            log.error("Error", e);
            return null;
        }
    }

    private Double getRandomHeight() {
        try {
            //Simulate latency
            Thread.sleep(LATENCY);
            return getRandomDouble(RANGE_MIN_HEIGHT, RANGE_MAX_HEIGHT);
        } catch (InterruptedException e) {
            log.error("Error", e);
            return null;
        }
    }

    private Integer getRandomAge() {
        try {
            //Simulate latency
            Thread.sleep(LATENCY);
            return getRandomInt(RANGE_MIN_AGE, RANGE_MAX_AGE);
        } catch (InterruptedException e) {
            log.error("Error", e);
            return null;
        }
    }

    private Double getRandomDouble(Double min, Double max) {
        return min + (max - min) * r.nextDouble();
    }

    private Integer getRandomInt(Integer min, Integer max) {
        return min + (max - min) * r.nextInt();
    }
}
