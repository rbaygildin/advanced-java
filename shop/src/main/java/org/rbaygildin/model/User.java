package org.rbaygildin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User extends BaseModel {

    private String name;
    private String surname;
    private String email;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
}
