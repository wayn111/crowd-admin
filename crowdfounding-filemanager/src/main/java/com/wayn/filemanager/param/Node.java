package com.wayn.filemanager.param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Node {
    @Value("${filemanager.volumes.Node.source}")
    private String source;
    @Value("${filemanager.volumes.Node.alias}")
    private String alias;
    @Value("${filemanager.volumes.Node.path}")
    private String path;
    @Value("${filemanager.volumes.Node._default}")
    private Boolean _default;
    @Value("${filemanager.volumes.Node.locale}")
    private String locale;
    @Autowired
    private Constraint constraint;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean get_default() {
        return _default;
    }

    public void set_default(Boolean _default) {
        this._default = _default;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Constraint getConstraint() {
        return constraint;
    }

    public void setConstraint(Constraint constraint) {
        this.constraint = constraint;
    }
}
