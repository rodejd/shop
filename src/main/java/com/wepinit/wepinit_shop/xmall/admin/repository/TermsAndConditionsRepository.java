package com.wepinit.wepinit_shop.xmall.admin.repository;

import com.wepinit.wepinit_shop.xmall.admin.vo.basic.TermsAndConditions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermsAndConditionsRepository extends JpaRepository<TermsAndConditions, Integer> {
    /**
     * 구분으로 검색
     * @param category
     */
    List<TermsAndConditions> findAllByCategory(String category);

    /**
     * 항목 검색, category, type, skin 모두 해당하는 항목은 하나 뿐임
     */
    TermsAndConditions findTopByCategoryAndTypeAndSkin(String category, String type, String skin);

    /**
     * no로 항목 검색
     */
    TermsAndConditions findByNo(Integer no);

}
