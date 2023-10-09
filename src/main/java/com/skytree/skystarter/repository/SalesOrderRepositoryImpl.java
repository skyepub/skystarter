    package com.skytree.skystarter.repository;

    import com.querydsl.core.QueryResults;
    import com.querydsl.core.types.Projections;
    import com.querydsl.core.types.dsl.BooleanExpression;
    import com.querydsl.jpa.impl.JPAQueryFactory;
    import com.skytree.skystarter.dto.SalesOrderComplex01DTO;
    import com.skytree.skystarter.dto.SalesOrderSearchOption;
    import com.skytree.skystarter.entity.SalesOrder;
    import lombok.RequiredArgsConstructor;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageImpl;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.jpa.repository.Query;

    import java.util.List;

    import static com.skytree.skystarter.entity.QMember.member;
    import static com.skytree.skystarter.entity.QProduct.product;
    import static com.skytree.skystarter.entity.QSalesOrder.salesOrder;
    import static com.skytree.skystarter.entity.QSalesOrderProduct.salesOrderProduct;

    @RequiredArgsConstructor
    public class SalesOrderRepositoryImpl implements SalesOrderRepositoryCustom {
        private final JPAQueryFactory queryFactory;

        public List<SalesOrder> findAllSalesOrder() {
            List<SalesOrder> result = queryFactory
                    .select(salesOrder)
                    .from(salesOrder)
                    .fetch();
            return result;
        }


        //    select m.name,p.name,p.price,sp.quantity,so.create_date  from salesorder so
        //    inner join salesorder_product sp on (so.salesorder_id = sp.salesorder_id)
        //    inner join product p on (sp.product_id = p.product_id)
        //    inner join member m on (m.member_id = so.member_id)

    // Projections이 Contructor인 경우 완전히 생성자와 같아야 되어서 필드의 순서가 바뀌어도 안되고 빠져서도 안된다.
        public List<SalesOrderComplex01DTO> findAllSalesOrderComplex01() {
            List<SalesOrderComplex01DTO> result = queryFactory
                    .select(Projections.constructor(SalesOrderComplex01DTO.class
                            ,salesOrder.salesorderId
                            ,salesOrder.memberId
                            ,salesOrderProduct.productId
                            ,salesOrder.createDate
                            ,member.name
                            ,product.name
                            ,product.price
                            ,salesOrderProduct.quantity
                    ))
                    .from(salesOrder)
                    .innerJoin(salesOrderProduct).on(salesOrder.salesorderId.eq(salesOrderProduct.salesorderId))
                    .innerJoin(product).on(product.productId.eq(salesOrderProduct.productId))
                    .innerJoin(member).on(member.memberId.eq(salesOrder.memberId))
                    .fetch();
            return result;
        }

        // Projections이 fields인 경우 필드의 순서가 바뀌어도 되고 심지어 하나 빠져도 에러가 생기지 않는다.
        public List<SalesOrderComplex01DTO> findAllSalesOrderComplex02() {
            List<SalesOrderComplex01DTO> result = queryFactory
                    .select(Projections.fields(SalesOrderComplex01DTO.class
                            ,salesOrder.salesorderId
                            ,salesOrder.memberId
                            ,salesOrderProduct.productId
                            ,salesOrder.createDate
                            ,member.name
                            ,salesOrderProduct.quantity
                            ,product.name
                            ,product.price
                    ))
                    .from(salesOrder)
                    .innerJoin(salesOrderProduct).on(salesOrder.salesorderId.eq(salesOrderProduct.salesorderId))
                    .innerJoin(product).on(product.productId.eq(salesOrderProduct.productId))
                    .innerJoin(member).on(member.memberId.eq(salesOrder.memberId))
                    .fetch();
            return result;
        }


        public Page<SalesOrderComplex01DTO> findAllSalesOrderComplex00(SalesOrderSearchOption salesOrderSearchOption, Pageable pageable){
            QueryResults<SalesOrderComplex01DTO> result = queryFactory
                    .select(Projections.constructor(SalesOrderComplex01DTO.class
                            ,salesOrder.salesorderId
                            ,salesOrder.memberId
                            ,salesOrderProduct.productId
                            ,salesOrder.createDate
                            ,member.name
                            ,product.name
                            ,product.price
                            ,salesOrderProduct.quantity
                    ))
                    .from(salesOrder)
                    .innerJoin(salesOrderProduct).on(salesOrder.salesorderId.eq(salesOrderProduct.salesorderId))
                    .innerJoin(product).on(product.productId.eq(salesOrderProduct.productId))
                    .innerJoin(member).on(member.memberId.eq(salesOrder.memberId))
                    .where(
                            eqMemberId(salesOrderSearchOption.getMemberId())
                            ,eqProductId(salesOrderSearchOption.getProductId())
                            ,eqSalesOrderId(salesOrderSearchOption.getSalesorderId())
                            ,eqMemberName(salesOrderSearchOption.getMemberName())
                            ,eqProductName(salesOrderSearchOption.getProductName())
                    ).fetchResults();
            return new PageImpl<>(result.getResults(),pageable,result.getTotal());
        }

        private BooleanExpression eqMemberId(Long memberId){
            if (memberId==null) {
                return null;
            }
            return salesOrder.memberId.eq(memberId);
        }

        private BooleanExpression eqProductId(Long productId){
            if (productId==null) {
                return null;
            }
            return salesOrderProduct.productId.eq(productId);
        }

        private BooleanExpression eqSalesOrderId(Long salesorderId){
            if (salesorderId==null) {
                return null;
            }
            return salesOrder.salesorderId.eq(salesorderId);
        }

        private BooleanExpression eqMemberName(String memberName){
            if (memberName==null || memberName.isEmpty()) {
                return null;
            }
            return member.name.eq(memberName);
        }

        private BooleanExpression eqProductName(String productName){
            if (productName==null || productName.isEmpty()) {
                return null;
            }
            return product.name.eq(productName);
        }
    }
