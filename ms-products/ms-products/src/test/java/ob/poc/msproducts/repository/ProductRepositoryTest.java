package ob.poc.msproducts.repository;

import ob.poc.msproducts.utils.StubFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class ProductRepositoryTest implements StubFactory {

    @Autowired
    ProductRepository underTest;

    @Test
    @DisplayName("Should find ALL Products Matching the NAME of the Category")
    void findAllByCategoryName() {
        assertNotNull(underTest.findAllByCategoryName("shoes"));
    }

}