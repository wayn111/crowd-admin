package com.wayn.common.domain.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class PageDTO<T> {

    private T data;

    private Page<T> page;

    public PageDTO(T data, Page<T> page) {
        this.data = data;
        this.page = page;
    }
}
