package vn.sdtech.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.sdtech.api.dto.request.BillRequest;
import vn.sdtech.api.model.Bill;
import vn.sdtech.api.service.BillServiceImpl;

import java.util.Map;

@RestController
@RequestMapping("/api/bill")
public class BillController {

    @Autowired
    private BillServiceImpl billService;

    @GetMapping(path = {"/", ""})
    public ResponseEntity<?> bills(@RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "5") int size,
                                   @RequestParam(defaultValue = "id") String sortBy,
                                   @RequestParam(defaultValue = "ASC") String sortType) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.fromString(sortType), sortBy));
        Page<Bill> bills = billService.findAll(pageable);

        return ResponseEntity.ok(bills);
    }

    @PostMapping(path = {"/", ""})
    public ResponseEntity<?> addBill(@ModelAttribute BillRequest billRequest) {
        try {
            return ResponseEntity.ok(billService.save(billRequest));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("errorCode", 400,
                    "message", e.getMessage()));
        }
    }

    @DeleteMapping(path = {"/{billId}", "/{billId}/"})
    public ResponseEntity<?> deleteBill(@PathVariable Long billId) {
        try {
            billService.delete(billId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("errorCode", 400,
                    "message", e.getMessage()));
        }
    }

}
