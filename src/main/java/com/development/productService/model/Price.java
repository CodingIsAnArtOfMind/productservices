package com.development.productService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Price extends  BaseModel{
    String currency;
    double price;
    @OneToOne(mappedBy = "price", cascade = CascadeType.ALL)
    private Product product;
}
