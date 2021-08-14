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
import com.wayn.common.constant.Constants;
import com.wayn.common.util.ImageUtil;
import com.wayn.filemanager.constant.ElFinderConstants;
import com.wayn.filemanager.service.ElfinderStorage;
import com.wayn.filemanager.service.VolumeHandler;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

public class GetCommand extends AbstractJsonCommand implements ElfinderCommand {

    public static final String ENCODING = Constants.UTF_ENCODING;
    public static final String IMAGE_MIME_TYPE = "image";

    @Override
    protected void execute(ElfinderStorage elfinderStorage, HttpServletRequest request, JSONObject json) throws Exception {
        final String target = request.getParameter(ElFinderConstants.ELFINDER_PARAMETER_TARGET);
        final VolumeHandler vh = findTarget(elfinderStorage, target);
        final InputStream is = vh.openInputStream();
        if (vh.getMimeType().contains(IMAGE_MIME_TYPE)) {
            json.put(ElFinderConstants.ELFINDER_PARAMETER_CONTENT, ImageUtil.imgToBase64(is, vh.getMimeType()));
        } else {
            final String content = IOUtils.toString(is, ENCODING);
            is.close();
            json.put(ElFinderConstants.ELFINDER_PARAMETER_CONTENT, content);
        }
    }
}
