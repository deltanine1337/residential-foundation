package com.test.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table
@Data
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long districtId;
    @Column(nullable = false, unique = true)
    private String districtName;
    @JsonIgnore
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<House> houses;
}
