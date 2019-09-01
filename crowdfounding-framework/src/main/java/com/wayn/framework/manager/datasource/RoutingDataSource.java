package com.wayn.framework.manager.datasource;

import com.wayn.framework.manager.thread.DataSourceHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 数据源切换类，<br>
 * 根据determineCurrentLookupKey方法返回key值，切换到对应的数据源
 */
public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.get();
    }
}
