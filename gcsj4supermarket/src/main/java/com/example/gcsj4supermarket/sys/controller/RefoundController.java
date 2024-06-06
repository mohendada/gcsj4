package com.example.gcsj4supermarket.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gcsj4supermarket.common.vo.Result;
import com.example.gcsj4supermarket.sys.entity.Refound;
import com.example.gcsj4supermarket.sys.service.IRefoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@RestController
@RequestMapping("/sys/refound")
public class RefoundController {

    @Autowired
    IRefoundService refoundService;

    /**
     * 获取所有退款订单（分页）
     *
     * @param pageNum  当前页码
     * @param pageSize 每页显示数量
     * @return 分页后的退款订单列表
     */
    @GetMapping("/GetAllRefound")
    public Result<Object> GetAllRefound(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Refound> page = new Page<>(pageNum, pageSize);
        IPage<Refound> refounds = refoundService.getAllRefounds(page);
        return Result.success(refounds);
    }

}
