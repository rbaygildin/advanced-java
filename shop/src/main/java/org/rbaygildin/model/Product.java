package org.rbaygildin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.rbaygildin.model.BaseModel;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "products")
public class Product extends BaseModel {

    private String name;
    private Double price;
}
