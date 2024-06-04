package com.example.gcsj4supermarket.sys.controller;

import com.example.gcsj4supermarket.common.util.AliOssUtil;
import com.example.gcsj4supermarket.common.vo.Result;
import com.example.gcsj4supermarket.sys.entity.Goods;
import com.example.gcsj4supermarket.sys.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


/**
 * <p>
 * 前端控制器
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
    /**
     * 查询所有商品信息
     *
     * @return List<Goods>
     */
    @RequestMapping("/GoodsList")
    public Result<Object> getGoods() {
        List<Goods> goods = goodsService.GetGoodsList();
        return Result.success(goods);
    }

    @RequestMapping("/ById")
    public Result<?> getGoods(@RequestParam("id") Integer id) {
        Goods good = goodsService.getGoodsById(id);
        log.info("controller getGoods {}", good);
        return Result.success(good);
    }

    @RequestMapping("/Update")
    public Result<?> updateGoods(@RequestBody Goods goods) {


        goodsService.update(goods);
        return Result.success();
    }

    @RequestMapping("/Insert")
    public Result<?> insertGoods(Goods goods, MultipartFile file) {

        log.info("上传文件:{}", file);
        try {

            String originalFilename = file.getOriginalFilename();

            String extension = null;

            if (originalFilename != null) {
                extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
            }

            String objectName = UUID.randomUUID().toString() + extension;

            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            goods.setGoodsPhoto(filePath);
            goodsService.insert(goods);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e);
        }

        return Result.success();
    }

    @RequestMapping("/Delete")
    public Result<?> deleteGoods(@RequestParam("id") Integer id) {
        goodsService.remove(id);
        return Result.success();
    }
}
