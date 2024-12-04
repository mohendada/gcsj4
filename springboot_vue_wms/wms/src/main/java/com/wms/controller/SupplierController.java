package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Menu;
import com.wms.entity.Supplier;

import com.wms.service.MenuService;
import com.wms.service.SupplierService;
import com.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wms
 * @since 2024-06-02
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private MenuService menuService;


    @GetMapping("/list")
    public List<Supplier> list() {
        return supplierService.list();
    }


    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String id) {
        List list = supplierService.lambdaQuery().eq(Supplier::getSupplierId, id).list();
        return list.size() > 0 ? Result.suc(list) : Result.fail();
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Supplier supplier) {
        return supplierService.save(supplier) ? Result.suc() : Result.fail();
    }

    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Supplier supplier) {
        return supplierService.updateById(supplier) ? Result.suc() : Result.fail();
    }

    //删除
    @GetMapping("/del")
    public Result del(@RequestParam int supplierId) {
        return supplierService.removeById(supplierId) ? Result.suc() : Result.fail();
    }


    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Supplier supplier) {
        return supplierService.updateById(supplier);
    }

    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Supplier supplier) {
        return supplierService.saveOrUpdate(supplier);
    }

    //删除
    @GetMapping("/delete")
    public boolean delete(Integer id) {
        return supplierService.removeById(id);
    }

    //查询（模糊、匹配）
    @PostMapping("/listP")
    public Result listP(@RequestBody Supplier supplier) {
        LambdaQueryWrapper<Supplier> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(supplier.getSupplierName())) {
            lambdaQueryWrapper.like(Supplier::getSupplierName, supplier.getSupplierName());
        }

        return Result.suc(supplierService.list(lambdaQueryWrapper));
    }

    @PostMapping("/listPage")
//    public List<User> listPage(@RequestBody HashMap map){
    public List<Supplier> listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        System.out.println("name===" + (String) param.get("name"));
        /*System.out.println("no==="+(String)param.get("no"));*/
        /*LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(User::getName,user.getName());

        return userService.list(lambdaQueryWrapper);*/

        Page<Supplier> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Supplier> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Supplier::getSupplierName, name);


        IPage result = supplierService.page(page, lambdaQueryWrapper);

        System.out.println("total==" + result.getTotal());

        return result.getRecords();
    }

    @PostMapping("/listPageC")
    public List<Supplier> listPageC(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        System.out.println("name===" + (String) param.get("name"));


        Page<Supplier> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Supplier> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Supplier::getSupplierName, name);


        //IPage result = userService.pageC(page);
        IPage result = supplierService.pageCC(page, lambdaQueryWrapper);

        System.out.println("total==" + result.getTotal());

        return result.getRecords();
    }

    @PostMapping("/listPageC1")
    public Result listPageC1(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("supplierName");
        String phone = (String) param.get("supplierPhoneNumber");
        String id = (String) param.get("supplierId");
        String addr = (String) param.get("supplierAddress");

        Page<Supplier> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Supplier> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(Supplier::getSupplierName, name);
        }

//        if (StringUtils.isNotBlank(id)) {
//            lambdaQueryWrapper.eq(Supplier::getSupplierId, id);
//        }

        //IPage result = userService.pageC(page);
        IPage result = supplierService.pageCC(page, lambdaQueryWrapper);

        System.out.println("total==" + result.getTotal());

        return Result.suc(result.getRecords(), result.getTotal());
    }
}
