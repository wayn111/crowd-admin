package com.wayn.framework.manager.thread;

import com.wayn.common.enums.DataSourceEnum;

/**
 * 数据库切换threadLocal
 */
public class DataSourceHolder {
    private static ThreadLocal<DataSourceEnum> dataSourceEnumThreadLocal = new ThreadLocal() {

        @Override
        protected DataSourceEnum initialValue() {
            return DataSourceEnum.MASTER;
        }
    };

    public static void set(DataSourceEnum dataSourceEnum) {
        dataSourceEnumThreadLocal.set(dataSourceEnum);
    }

    public static DataSourceEnum get() {
        return dataSourceEnumThreadLocal.get();
    }

    public static void remove() {
        dataSourceEnumThreadLocal.remove();
    }

}
