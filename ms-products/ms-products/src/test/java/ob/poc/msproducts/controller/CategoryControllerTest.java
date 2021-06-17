package ob.poc.msproducts.controller;

import ob.poc.msproducts.entity.Category;
import ob.poc.msproducts.repository.CategoryRepository;
import ob.poc.msproducts.service.CategoryServiceImpl;
import ob.poc.msproducts.utils.StubFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest implements StubFactory {

    @MockBean
    CategoryController underTest;

    @MockBean
    CategoryServiceImpl service;

    @Mock
    CategoryRepository repository;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }


    @Test
    @DisplayName("Should registry a NEW Category")
    void shouldRegisterCategory() throws Exception {
        Category categoryStub = createCategoryStub();
        repository.save(categoryStub);
        underTest.registerCategory(createCategoryDtoStub());
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(categoryStub)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}