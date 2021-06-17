package ob.poc.mscustomersmultithreading.domain;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomersRequest implements Serializable {
    private List<CustomerDto> customers;

}
