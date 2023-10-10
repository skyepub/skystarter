package com.skytree.skystarter.repository;

import com.skytree.skystarter.dto.SalesOrderComplexDTO;
import com.skytree.skystarter.dto.SalesOrderSearchOption;
import com.skytree.skystarter.entity.SalesOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// 개체가 파라미터로 전달되는 @Query에 대한 매우 자세한 분석이 필요하다.
public interface SalesOrderRepository extends JpaRepository<SalesOrder,Long>,SalesOrderRepositoryCustom {
    // JPQL의 theta join을 이용해서 연관성 없는 Entity를 join하고 결과를 DTO로 반환한다.
    @Query(value = """
            select new com.skytree.skystarter.dto.SalesOrderComplexDTO(
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
    public List<SalesOrderComplexDTO> findAllSalesOrderComplex03();

    // JPQL의 theta join을 이용해서 연관성 없는 Entity를 join하고 결과를 DTO로 반환한다.
    @Query(value = """
            select new com.skytree.skystarter.dto.SalesOrderComplexDTO(
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
                where member.memberId = :memberId
            """)
    public Page<SalesOrderComplexDTO> findAllSalesOrderComplex04(@Param("memberId") long memberId, Pageable pageable);
//    public Page<SalesOrderComplexDTO> findAllSalesOrderComplex04(@Param("option") SalesOrderSearchOption option, Pageable pageable);
}
