package com.test.task.dto;

import lombok.Data;

@Data
public class TenantDto {
    private Long tenantId;
    private String fio;
    private String telNum;
    private int apartmentNumber;
    private HouseDto houseDto;
}
