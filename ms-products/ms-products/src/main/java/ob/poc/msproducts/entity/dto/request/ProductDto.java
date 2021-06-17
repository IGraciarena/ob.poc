package ob.poc.msproducts.entity.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private String name;
    private Double price;
    private Integer stock;
}
