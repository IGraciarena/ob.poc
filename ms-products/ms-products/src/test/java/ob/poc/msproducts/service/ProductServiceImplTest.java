package ob.poc.msproducts.service;

import ob.poc.msproducts.entity.Product;
import ob.poc.msproducts.entity.dto.request.ProductDto;
import ob.poc.msproducts.repository.ProductRepository;
import ob.poc.msproducts.utils.StubFactory;
import ob.poc.msproducts.utils.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest implements StubFactory {

    ProductService underTest;

    @Mock
    ProductRepository repository;

    Product productStub;

    ProductMapper mapper;

    @BeforeEach
    public void setup() {
        underTest = new ProductServiceImpl(repository);
        productStub = createProductStub();
        mapper = ProductMapper.INSTANCE;
    }

    @Test
    void registerProduct() {
        ArgumentCaptor<Product> argumentCaptor = ArgumentCaptor.forClass(Product.class);
        underTest.registerProduct(createProductStub());
        verify(repository, times(1)).save(argumentCaptor.capture());
        var capturedProduct = argumentCaptor.getValue();
        assertEquals("productStubName", capturedProduct.getName());
    }

    @Test
    void getProductById() {
        when(repository.findById(1L)).thenReturn(Optional.of(createProductStub()));
        underTest.getProductById(1L);
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void getAllProductsByCategoryName() {
        when(repository.findAllByCategoryName(anyString())).thenReturn(createProductsStub());
        underTest.getAllProductsByCategoryName("categoryStubName");
        verify(repository, times(1)).findAllByCategoryName("categoryStubName");
    }

    @Test
    void updateProduct() {
        ProductDto dto = ProductDto.builder().name("productDtoStubName").price(100D).stock(100).build();
        when(repository.findById(1L)).thenReturn(Optional.of(productStub));
        when(repository.save(any(Product.class))).thenReturn(productStub);
        underTest.updateProduct(1L, dto);
        assertEquals(productStub, underTest.updateProduct(1L, dto));
    }

    @Test
    void deleteProduct() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(productStub));
        underTest.deleteProduct(1L);
        verify(repository, times(1)).findById(anyLong());
    }


}