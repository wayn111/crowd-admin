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
import com.wayn.filemanager.constant.ElFinderConstants;
import com.wayn.filemanager.core.Target;
import com.wayn.filemanager.core.Volume;
import com.wayn.filemanager.core.VolumeSecurity;
import com.wayn.filemanager.service.ElfinderStorage;
import com.wayn.filemanager.service.VolumeHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines how to execute the search command.
 *
 * @author Thiago Gutenberg Carvalho da Costa
 */
public class SearchCommand extends AbstractJsonCommand implements ElfinderCommand {

    @Override
    protected void execute(ElfinderStorage elfinderStorage, HttpServletRequest request, JSONObject json) throws Exception {

        final String query = request.getParameter(ElFinderConstants.ELFINDER_PARAMETER_SEARCH_QUERY);

        try {
            List<Object> objects = null;
            List<Volume> volumes = elfinderStorage.getVolumes();
            for (Volume volume : volumes) {

                // checks volume security
                Target volumeRoot = volume.getRoot();
                VolumeSecurity volumeSecurity = elfinderStorage.getVolumeSecurity(volumeRoot);

                // search only in volumes that are readable
                if (volumeSecurity.getSecurityConstraint().isReadable()) {

                    // search for targets
                    List<Target> targets = volume.search(query);

                    if (targets != null) {

                        // init object list
                        if (objects == null) {
                            objects = new ArrayList<>(targets.size());
                        }

                        // adds targets info in the return list
                        for (Target target : targets) {
                            objects.add(getTargetInfo(request, new VolumeHandler(target, elfinderStorage)));
                        }
                    }
                }
            }

            Object[] objectArray = objects != null ? objects.toArray() : new Object[0];
            json.put(ElFinderConstants.ELFINDER_PARAMETER_FILES, objectArray);

        } catch (Exception e) {
            json.put(ElFinderConstants.ELFINDER_JSON_RESPONSE_ERROR, "Unable to search! Error: " + e);
        }
    }
}
