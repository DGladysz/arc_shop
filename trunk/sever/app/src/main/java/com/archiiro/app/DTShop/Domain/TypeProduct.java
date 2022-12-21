package com.archiiro.app.DTShop.Domain;

import com.archiiro.app.Core.Domain.BaseObjectMetadata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="tbl_type_product")
@XmlRootElement
public class TypeProduct extends BaseObjectMetadata {
    @Column(name = "status")
    private Integer status; // 0: Hết sản phẩm, 1: Đang giao bán

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
