package com.test.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.task.model.keys.HouseId;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Data
public class House {
    @EmbeddedId
    private HouseId houseId;
    @Column(nullable = false)
    private int numberOfApartments;
    @Column(nullable = false)
    private int numberOfFloors;
    @Column(nullable = false)
    private int numberOfEntraces;
    @ManyToOne
    @JoinColumn(name = "districtId")
    private District district;
    @JsonIgnore
    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Tenant> tenants;
}
