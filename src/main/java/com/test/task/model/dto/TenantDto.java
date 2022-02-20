package com.test.task.model.dto;

import lombok.Data;

@Data
public class TenantDto {
    private Long tenantId;
    private String fio;
    private String telNum;
    private int apartmentNumber;
    private HouseDto houseDto;
}
