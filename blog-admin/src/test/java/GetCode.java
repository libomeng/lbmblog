import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class GetCode {
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


    public static void main(String[] args) {

        //创建代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        //全局配置
        GlobalConfig globalConfig =new GlobalConfig();
        String property = System.getProperty("user.dir")+"\\blog-admin";
        System.out.println(property);
        globalConfig.setOutputDir(property+"/src/main/java");
        globalConfig.setAuthor("lbm");
        globalConfig.setOpen(false);    //生成后是否打开资源管理器
        globalConfig.setFileOverride(false); //重新生成时是否覆盖文件
        /**
         * Mp生成service层代码，默认接口名称第一个字有I
         * 去掉I
         */
        globalConfig.setServiceName("%sService");//去掉首字母的I
        globalConfig.setIdType(IdType.ASSIGN_ID);//主键策略
        globalConfig.setDateType(DateType.ONLY_DATE);//定义生成实体类的日期类型
        globalConfig.setSwagger2(true);//开启Swagger2模式
        autoGenerator.setGlobalConfig(globalConfig);


        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3307/blog?serverTimezone=GMT%2B8");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("0507");
        dataSourceConfig.setDbType(DbType.MYSQL); //设置数据库类型

        autoGenerator.setDataSource(dataSourceConfig);

        //包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setController("controller");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setParent("com.lbm");
        packageConfig.setModuleName(scanner("模块名"));

        autoGenerator.setPackageInfo(packageConfig);


        //策略配置

        StrategyConfig strategyConfig = new StrategyConfig();

        strategyConfig.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体累设置为驼峰命名策略
        strategyConfig.setTablePrefix("bm_"); //生成实体类时 去掉表前缀
        strategyConfig.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作
        strategyConfig.setRestControllerStyle(true);//restful api风格控制器
        strategyConfig.setControllerMappingHyphenStyle(true);//url中驼峰转连字符

        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();//执行


    }

}
