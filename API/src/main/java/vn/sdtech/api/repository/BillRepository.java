package vn.sdtech.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.sdtech.api.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {
    boolean existsByProductId(Long id);
}
