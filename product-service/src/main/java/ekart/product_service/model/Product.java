package ekart.product_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Documented;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;

}
