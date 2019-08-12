package com.guli.ucenter.service;

import com.guli.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Helen
 * @since 2019-08-09
 */
public interface MemberService extends IService<Member> {

    Integer countRegisterByDay(String data);
}
