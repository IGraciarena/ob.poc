package ob.poc.msproducts.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


}
