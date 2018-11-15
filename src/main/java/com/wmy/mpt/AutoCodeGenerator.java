package com.wmy.mpt;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class AutoCodeGenerator {

    /**
     * 是否强制带上注解
     */
    boolean enableTableFieldAnnotation = false;
    /**
     * 生成的注解带上IdType类型
     */
    IdType tableIdType = null;
    /**
     * 是否去掉生成实体的属性名前缀
     */
    String[] fieldPrefix = null;
    /**
     * 生成的Service 接口类名是否以I开头
     * 默认是以I开头
     * user表 -> IUserService, UserServiceImpl
     */
    boolean serviceClassNameStartWithI = true;

    public void generateCode() {
        String packageName = "com.wmy.mpt";

        //tableIdType不起作用，因为数据库字段被设置成自增（待检测）

        /*---------------------------------不带前缀的注解与idType测试------------------------------------------*/
        //生成实体时，生成字段注解只有id，表名
        enableTableFieldAnnotation = false;
        tableIdType = null;
        generateByTables(packageName + ".noannoidtype", "sys_dept");

        //生成实体时，生成全部字段注解
        enableTableFieldAnnotation = true;
        tableIdType = null;
        generateByTables(packageName + ".noidtype", "sys_dept");

        //tableIdType不起作用
        enableTableFieldAnnotation = false;
        tableIdType = IdType.INPUT;
        generateByTables(packageName + ".noanno", "sys_dept");


        enableTableFieldAnnotation = true;
        tableIdType = IdType.INPUT;
        generateByTables(packageName + ".both", "sys_dept");
        /*---------------------------------不带前缀的注解与idType测试------------------------------------------*/

        //验证字段带前缀
        fieldPrefix = new String[]{"test"};
        /*---------------------------------带前缀的注解与idType测试------------------------------------------*/
        enableTableFieldAnnotation = false;
        tableIdType = null;
        generateByTables(packageName + ".noannoidtypewithprefix", "sys_dept");

        enableTableFieldAnnotation = true;
        tableIdType = null;
        generateByTables(packageName + ".noidtypewithprefix", "sys_dept");


        enableTableFieldAnnotation = false;
        tableIdType = IdType.INPUT;
        generateByTables(packageName + ".noannowithprefix", "sys_dept");
        enableTableFieldAnnotation = true;
        tableIdType = IdType.INPUT;
        generateByTables(packageName + ".withannoidtypeprefix", "sys_dept");
        /*---------------------------------带前缀的注解与idType测试------------------------------------------*/


        serviceClassNameStartWithI = false;
        generateByTables(packageName, "sys_dept");
    }

    private  void generateByTables(String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://localhost:3306/mptest";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setUrl(dbUrl);
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("wumengya");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                // .setDbColumnUnderline(true) 改为如下 2 个配置
                .setNaming(NamingStrategy.underline_to_camel)
                // .setColumnNaming(NamingStrategy.underline_to_camel)
                .entityTableFieldAnnotationEnable(enableTableFieldAnnotation)
                .setFieldPrefix(fieldPrefix)//test_id -> id, test_type -> type
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setIdType(tableIdType)
                .setAuthor("w_mengya")
                .setOutputDir("c:\\workspaces\\MybatisPlusTest\\src\\main\\java")
                .setFileOverride(true);
        if (!serviceClassNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("model")
                                .setService("service")
                                .setServiceImpl("service.impl")
                                .setMapper("mapper")
                                .setXml("mapper.xml")
                ).execute();
    }

    public static void main(String[] args){
       new AutoCodeGenerator().generateCode();
    }
}
