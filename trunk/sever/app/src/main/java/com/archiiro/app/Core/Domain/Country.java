package com.archiiro.app.Core.Domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "tbl_country")
@XmlRootElement
public class Country extends BaseObjectMetadata{

}
