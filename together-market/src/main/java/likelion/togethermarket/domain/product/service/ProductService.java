package likelion.togethermarket.domain.product.service;

import likelion.togethermarket.domain.product.dto.BasicProductDto;
import likelion.togethermarket.domain.product.entity.Product;
import likelion.togethermarket.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ResponseEntity<?> getProductList() {
        List<Product> productList = productRepository.findAll();
        List<BasicProductDto> basicProductDtos = productList.stream().map(product -> new BasicProductDto(product)).toList();

        return new ResponseEntity<List<BasicProductDto>>(basicProductDtos, HttpStatusCode.valueOf(200));
    }
}
