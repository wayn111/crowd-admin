package com.wayn.commom.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wayn.commom.constant.Constants;
import com.wayn.commom.dao.LogininforDao;
import com.wayn.commom.domain.Logininfor;
import com.wayn.commom.domain.vo.EchartVO;
import com.wayn.commom.excel.IExcelExportStylerImpl;
import com.wayn.commom.service.LogininforService;
import com.wayn.commom.shiro.util.ShiroUtil;
import com.wayn.commom.util.IP2RegionUtil;
import com.wayn.commom.util.LogUtils;
import com.wayn.commom.util.ParameterUtil;
import com.wayn.commom.util.ServletUtil;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@Service
public class logininforServiceImpl extends ServiceImpl<LogininforDao, Logininfor> implements LogininforService {

    private static final Logger logger = LoggerFactory.getLogger(logininforServiceImpl.class);

    @Autowired
    private LogininforDao logininforDao;

    @Override
    public Page<Logininfor> listPage(Page<Logininfor> page, Logininfor log) {
        QueryWrapper<Logininfor> wrapper = ParameterUtil.get();
        wrapper.like("loginName", log.getLoginName());
        wrapper.like("ipaddr", log.getIpaddr());
        wrapper.like("loginLocation", log.getLoginLocation());
        wrapper.eq(StringUtils.isNoneEmpty(log.getStatus()), "status", log.getStatus());
        return logininforDao.selectPage(page, wrapper);
    }

    @Override
    public boolean addLog(String username, String status, String message) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtil.getRequest().getHeader("User-Agent"));
        final String ip = ShiroUtil.getIP();
        String address = IP2RegionUtil.getCityInfo(ShiroUtil.getIP());
        StringBuilder s = new StringBuilder();
        s.append(LogUtils.getBlock(ip));
        s.append(address);
        s.append(LogUtils.getBlock(username));
        s.append(LogUtils.getBlock(status));
        s.append(LogUtils.getBlock(message));
        // 打印信息到日志
        logger.info(s.toString());
        // 获取客户端操作系统
        String os = userAgent.getOperatingSystem().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();
        // 封装对象
        Logininfor logininfor = new Logininfor();
        logininfor.setLoginName(username);
        logininfor.setIpaddr(ip);
        logininfor.setLoginLocation(address);
        logininfor.setBrowser(browser);
        logininfor.setOs(os);
        logininfor.setMsg(message);
        logininfor.setLoginTime(new Date());
        logininfor.setCreateTime(new Date());
        // 日志状态
        if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
            logininfor.setStatus(Constants.SUCCESS);
        } else if (Constants.LOGIN_FAIL.equals(status)) {
            logininfor.setStatus(Constants.FAIL);
        }
        // 插入数据
        return save(logininfor);
    }

    @Override
    public void export(Logininfor log, HttpServletResponse response, HttpServletRequest request) throws IOException {
        QueryWrapper<Logininfor> wrapper = ParameterUtil.get();
        wrapper.like("loginName", log.getLoginName());
        wrapper.like("ipaddr", log.getIpaddr());
        wrapper.eq(StringUtils.isNoneEmpty(log.getStatus()), "status", log.getStatus());
        List<Logininfor> list = list(wrapper);
        ExportParams exportParams = new ExportParams();
        exportParams.setStyle(IExcelExportStylerImpl.class);
        exportParams.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Logininfor.class, list);
        // 使用bos获取excl文件大小
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        ServletUtil.setExportResponse(request, response, "日志列表.xls", bos.size());
        //保存数据
        OutputStream os = response.getOutputStream();
        workbook.write(os);
        workbook.close();
        os.close();
        bos.close();
    }

    @Override
    public List<EchartVO> selectLoginLocationCount() {
        return logininforDao.selectLoginLocationCount();
    }
}
