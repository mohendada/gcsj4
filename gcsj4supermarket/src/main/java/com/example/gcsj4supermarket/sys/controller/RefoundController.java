package com.example.gcsj4supermarket.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gcsj4supermarket.common.vo.Result;
import com.example.gcsj4supermarket.sys.entity.Refound;
import com.example.gcsj4supermarket.sys.mapper.RefoundMapper;
import com.example.gcsj4supermarket.sys.service.IRefoundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@Slf4j
@RestController
@RequestMapping("/sys/refound")
public class RefoundController {

    @Autowired
    IRefoundService refoundService;
    @Autowired
    private RefoundMapper refoundMapper;

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

    /**
     * 管理员直接生成退款
     *
     * @param orderid
     * @return
     */
    @PostMapping("/generateRefound")
    public Result<?> generate(@RequestParam("orderId") Integer orderid) throws Exception {
        try {
            refoundService.generate(orderid);
            return Result.success();
        } catch (Exception e) {
            log.info("捕获异常：{}", e);
            return Result.fail();
        }
    }

    /**
     * 由用户发起退款申请
     *
     * @param orderid //     * @param status
     * @return
     */
    @PostMapping("/RequestRefound")
    public Result<?> request(@RequestParam("orderId") Integer orderid) throws Exception {
        try {
            refoundService.request(orderid);
            return Result.success();
        } catch (Exception e) {
            log.info("捕获异常：{}", e);
            return Result.fail();
        }
    }

    /**
     * 处理退款申请
     *
     * @param orderid
     * @return
     */
    @PostMapping("/confirm")
    public Result<?> confirm(@RequestParam("orderid") Integer orderid, @RequestParam("status") Integer status) throws Exception {
        try {
            refoundService.confirm(orderid, status);
            return Result.success();
        } catch (Exception e) {
            log.info("发生错误：{}", e);
            return Result.fail();
        }
    }

}
