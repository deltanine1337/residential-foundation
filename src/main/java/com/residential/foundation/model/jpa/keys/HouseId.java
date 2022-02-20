package com.residential.foundation.model.jpa.keys;


import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseId implements Serializable {
    private String street;
    private Integer houseNumber;
}

