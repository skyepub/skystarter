package com.skytree.skystarter.service;

import com.skytree.skystarter.dto.MemberDTO;
import com.skytree.skystarter.dto.ProductDTO;
import com.skytree.skystarter.dto.SalesOrderComplexDTO;
import com.skytree.skystarter.dto.SalesOrderSearchOption;
import com.skytree.skystarter.entity.Member;
import com.skytree.skystarter.entity.Product;
import com.skytree.skystarter.entity.SalesOrder;
import com.skytree.skystarter.exception.NotFoundException;
import com.skytree.skystarter.repository.MemberRepository;
import com.skytree.skystarter.repository.ProductRepository;
import com.skytree.skystarter.repository.SalesOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

// 향후 상당히 자세한 주석과 다큐먼트를 붙인다.
@Service
@RequiredArgsConstructor
public class MainService {
    @Autowired
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final SalesOrderRepository salesOrderRepository;

    public MemberDTO getMemberById(long memberId) throws NotFoundException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new NotFoundException("memberId로 검색 실패"));
        MemberDTO memberDTO = MemberDTO.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .name(member.getName())
                .build();
        return memberDTO;
    }

    public ProductDTO getProductById(long productId) throws NotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new NotFoundException("productId로 검색 실패"));
        ProductDTO productDTO = ProductDTO.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .price(product.getPrice())
                .desc(product.getDesc())
                .build();
        return productDTO;
    }

    public List<SalesOrder> getAllSalesOrder() {
        return salesOrderRepository.findAllSalesOrder();
    }

    public Page<SalesOrderComplexDTO> getAllSalesOrderComplex00(SalesOrderSearchOption salesOrderSearchOption, Pageable pageable) {
        return salesOrderRepository.findAllSalesOrderComplex00(salesOrderSearchOption,pageable);
    }

    // JPQL에 개체를 파라미터로 전달하는 경우에 :criteria.column에서 .column에서 구문 오류가 발생한다.
    // 개체의 .이 아닌 그냥 :param은 잘 인식된다.
    // 향후 시간이 있을때 깊이 살펴볼 것
    public Page<SalesOrderComplexDTO> getAllSalesOrderComplex04(SalesOrderSearchOption searchOption,Pageable pageable) {
        return salesOrderRepository.findAllSalesOrderComplex04(searchOption.getMemberId(),pageable);
//        return salesOrderRepository.findAllSalesOrderComplex04(searchOption,pageable);
    }

    public List<SalesOrderComplexDTO> getAllSalesOrderComplex01() {
        return salesOrderRepository.findAllSalesOrderComplex01();
    }

    public List<SalesOrderComplexDTO> getAllSalesOrderComplex02() {
        return salesOrderRepository.findAllSalesOrderComplex02();
    }

    public List<SalesOrderComplexDTO> getAllSalesOrderComplex03() {
        return salesOrderRepository.findAllSalesOrderComplex03();
    }

    public Member createMember(MemberDTO memberDTO) {
        Member member = Member.builder()
                .name(memberDTO.getName())
                .email(memberDTO.getEmail())
                .build();
        return memberRepository.save(member);
    }

    public Member updateMember(long memberId,MemberDTO memberDTO) throws NotFoundException{
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new NotFoundException("memberId로 검색 실패"));
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        return memberRepository.save(member);
    }
}
