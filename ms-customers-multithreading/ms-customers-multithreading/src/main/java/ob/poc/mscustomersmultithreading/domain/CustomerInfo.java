package ob.poc.mscustomersmultithreading.domain;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerInfo implements Serializable {
    private String name;
    private Integer age;
    private Double weight;
    private Double height;
}
