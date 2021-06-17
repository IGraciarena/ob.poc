package ob.poc.msproducts.controller;

import ob.poc.msproducts.service.ProductServiceImpl;
import ob.poc.msproducts.utils.StubFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest implements StubFactory {
    @MockBean
    ProductController underTest;

    @MockBean
    ProductServiceImpl service;

    @Autowired
    WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    @Test
    void shouldRegisterProduct() throws Exception {
        underTest.registerProduct(createProductStub());
        mockMvc
                .perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createProductStub())))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetProductById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/products/{productId}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAllByCategoryName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/products/category/{categoryName}", "shoes")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .patch("/products/{productId}", 1)
                .content(asJsonString(createProductStub()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{productId}", 1))
                .andExpect(status().isOk());
    }
}