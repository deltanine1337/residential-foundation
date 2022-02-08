package com.test.task.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long tenantId;
    @Column(nullable = false)
    private String fio;
    @Column(nullable = false, length = 12)
    private String telNum;
    @Column(nullable = false)
    private byte apartmentNumber;
    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(
                    name = "street", insertable = false, updatable = false, nullable = false),
            @JoinColumn(
                    name = "houseNumber", insertable = false, updatable = false, nullable = false)
    })
    private House house;

}
