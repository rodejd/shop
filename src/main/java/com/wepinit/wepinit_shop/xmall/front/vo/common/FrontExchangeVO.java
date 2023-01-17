package com.wepinit.wepinit_shop.xmall.front.vo.common;

import lombok.Data;

/**
 * 한국 수출입은행 환율 정보
 */
@Data
public class FrontExchangeVO {
    private int result; // 결과값, 1 : 성공, 2 : DATA코드 오류, 3 : 인증코드 오류, 4 : 일일제한횟수 마감
    private String cur_unit;    // 통화코드
    private String ttb; // 전신환(송금) 받으실때
    private String tts; // 	전신환(송금) 보내실때
    private String deal_bas_r;  // ** 매매 기준율
    private String bkpr;    // 장부가격
    private String yy_efee_r;   // 년환가료율
    private String ten_dd_efee_r;   // 일환가료율
    private String kftc_bkpr;   // 서울외국환중개 장부가격
    private String kftc_deal_bas_r; // 서울외국환중개 매매기준율
    private String cur_nm;  // 국가/통화명
}
