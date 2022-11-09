package com.example.mystock;

import com.example.mystock.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@SpringBootTest
class MyStockApplicationTests {

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void testRepoEnabled() {
        assertThat(productRepository).isNotNull();
    }


    @Test
    public void shouldReturnArtByName() {
        assertEquals(productRepository.getAllByNameContains("Товар2").get(0).getArt(), new BigDecimal(222222));
    }

}
