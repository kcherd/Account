package com.ifuture.accountservice.models;

import javax.persistence.*;

@Entity
@Table(name = "Amount")
public class Amount {
    @Id
    private Integer id;

    @Column(name = "value")
    private Long value;

    public Amount() {}
    public Amount(Integer id, Long value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
