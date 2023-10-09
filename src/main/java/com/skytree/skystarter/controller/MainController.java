package com.skytree.skystarter.controller;
import com.skytree.skystarter.dto.MemberDTO;
import com.skytree.skystarter.dto.ProductDTO;
import com.skytree.skystarter.dto.SalesOrderComplex01DTO;
import com.skytree.skystarter.dto.SalesOrderSearchOption;
import com.skytree.skystarter.entity.Member;
import com.skytree.skystarter.entity.SalesOrder;
import com.skytree.skystarter.exception.NotFoundException;
import com.skytree.skystarter.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// 2023년 10월7일~10일 (주로 8일,9일 양일간 모든 케이스를 커버하는 예제 완성)

// RestController가 아니고 그냥 Controllerd일 경우
// DTO를 리턴하면 404 Not Found 에러가 발생된다.
@RestController
@RequiredArgsConstructor
@RequestMapping("/starter")
public class MainController {
    @Autowired
    private final MainService mainService;

    @GetMapping
    public String hello() {
        String msg = "Hello,World.";
        System.out.println(msg);
        return msg;
    }

    @GetMapping("/member/{memberId}")
    public MemberDTO getMemberById(@PathVariable("memberId") long memberId) throws NotFoundException {
        MemberDTO memberDTO = mainService.getMemberById(memberId);
        return memberDTO;
    }

    @GetMapping("/product/{productId}")
    public ProductDTO getProductById(@PathVariable("productId") long productId) throws NotFoundException {
        ProductDTO productDTO = mainService.getProductById(productId);
        return productDTO;
    }

    // Entity를 바로 반환
    @GetMapping("/salesorder")
    public List<SalesOrder> getAllSalesOrder() {
        return mainService.getAllSalesOrder();
    }

    // 복합Join의 결과를 담기위한 DTO를 만들고 이를 반환 - 2023.10.9 15:00에 성공
    @GetMapping("/salesorder/complex1")
    public List<SalesOrderComplex01DTO> getAllSalesOrderComplex01() {
        return mainService.getAllSalesOrderComplex01();
    }

    // 복합Join의 결과를 담기위한 DTO를 만들고 이를 반환 - 2023.10.9 15:00에 성공
    @GetMapping("/salesorder/complex2")
    public List<SalesOrderComplex01DTO> getAllSalesOrderComplex02() {
        return mainService.getAllSalesOrderComplex02();
    }

    // 복합Join의 결과를 담기위한 DTO를 만들고 이를 반환 - 2023.10.9 15:00에 성공
    @GetMapping("/salesorder/complex3")
    public List<SalesOrderComplex01DTO> getAllSalesOrderComplex03() {
        return mainService.getAllSalesOrderComplex03();
    }

    // 복합Join의 결과를 담기위한 DTO를 만들고 이를 반환 - 2023.10.9 15:00에 성공
    @GetMapping("/salesorder/complex0")
    public Page<SalesOrderComplex01DTO> getAllSalesOrderComplex00(@ModelAttribute SalesOrderSearchOption salesOrderSearchOption, Pageable pageable) {
        Page<SalesOrderComplex01DTO> result = mainService.getAllSalesOrderComplex00(salesOrderSearchOption,pageable);
        return result;
    }

    // 복합Join의 결과를 담기위한 DTO를 만들고 이를 반환 - 2023.10.9 15:00에 성공
    @GetMapping("/salesorder/complex4")
    public Page<SalesOrderComplex01DTO> getAllSalesOrderComplex04(Pageable pageable) {
        Page<SalesOrderComplex01DTO> result = mainService.getAllSalesOrderComplex04(pageable);
        return result;
    }

    @PostMapping("/member/create")
    public MemberDTO createMember(@RequestBody MemberDTO memberDTO) {
        Member member = mainService.createMember(memberDTO);
        return member.toDTO();
    }

    @PutMapping("/member/update/{memberId}")
    public MemberDTO createMember(@PathVariable("memberId") long memberId,@RequestBody MemberDTO memberDTO) throws NotFoundException {
        Member member = mainService.updateMember(memberId,memberDTO);
        return member.toDTO();
    }
}
