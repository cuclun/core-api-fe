package vn.sdtech.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import vn.sdtech.api.dto.request.BillRequest;
import vn.sdtech.api.dto.response.BillResponse;
import vn.sdtech.api.model.Bill;

@Mapper(componentModel = "spring")
public interface BillMapper {
    BillMapper INSTANCE = Mappers.getMapper(BillMapper.class);

    @Mapping(target = "productId", source = "bill.product.id")
    BillResponse billToBillResponse(Bill bill);

    @Mapping(target = "product.id", source = "billRequest.productId")
    Bill billRequestDTOToBill(BillRequest billRequest);

}