package com.example.gcsj4supermarket.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gcsj4supermarket.common.util.AliOssUtil;
import com.example.gcsj4supermarket.common.vo.Result;
import com.example.gcsj4supermarket.sys.entity.Goods;
import com.example.gcsj4supermarket.sys.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzuiding
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
    @RequestMapping("/GoodsList")
    public Result<Object> getGoods(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<Goods> page = new Page<>(pageNum, pageSize);
        IPage<Goods> goodsPage = goodsService.getGoodsPage(page);
        return Result.success(goodsPage);
    }
    /**
     * 根据Id查询商品
     * @param id
     * @return good
     */
    @RequestMapping("/ById")
    public Result<?> getGoods(@RequestParam("id") Integer id) {
        Goods good = goodsService.getGoodsById(id);
        log.info("controller getGoods {}", good);
        return Result.success(good);
    }

    /**
     * 更新商品信息
     * @param goods
     * @return
     */
    @RequestMapping("/Update")
    public Result<?> updateGoods(@RequestBody Goods goods,MultipartFile file) {


        goodsService.updateGoods(goods,file);
        return Result.success();
    }

    /**
     * 新增商品
     * @param goods
     * @param file
     * @return
     */
    @RequestMapping("/Insert")
    public Result<?> insertGoods(@RequestBody Goods goods, MultipartFile file) {

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

    /**
     * 删除商品
     * @param id
     * @return
     */
    @RequestMapping("/Delete")
    public Result<?> deleteGoods(@RequestParam("id") Integer id) {
        goodsService.remove(id);
        return Result.success();
    }

    @RequestMapping("/goodStatus")
    public Result<?> GoodStatus(@RequestParam("id") Integer id){
        goodsService.GoodStatus(id);
        return  Result.success();
    }
}
