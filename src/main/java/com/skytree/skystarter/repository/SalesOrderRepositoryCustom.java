package com.skytree.skystarter.repository;

import com.skytree.skystarter.dto.SalesOrderComplexDTO;
import com.skytree.skystarter.dto.SalesOrderSearchOption;
import com.skytree.skystarter.entity.SalesOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SalesOrderRepositoryCustom {
    public List<SalesOrder> findAllSalesOrder();
    public List<SalesOrderComplexDTO> findAllSalesOrderComplex01();
    public List<SalesOrderComplexDTO> findAllSalesOrderComplex02();
    public Page<SalesOrderComplexDTO> findAllSalesOrderComplex00(SalesOrderSearchOption salesOrderSearchOption, Pageable pageable);
}
