/*
 * #%L
 * %%
 * Copyright (C) 2015 Trustsystems Desenvolvimento de Sistemas, LTDA.
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the Trustsystems Desenvolvimento de Sistemas, LTDA. nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package com.wayn.filemanager.command;

import com.alibaba.fastjson.JSONObject;
import com.wayn.commom.constant.Constants;
import com.wayn.commom.util.HttpUtil;
import com.wayn.filemanager.constant.ElFinderConstants;
import com.wayn.filemanager.core.Target;
import com.wayn.filemanager.service.ElfinderStorage;
import com.wayn.filemanager.service.VolumeHandler;
import com.wayn.filemanager.support.archiver.Archiver;
import com.wayn.filemanager.support.archiver.ArchiverType;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

public class ZipdlCommand extends AbstractCommand implements ElfinderCommand {
    @Override
    public void execute(ElfinderStorage elfinderStorage, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject json = new JSONObject();
        final String[] targets = request.getParameterValues(ElFinderConstants.ELFINDER_PARAMETER_TARGETS);
        boolean download = request.getParameter(ElFinderConstants.ELFINDER_PARAMETER_DOWNLOAD) != null;
        if (download) {
//			String cwd = targets[0];
            String archiveFileTarget = targets[1];
            String downloadFileName = targets[2];
            String mime = targets[3];
            VolumeHandler archiveTarget = findTarget(elfinderStorage, archiveFileTarget);
            response.setCharacterEncoding(Constants.UTF_ENCODING);
            response.setContentType(mime);
            response.setHeader("Content-Disposition", HttpUtil.safeHttpHeader("attachments; "
                    + HttpUtil.getAttachementFileName(downloadFileName, request.getHeader("USER-AGENT"))));
            response.setHeader("Content-Transfer-Encoding", "binary");

            OutputStream out = response.getOutputStream();
            response.setContentLength((int) archiveTarget.getSize());

            try (InputStream is = archiveTarget.openInputStream()) {
                IOUtils.copy(is, out);
                out.flush();
                out.close();
            } finally {
                archiveTarget.delete();
            }

        } else {
            List<Target> targetList = findTargets(elfinderStorage, targets);
            Archiver archiver = ArchiverType.of("application/zip").getStrategy();
            Target targetArchive = archiver.compress(targetList.toArray(new Target[]{}));
            targetArchive.getVolume().getParent(targetArchive);
            json.put(ElFinderConstants.ELFINDER_PARAMETER_ZIPDL,
                    getTargetInfo(request, new VolumeHandler(targetArchive, elfinderStorage)));
            PrintWriter writer = response.getWriter();
            try {
                response.setContentType("application/json; charset=UTF-8");
                writer.write(json.toJSONString());
                writer.flush();
            } catch (Exception e) {
                logger.error("Unable to execute abstract json command", e);
                json.put(ElFinderConstants.ELFINDER_JSON_RESPONSE_ERROR, e.getMessage());
                writer.write(json.toJSONString());
                writer.flush();
            } finally {
                writer.close();
            }
        }

    }
}
