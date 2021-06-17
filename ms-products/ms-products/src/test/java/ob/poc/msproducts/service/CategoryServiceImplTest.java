package ob.poc.msproducts.service;

import ob.poc.msproducts.entity.Category;
import ob.poc.msproducts.repository.CategoryRepository;
import ob.poc.msproducts.utils.StubFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest implements StubFactory {

    CategoryService underTest;

    @Mock
    CategoryRepository repository;

    @BeforeEach
    public void setup(){
        underTest = new CategoryServiceImpl(repository);
    }

    @Test
    void registerCategory() {
        ArgumentCaptor<Category> argumentCaptor = ArgumentCaptor.forClass(Category.class);
        underTest.registerCategory(createCategoryDtoStub());
        verify(repository, times(1)).save(argumentCaptor.capture());
        var capturedCategory = argumentCaptor.getValue();
        assertEquals("categoryDtoStubName", capturedCategory.getName());
    }
}