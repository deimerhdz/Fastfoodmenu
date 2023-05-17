package com.dhernandez.fastfood.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.dhernandez.fastfood.domain.dto.ProductDto;
import com.dhernandez.fastfood.persistence.entities.Product;

@Mapper(componentModel = "Spring",uses = {UserMapper.class,CategoryMapper.class})
public interface ProductMapper {

		List<ProductDto> toProductsDto(List<Product> products);
		
		ProductDto toProductDto(Product product);
		
		Product toProduct(ProductDto productDto);
}
