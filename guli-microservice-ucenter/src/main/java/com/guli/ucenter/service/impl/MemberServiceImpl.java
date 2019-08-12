package com.guli.ucenter.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guli.ucenter.entity.Member;
import com.guli.ucenter.mapper.MemberMapper;
import com.guli.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-08-09
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Integer countRegisterByDay(String day) {
        return memberMapper.selectRegisterCount(day);
    }
}
