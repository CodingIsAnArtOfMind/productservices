package com.development.productService.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Setter
@Getter
@MappedSuperclass
public class BaseModel {
    @Id
    private UUID id;
}
