package ob.poc.mscustomersmultithreading.domain;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomersResponse implements Serializable {
    private List<CustomerInfo> customers;
}
