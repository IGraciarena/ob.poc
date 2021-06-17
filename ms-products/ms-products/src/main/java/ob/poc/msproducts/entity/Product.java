package ob.poc.msproducts.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import ob.poc.msproducts.entity.enums.ProductStatus;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "products")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name must not be null.")
    private String name;

    private String description;
    @Positive(message = "Stock must be grater than cero.")
    private Double stock;

    private Double price;

    @Enumerated
    private ProductStatus status;

    @NotNull(message = "Category can not be null.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;
}
