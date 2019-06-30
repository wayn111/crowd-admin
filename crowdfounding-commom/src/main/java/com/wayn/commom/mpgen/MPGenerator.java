package com.wayn.commom.mpgen;

import org.junit.Test;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * <p>
 * 测试生成代码
 * </p>
 *
 * @author K神
 * @date 2017/12/18
 */
public class MPGenerator {

	@Test
	public void generateCode() {
		String packageName = "com.wayn";
		boolean serviceNameStartWithI = false;// user -> UserService, 设置成true: user -> IUserService
		generateByTables(serviceNameStartWithI, packageName, "sys_dict");
	}

	private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
		GlobalConfig config = new GlobalConfig();
		String dbUrl = "jdbc:mysql://192.168.233.128:3306/crowdfounding?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setDbType(DbType.MYSQL).setUrl(dbUrl).setUsername("root").setPassword("admin123")
				.setDriverName("com.mysql.cj.jdbc.Driver");
		StrategyConfig strategyConfig = new StrategyConfig();
		strategyConfig.setTablePrefix(new String[] { "sys_" }).setCapitalMode(true).setEntityLombokModel(false)
				.setNaming(NamingStrategy.underline_to_camel).setInclude(tableNames);// 修改替换成你需要的表名，多个表名传数组
		config.setActiveRecord(false).setAuthor("wayn").setOutputDir("./src/main/java").setFileOverride(true);
		if (!serviceNameStartWithI) {
			config.setServiceName("%sService");
			config.setMapperName("%sDao");
			config.setServiceImplName("%sServiceImpl");
		}
		new AutoGenerator().setTemplateEngine(new FreemarkerTemplateEngine()).setGlobalConfig(config)
				.setDataSource(dataSourceConfig).setStrategy(strategyConfig)
				.setPackageInfo(new PackageConfig().setParent(packageName).setEntity("domain")).execute();
	}

}
