package com.wayn.common.util;

import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.lang.reflect.Method;

/**
 * ip2region帮助类
 */
public class IP2RegionUtil {
    public static String getCityInfo(String ip) {
        //db
        //DbSearcher.BINARY_ALGORITHM //Binary
        //DbSearcher.MEMORY_ALGORITYM //Memory
        try {

            String dbPath = IP2RegionUtil.class.getClassLoader().getResource("ip2region.db").getPath();
            File file = new File(dbPath);

            if (!file.exists()) {
                System.out.println("Error: Invalid ip2region.db file");
            }

            //查询算法
            int algorithm = DbSearcher.BTREE_ALGORITHM; //B-tree

            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config, file.getAbsolutePath());

            //define the method
            Method method = null;
            switch (algorithm) {
                case DbSearcher.BTREE_ALGORITHM:
                    method = searcher.getClass().getMethod("btreeSearch", String.class);
                    break;
                case DbSearcher.BINARY_ALGORITHM:
                    method = searcher.getClass().getMethod("binarySearch", String.class);
                    break;
                case DbSearcher.MEMORY_ALGORITYM:
                    method = searcher.getClass().getMethod("memorySearch", String.class);
                    break;
            }

            DataBlock dataBlock = null;
            if (Util.isIpAddress(ip) == false) {
                System.out.println("Error: Invalid ip address");
            }

            dataBlock = (DataBlock) method.invoke(searcher, ip);

            return dataBlock.getRegion();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) throws Exception {
        System.err.println(getCityInfo("220.248.12.158"));
    }

}
