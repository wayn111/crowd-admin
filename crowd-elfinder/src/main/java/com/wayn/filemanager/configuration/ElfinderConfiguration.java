package com.wayn.filemanager.configuration;

import com.wayn.filemanager.param.Node;
import com.wayn.filemanager.param.Thumbnail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:filemanager.properties")
public class ElfinderConfiguration {

    @Autowired
    private Thumbnail thumbnail;

    @Autowired
    private Node volume;

    private Long maxUploadSize = -1L;//默认不限制

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Node getVolume() {
        return volume;
    }

    public ElfinderConfiguration setVolume(Node volume) {
        this.volume = volume;
        return this;
    }

    public Long getMaxUploadSize() {
        return maxUploadSize;
    }

    public void setMaxUploadSize(Long maxUploadSize) {
        this.maxUploadSize = maxUploadSize;
    }
}
