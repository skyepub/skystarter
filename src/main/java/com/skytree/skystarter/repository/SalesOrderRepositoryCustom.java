package com.skytree.skystarter.repository;

import com.skytree.skystarter.dto.SalesOrderComplex01DTO;
import com.skytree.skystarter.dto.SalesOrderSearchOption;
import com.skytree.skystarter.entity.SalesOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SalesOrderRepositoryCustom {
    public List<SalesOrder> findAllSalesOrder();
    public List<SalesOrderComplex01DTO> findAllSalesOrderComplex01();
    public List<SalesOrderComplex01DTO> findAllSalesOrderComplex02();
    public Page<SalesOrderComplex01DTO> findAllSalesOrderComplex00(SalesOrderSearchOption salesOrderSearchOption, Pageable pageable);
}
