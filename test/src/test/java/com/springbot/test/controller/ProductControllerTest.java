package com.springbot.test.controller;

import com.google.gson.Gson;
import com.springbot.test.data.dto.ProductDto;
import com.springbot.test.data.dto.ProductResponseDto;
import com.springbot.test.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
/*
 *  <테스트 대상 클래스>
 *  웹에서 사용되는 요청과 응답에 대한 테스트 수행 가능. 대상 클래스만 로드해 테스트 수행하며, 만약 대상 클래스를 추가하지 않으면
 * @Controller, @RestController, @ControllerAdvice 등의 컨트롤러 관련 빈 객체가 모두 로드됨.
 *
 * @SpringBootTest보다 가볍게 테스트하기 위함
 * */
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean   // 실제 빈 객체가 아닌 Mock(가짜)를 생성해서 주입하는 역할
    ProductServiceImpl productService;

    @Test   // 테스트 코드가 포함돼 있다고 선언하는 어노테이션.
    @DisplayName("MockMvc를 통한 Product 데이터 가져오기 테스트")
        // 테스트 메서드의 이름이 가독성이 떨어질 경우, 테스트에 대한 표현을 정의 가능
    void getProductTest() throws Exception {
        // 이 객체에서 어떤 메서드가 호출되고 어떤 파라미터를 주입하는지 가정
        given(productService.getProduct(123L)).willReturn(
                new ProductResponseDto(123L, "pen", 5000, 2000));   // 어떤 결과를 리턴할 것인지 정의

        String productId = "123";

        // 서버로 URL 요청을 보내느 것처럼 통신 테스트 코드 작성해 컨트롤러 테스트 가능
        mockMvc.perform(
                        get("/product?number=" + productId))
                .andExpect(status().isOk()) //결과값 검증 수행
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());    //요청과 응답 전체 내용 확인

        verify(productService).getProduct(123L);    // 지정된 메서드가 실행됐는지 검증

    }

    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void createProductTest() throws Exception{
        //Mock 객체에서 특정 메서드가 실행되는 경우 실제 return을 줄 수 없기 때문에 가정 사항 만듦
        given(productService.saveProduct(new ProductDto("pen", 5000, 2000)))
                .willReturn(new ProductResponseDto(12315L, "pen", 5000, 2000));

        ProductDto productDto = ProductDto.builder()
                .name("pen")
                .price(5000)
                .stock(2000)
                .build();

        Gson gson = new Gson();
        String content = gson.toJson(productDto);

        mockMvc.perform(
                        post("/product")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());
        verify(productService).saveProduct((new ProductDto("pen",5000,2000)));

    }
}
