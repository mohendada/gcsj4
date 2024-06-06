package com.example.gcsj4supermarket.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gcsj4supermarket.common.util.AliOssUtil;
import com.example.gcsj4supermarket.sys.entity.Goods;
import com.example.gcsj4supermarket.sys.mapper.GoodsMapper;
import com.example.gcsj4supermarket.sys.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private AliOssUtil aliOssUtil;

    @Override
    public Goods getGoodsById(Integer id) {
        return goodsMapper.getGoodsById(id);
    }

    @Override
    public void updateGoods(Goods goods) {
        goodsMapper.updateGoods(goods);
    }

    @Override
    public void insert(Goods goods) {
        int id = goodsMapper.selectId();
        goods.setGoodsId(id + 1);
        goodsMapper.insert(goods);
    }

    @Override
    public void remove(Integer id) {
        goodsMapper.delete(id);
    }

    @Override
    public void GoodStatus(Integer id) {
        Goods goods = goodsMapper.getGoodsById(id);
        log.info("修改前{}", goods);
        goods.setGoodsStatus(goods.getGoodsStatus() == 1 ? 0 : 1);
        log.info("修改后{}", goods);
        updateGoods(goods);
    }

    @Override
    public IPage<Goods> getGoodsPage(Page<Goods> page) {
        return goodsMapper.selectPage(page, null);
    }

    @Override
    public void updateGoods(Goods goods, MultipartFile file) throws IOException {
        if (file != null) {
            log.info("上传文件:{}", file);
            try {
                String originalFilename = file.getOriginalFilename();
                String extension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf('.')) : "";
                String objectName = UUID.randomUUID().toString() + extension;
                String filePath = aliOssUtil.upload(file.getBytes(), objectName);
                goods.setGoodsPhoto(filePath);
            } catch (IOException e) {
                log.error("文件上传失败：{}", e);
                throw e;
            }
        }
        updateGoods(goods);
    }
}
