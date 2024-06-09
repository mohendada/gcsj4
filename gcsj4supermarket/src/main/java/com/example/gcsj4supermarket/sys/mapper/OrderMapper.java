package com.example.gcsj4supermarket.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gcsj4supermarket.sys.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
public interface OrderMapper extends BaseMapper<Order> {

    @Insert("INSERT INTO `order` (order_id, order_kind, order_time, order_status, order_name, order_number,order_creater_id,receiving_time) " +
            "VALUES (#{orderId}, #{orderKind}, #{orderTime}, #{orderStatus}, #{orderName},#{orderNumber},#{orderCreaterId},#{receivingTime})")
    int insert(Order order);

//    @Update("update `order` " +
//            "set order_kind=#{orderKind},order_time=#{orderTime},order_status=#{orderStatus},order_name=#{orderName},order_number=#{orderNumber},order_creater_id = #{orderCreaterId},receiving_time = #{receivingTime} where order_id=#{orderId}")
    void update(Order order);

    @Select("select max(order_id)from `order`")
    int maxId();

    @Delete("delete from `order` where order_id = #{id}")
    void deleter(Integer id);

    @Select("select * from `order` where order_id=#{id}")
    Order getById(Integer id);

    @Select("select * from  `order`")
    List<Order> getAllOrders();

    @Select("select * from  `order` where order_status = #{id}")
    List<Order> GetByStatus(Integer id);

    IPage<Order> selectOrderPage(Page<Order> page, Order order);
}
