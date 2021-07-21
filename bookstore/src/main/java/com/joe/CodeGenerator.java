package com.joe;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class CodeGenerator {

    public static void main(String[] args) {
        // 需要構建一個 代碼自動生成器 物件
        AutoGenerator mpg = new AutoGenerator();

        //1、設定資料源(數據庫)
        DataSourceConfig dsc = new DataSourceConfig();
        //數據庫地址
        dsc.setUrl("jdbc:mysql://localhost:3306/bookstore?characterEncoding=UTF-8");
        //數據庫驅動
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        //用戶名
        dsc.setUsername("root");
        //密碼
        dsc.setPassword("123456");
        //數據庫類型
        dsc.setDbType(DbType.MYSQL);
        //把數據庫配置放入生成器中
        mpg.setDataSource(dsc);

        // 配置策略
        // 2、全域配置
        GlobalConfig gc = new GlobalConfig();
        //獲取用戶項目的目錄(獲取程序運行的當前絕對路徑)
        String projectPath = System.getProperty("user.dir");
        //生成到哪個目錄。
        //使用module模塊生成: projectPath + "/模塊名" + "/src/main/java"
        //使用項目生成: projectPath + "/src/main/java"
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("joe");   //自動生成後，代碼上註釋的作者
        gc.setOpen(false);  //生成後是否開啟文件夾。建議設成false
        gc.setFileOverride(false);      //是否覆寫原來生成的
        gc.setServiceName("%sService");     //去除Service的I前綴。

        //把全局配置放入生成器中
        mpg.setGlobalConfig(gc);

        //3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.joe");    //主目錄名稱

        //把包的配置放入生成器中
        mpg.setPackageInfo(pc);

        //4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        //
        strategy.setInclude("book");
        //數據庫 "表" 映射到實體的命名策略: 把下滑線轉成駝峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //數據庫表 "字段" 映射到實體的命名策略: 把列名的下滑線轉成駝峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //自動生成lombok 【可選】
        strategy.setEntityLombokModel(true);

        // 自動填充配置 【可選】
        //(1)使用構造方法創建對象 (注意:駝峰命名)。 TableFill("字段名" , 操作的枚舉類)
        TableFill gmtCreate = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        //(2)創建 ArrayList 將自動填充配置放入
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(updateTime);
        //(3)將 "自動填充配置" 放入 "策略配置"。 參數: List類型
        strategy.setTableFillList(tableFills);

        //策略配置 放入 生成器
        mpg.setStrategy(strategy);

        mpg.execute(); //執行
    }
}

