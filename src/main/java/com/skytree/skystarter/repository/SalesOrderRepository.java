package com.skytree.skystarter.repository;

import com.skytree.skystarter.dto.SalesOrderComplex01DTO;
import com.skytree.skystarter.entity.SalesOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesOrderRepository extends JpaRepository<SalesOrder,Long>,SalesOrderRepositoryCustom {
    // JPQL의 theta join을 이용해서 연관성 없는 Entity를 join하고 결과를 DTO로 반환한다.
    @Query(value = """
            select new com.skytree.skystarter.dto.SalesOrderComplex01DTO(
                 salesOrder.salesorderId 
                ,salesOrder.memberId
                ,salesOrderProduct.productId
                ,salesOrder.createDate
                ,member.name
                ,product.name
                ,product.price
                ,salesOrderProduct.quantity
                ) from SalesOrder salesOrder
                inner join SalesOrderProduct salesOrderProduct on (salesOrder.salesorderId = salesOrderProduct.salesorderId)
                inner join Product product on (product.productId = salesOrderProduct.productId)
                inner join Member member on (member.memberId = salesOrder.memberId)
            """)
    public List<SalesOrderComplex01DTO> findAllSalesOrderComplex03();

    // JPQL의 theta join을 이용해서 연관성 없는 Entity를 join하고 결과를 DTO로 반환한다.
    @Query(value = """
            select new com.skytree.skystarter.dto.SalesOrderComplex01DTO(
                 salesOrder.salesorderId 
                ,salesOrder.memberId
                ,salesOrderProduct.productId
                ,salesOrder.createDate
                ,member.name
                ,product.name
                ,product.price
                ,salesOrderProduct.quantity
                ) from SalesOrder salesOrder
                inner join SalesOrderProduct salesOrderProduct on (salesOrder.salesorderId = salesOrderProduct.salesorderId)
                inner join Product product on (product.productId = salesOrderProduct.productId)
                inner join Member member on (member.memberId = salesOrder.memberId)
            """)
    public Page<SalesOrderComplex01DTO> findAllSalesOrderComplex04(Pageable pageable);
}
