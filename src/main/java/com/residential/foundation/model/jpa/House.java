package com.residential.foundation.model.jpa;

import com.residential.foundation.model.jpa.keys.HouseId;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

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
    @JoinColumn(name = "district_id")
    private District district;
    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Tenant> tenants;
}
