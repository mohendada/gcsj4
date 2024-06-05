package com.example.gcsj4supermarket.sys.mapper;

import com.example.gcsj4supermarket.sys.entity.Leaveapplication;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author li
 * @since 2024-05-30
 */
@Mapper
public interface LeaveapplicationMapper extends BaseMapper<Leaveapplication> {
    @Insert("INSERT INTO leaveapplication ( applicant, btime, etime, reason ) VALUES (#{applicant}, #{btime}, #{etime}, #{reason} )")
    void insertnewLapp(Leaveapplication a);
}
