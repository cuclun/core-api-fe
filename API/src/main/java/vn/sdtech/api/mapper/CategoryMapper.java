package vn.sdtech.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.sdtech.api.dto.request.CategoryRequest;
import vn.sdtech.api.dto.response.CategoryResponse;
import vn.sdtech.api.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryResponse categoryToCategoryResponse(Category category);

    Category categoryRequestDTOToCategory(CategoryRequest categoryRequest);
}