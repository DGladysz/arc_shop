package com.archiiro.app.Core.Domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "tbl_religion")
@XmlRootElement
public class Religion extends BaseObjectMetadata{

}
