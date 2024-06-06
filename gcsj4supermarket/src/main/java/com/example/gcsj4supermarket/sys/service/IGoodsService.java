package com.example.gcsj4supermarket.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gcsj4supermarket.sys.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */

public interface IGoodsService extends IService<Goods> {
//    List<Goods> GetGoodsList();

    Goods getGoodsById(Integer id);

    void updateGoods(Goods goods);

    void insert(Goods goods);

    void remove(Integer id);

    void GoodStatus(Integer id);

    IPage<Goods> getGoodsPage(Page<Goods> page);

    void updateGoods(Goods goods, MultipartFile file) throws IOException;
}
