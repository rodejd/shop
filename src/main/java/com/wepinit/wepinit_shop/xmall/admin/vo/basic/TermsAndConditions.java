package com.wepinit.wepinit_shop.xmall.admin.vo.basic;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_gd_terms_and_conditions", schema = "wepinit", catalog = "wepinit")
public class TermsAndConditions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private Integer no;

    /**
     * 구분
     * <pre>
     * - clause : 약관
     * - policy : 정책
     * </pre>
     */
    @Column(name = "category")
    private String category;

    /**
     * 타입
     * <pre>
     * - clause
     *  1: 회원가입약관1
     *  2: 회원가입약관2
     *  3: 이용약관
     *  4:회사소개
     * - policy
     *  1: 품질보증정책
     *  2: 주문배송정책
     *  3: 교환반품정책
     *  4: 고객등급정책
     *  5: 결제/가격/적립금정책
     *  6: A/S정책
     *  7: 개인정보취급방침
     * </pre>
     */
    @Column(name = "type")
    private String type;

    /**
     * 스킨(언어)
     * <pre>
     * kr, en, ...
     * </pre>
     */
    @Column(name = "skin")
    private String skin;

    /**
     * 상세 내용
     */
    @Column(name = "content")
    private String content;


}
