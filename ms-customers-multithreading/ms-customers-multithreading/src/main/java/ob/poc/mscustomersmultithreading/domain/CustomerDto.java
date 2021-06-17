package ob.poc.mscustomersmultithreading.domain;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDto implements Serializable {
    private String name;
    private Integer age;
}
