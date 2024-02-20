package vn.sdtech.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import vn.sdtech.api.dto.request.ProductRequest;
import vn.sdtech.api.dto.response.ProductResponse;
import vn.sdtech.api.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "categoryId", source = "product.category.id")
    ProductResponse productToProductResponse(Product product);

    @Mapping(target = "image", source = "productRequest.image.originalFilename")
    Product productRequestDTOToProduct(ProductRequest productRequest);

}