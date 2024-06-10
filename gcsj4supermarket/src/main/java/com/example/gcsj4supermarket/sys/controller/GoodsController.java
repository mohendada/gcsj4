package com.example.gcsj4supermarket.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gcsj4supermarket.common.Res2.QueryPageParam;
import com.example.gcsj4supermarket.common.Res2.Result2;
import com.example.gcsj4supermarket.common.vo.Result;
import com.example.gcsj4supermarket.sys.entity.Goods;
import com.example.gcsj4supermarket.sys.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.gcsj4supermarket.common.util.AliOssUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@RestController
@RequestMapping("/sys/goods")
@Slf4j
public class GoodsController {
    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private AliOssUtil aliOssUtil;
//    /**
//     * 查询所有商品信息
//     *
//     * @return List<Goods>
//     */
//    @RequestMapping("/GoodsList")
//    public Result<Object> getGoods() {
//        List<Goods> goods = goodsService.GetGoodsList();
//        return Result.success(goods);
//    }

    /**
     * 分页查询所有商品信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页显示记录数
     * @return IPage<Goods>
     */
    @GetMapping("/GoodsList")
    public Result<IPage<Goods>> getGoods(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<Goods> page = new Page<>(pageNum, pageSize);
        IPage<Goods> goodsPage = goodsService.getGoodsPage(page);
        return Result.success(goodsPage);
    }

    /**
     * 根据Id查询商品
     *
     * @param id
     * @return good
     */
    @GetMapping("/ById")
    public Result<?> getGoods(@RequestParam("id") Integer id) {
        Goods good = goodsService.getGoodsById(id);
        log.info("controller getGoods {}", good);
        return Result.success(good);
    }

    /**
     * 更新商品信息
     *
     * @param goods
     * @param file
     * @return
     */
    @PutMapping("/Update")
    public Result<?> updateGoods(Goods goods, MultipartFile file) {
        try {
            goodsService.updateGoods(goods, file);
            return Result.success();
        } catch (IOException e) {
            log.error("更新商品信息失败：{}", e.getMessage());
            return Result.fail("更新失败");
        }
    }
    //新增
    @PostMapping("/save")
    public Result2 save(@RequestBody Goods goods){
        return goodsService.save(goods)? Result2.suc(): Result2.fail();
    }
    //更新
    @PostMapping("/update")
    public Result2 update(@RequestBody Goods goods){
        return goodsService.updateById(goods)? Result2.suc(): Result2.fail();
    }
    //删除
    @GetMapping("/del")
    public Result2 del(@RequestParam String id){
        return goodsService.removeById(id)? Result2.suc(): Result2.fail();
    }

    @PostMapping("/listPage")
    public Result2 listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("name");
        String goodstype = (String)param.get("goodstype");
        String storage = (String)param.get("storage");

        Page<Goods> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Goods::getGoodsName,name);
        }
        if(StringUtils.isNotBlank(goodstype) && !"null".equals(goodstype)){
            lambdaQueryWrapper.eq(Goods::getGoodsType,goodstype);
        }

        IPage result = goodsService.pageCC(page,lambdaQueryWrapper);
        return Result2.suc(result.getRecords(),result.getTotal());
    }


    /**
     * 新增商品
     *
     * @param goods
     * @param file
     * @return
     */
    @PostMapping("/Insert")
    public Result<?> insertGoods(Goods goods, MultipartFile file, HttpServletRequest httpRequest) {
        log.info("收到的请求体:{}", httpRequest.toString());
        log.info("接收到的Goods对象：{}", goods);
        if ((file != null)) {
            log.info("上传文件:{}", file);
            try {
                String originalFilename = file.getOriginalFilename();
                String extension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf('.')) : "";
                String objectName = UUID.randomUUID().toString() + extension;
                String filePath = aliOssUtil.upload(file.getBytes(), objectName);
                goods.setGoodsPhoto(filePath);
            } catch (IOException e) {
                log.error("文件上传失败：{}", e);
                return Result.fail("文件上传失败");
            }
        }
        goodsService.insert(goods);
        return Result.success();
    }

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    @DeleteMapping("/Delete")
    public Result<?> deleteGoods(@RequestParam("id") Integer id) {
        goodsService.remove(id);
        return Result.success();
    }

    @PostMapping("/goodStatus")
    public Result<?> GoodStatus(@RequestParam("id") Integer id) {
        goodsService.GoodStatus(id);
        return Result.success(9999, "修改成功");
    }
}
