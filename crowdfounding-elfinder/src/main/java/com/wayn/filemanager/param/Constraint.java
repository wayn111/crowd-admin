package com.wayn.filemanager.param;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constraint {
    @Value("${filemanager.volumes.Node.constraint.locked}")
    private boolean locked;
    @Value("${filemanager.volumes.Node.constraint.readable}")
    private boolean readable;
    @Value("${filemanager.volumes.Node.constraint.writable}")
    private boolean writable;

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    public boolean isWritable() {
        return writable;
    }

    public void setWritable(boolean writable) {
        this.writable = writable;
    }
}
