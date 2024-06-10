package com.example.gcsj4supermarket;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class code {
    public static void main(String[] args) {
        String url="jdbc:mysql:///warehouse";
        String username="root";
        String password="123456";
        String moudleName="sys";
        String mapperLocation="D:\\warehouse\\gr\\gcsj4supermarket\\src\\main\\resources\\mapper\\"+moudleName;
        String tables="shoppingcart";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("li") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .outputDir("D:\\warehouse\\gr\\gcsj4supermarket\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder ->
                        builder.parent("com.example.gcsj4supermarket") // 设置父包名
                                .moduleName(moudleName) // 设置父包模块名
                                .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)) // 设置mapperXml生成路径
                )
                .strategyConfig(builder ->
                        builder.addInclude(tables) // 设置需要生成的表名
                               // .addTablePrefix("x_") // 设置过滤表前缀
                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
