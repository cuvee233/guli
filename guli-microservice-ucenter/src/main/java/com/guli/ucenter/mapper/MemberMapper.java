package com.guli.ucenter.mapper;

import com.guli.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-08-09
 */
public interface MemberMapper extends BaseMapper<Member> {

    Integer selectRegisterCount(String day);

}
