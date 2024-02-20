package vn.sdtech.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.sdtech.api.dto.request.BillRequest;
import vn.sdtech.api.mapper.BillMapper;
import vn.sdtech.api.model.Bill;
import vn.sdtech.api.model.Product;
import vn.sdtech.api.repository.BillRepository;
import vn.sdtech.api.repository.ProductRepository;

import java.util.Optional;

@Service
public class BillServiceImpl {
    @Autowired
    private BillMapper billMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BillRepository billRepository;

    public Page<Bill> findAll(Pageable pageable){
        Page<Bill> bills = billRepository.findAll(pageable);
        return bills;
    }

    public BillRequest save(BillRequest billRequest) {
        Product product = productRepository.findById(billRequest.getProductId()).get();
        if (product.getQuantity() == 0) {
            throw new RuntimeException("Hết hàng");
        }
        if(billRequest.getQuantity() < 1) {
            throw new RuntimeException("Mua hơn 0 sản phẩm?");
        }
        if(billRequest.getQuantity() > product.getQuantity()) {
            throw new RuntimeException("Trong kho chỉ còn " + product.getQuantity() + " sản phẩm");
        }
        billRepository.save(billMapper.billRequestDTOToBill(billRequest));
        product.setQuantity(product.getQuantity() - billRequest.getQuantity());
        productRepository.save(product);
        return billRequest;
    }

    public void delete(Long billId) {
        Optional<Bill> existsBill = billRepository.findById(billId);
        if (existsBill.isPresent()){
            billRepository.deleteById(billId);
        } else {
            throw new RuntimeException("Hóa đơn không tồn tại");
        }
    }
}


