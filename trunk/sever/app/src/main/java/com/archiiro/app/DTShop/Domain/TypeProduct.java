package com.archiiro.app.DTShop.Domain;

import com.archiiro.app.Core.Domain.BaseObjectMetadata;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="tbl_type_product")
@XmlRootElement
public class TypeProduct extends BaseObjectMetadata {

}
