package org.crowdfounding.framework;

import com.alibaba.fastjson.JSONArray;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    @org.junit.Test
    public void testApp() {
        String json = "[\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"-1\",\"text\":\"顶级节点\",\"state\":[\"java.util.HashMap\",{\"opened\":true}],\"checked\":true,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[[\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"1\",\"text\":\"产品部\",\"state\":null,\"checked\":false,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[[\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"4\",\"text\":\"cpb\",\"state\":null,\"checked\":false,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[]],\"parentId\":\"1\",\"hasParent\":true,\"hasChildren\":false}],[\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"4\",\"text\":\"cpb\",\"state\":null,\"checked\":false,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[]],\"parentId\":\"1\",\"hasParent\":true,\"hasChildren\":false}]]],\"parentId\":\"0\",\"hasParent\":false,\"hasChildren\":true}],[\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"2\",\"text\":\"销售部\",\"state\":null,\"checked\":false,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[[\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"8\",\"text\":\"销售部1\",\"state\":null,\"checked\":false,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[]],\"parentId\":\"2\",\"hasParent\":true,\"hasChildren\":false}],[\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"8\",\"text\":\"销售部1\",\"state\":null,\"checked\":false,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[]],\"parentId\":\"2\",\"hasParent\":true,\"hasChildren\":false}]]],\"parentId\":\"0\",\"hasParent\":false,\"hasChildren\":true}],[\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"3\",\"text\":\"test部门\",\"state\":null,\"checked\":false,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[[\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"6\",\"text\":\"测试部门\",\"state\":null,\"checked\":false,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[]],\"parentId\":\"3\",\"hasParent\":true,\"hasChildren\":false}],[\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"6\",\"text\":\"测试部门\",\"state\":null,\"checked\":false,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[]],\"parentId\":\"3\",\"hasParent\":true,\"hasChildren\":false}]]],\"parentId\":\"0\",\"hasParent\":false,\"hasChildren\":true}]]],\"parentId\":\"\",\"hasParent\":false,\"hasChildren\":true}][\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"-1\",\"text\":\"顶级节点\",\"state\":[\"java.util.HashMap\",{\"opened\":true}],\"checked\":true,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[[\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"1\",\"text\":\"产品部\",\"state\":null,\"checked\":false,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[[\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"4\",\"text\":\"cpb\",\"state\":null,\"checked\":false,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[]],\"parentId\":\"1\",\"hasParent\":true,\"hasChildren\":false}],[\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"4\",\"text\":\"cpb\",\"state\":null,\"checked\":false,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[]],\"parentId\":\"1\",\"hasParent\":true,\"hasChildren\":false}]]],\"parentId\":\"0\",\"hasParent\":false,\"hasChildren\":true}],[\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"2\",\"text\":\"销售部\",\"state\":null,\"checked\":false,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[[\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"8\",\"text\":\"销售部1\",\"state\":null,\"checked\":false,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[]],\"parentId\":\"2\",\"hasParent\":true,\"hasChildren\":false}],[\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"8\",\"text\":\"销售部1\",\"state\":null,\"checked\":false,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[]],\"parentId\":\"2\",\"hasParent\":true,\"hasChildren\":false}]]],\"parentId\":\"0\",\"hasParent\":false,\"hasChildren\":true}],[\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"3\",\"text\":\"test部门\",\"state\":null,\"checked\":false,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[[\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"6\",\"text\":\"测试部门\",\"state\":null,\"checked\":false,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[]],\"parentId\":\"3\",\"hasParent\":true,\"hasChildren\":false}],[\"com.wayn.generator.domain.vo.Tree\",{\"id\":\"6\",\"text\":\"测试部门\",\"state\":null,\"checked\":false,\"attributes\":null,\"children\":[\"java.util.ArrayList\",[]],\"parentId\":\"3\",\"hasParent\":true,\"hasChildren\":false}]]],\"parentId\":\"0\",\"hasParent\":false,\"hasChildren\":true}]]],\"parentId\":\"\",\"hasParent\":false,\"hasChildren\":true}]";
        System.out.println(JSONArray.parseObject(json));
    }
}
