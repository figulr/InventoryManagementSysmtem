package com.fenikskrylo.dechallintier.feniksystem.web;

import com.fenikskrylo.dechallintier.feniksystem.domain.Products;
import com.fenikskrylo.dechallintier.feniksystem.domain.ProductsRepository;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsSaveRequestDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductsAPIControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductsRepository productsRepository;

    @After
    public void tearDown() throws Exception{
        productsRepository.deleteAll();
    }

    @Test
    public void Product_등록된다() throws Exception{
        //given
        //given
        long barcodeId = 111;
        String productName = "제품이름";
        String price = "111";
        String brand = "브랜드이름";
        String weight = "111";
        String volumeShort = "111";
        String volumeLong = "111";
        String volumeHeight = "111";
        ProductsSaveRequestDto requestDto = ProductsSaveRequestDto.builder()
                .barcodeId(barcodeId)
                .productName(productName)
                .price(price)
                .brand(brand)
                .weight(weight)
                .volumeShort(volumeShort)
                .volumeLong(volumeLong)
                .volumeHeight(volumeHeight)
                .build();

        String url = "http://localhost:"+port+"/api/v1/register";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Products> all = productsRepository.findAll();
        assertThat(all.get(0).getBarcodeId()).isEqualTo(barcodeId);
        assertThat(all.get(0).getProductName()).isEqualTo(productName);
        assertThat(all.get(0).getPrice()).isEqualTo(price);
        assertThat(all.get(0).getBrand()).isEqualTo(brand);
        assertThat(all.get(0).getWeight()).isEqualTo(weight);
        assertThat(all.get(0).getVolumeShort()).isEqualTo(volumeShort);
        assertThat(all.get(0).getVolumeLong()).isEqualTo(volumeLong);
        assertThat(all.get(0).getVolumeHeight()).isEqualTo(volumeHeight);
    }

    @Test
    public void Product_수정된다() throws Exception{
        //given
        Products savedProduct = productsRepository.save(
                Products.builder()
                        .barcodeId(111)
                        .price("111")
                        .weight("111")
                        .brand("브랜드이름")
                        .productName("제품이름")
                        .volumeLong("111")
                        .volumeShort("111")
                        .volumeHeight("111")
                        .build()
        );

        Long updateId = savedProduct.getId();
        String expectedPrice = "222";
        String expectedWeight = "222";

        ProductsUpdateRequestDto requestDto = ProductsUpdateRequestDto.builder()
                .price(expectedPrice)
                .weight(expectedWeight)
                .build();

        String url = "http://localhost:"+port+"/api/v1/edit/"+updateId;

        HttpEntity<ProductsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
//        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        List<Products> all = productsRepository.findAll();
//        assertThat(all.get(0).getPrice()).isEqualTo(expectedPrice);
//        assertThat(all.get(0).getWeight()).isEqualTo(expectedWeight);
    }
}
