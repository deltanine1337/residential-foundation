package com.test.task.model.dto;

import lombok.Data;

@Data
public class TenantDTO {
    private Long tenantId;
    private String fio;
    private String telNum;
    private int apartmentNumber;
    private HouseDTO houseDto;
}
