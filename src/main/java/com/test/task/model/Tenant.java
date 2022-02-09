package com.test.task.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Data
public class Tenant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long tenantId;
    @Column(nullable = false)
    private String fio;
    @Column(nullable = false, length = 12)
    private String telNum;
    @Column(nullable = false)
    private int apartmentNumber;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "houseNumber", insertable = false, updatable = false, nullable = false),
            @JoinColumn(
                    name = "street", insertable = false, updatable = false, nullable = false)
    })
    private House house;

    public void setHouse(House house){
        this.house = house;
    }

    public House getHouse(){
        return house;
    }

}
