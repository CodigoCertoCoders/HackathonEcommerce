package com.guardians_of_the_code.dtos;

import com.guardians_of_the_code.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductResponseDTO {
    private UUID uuid;
    private String name;
    private String url;
    private String category;
    private Double price;
    private String description;

    public void setCategory(Category category){
        this.category = formmaterCategory(category);
    }

    private String formmaterCategory(Category category){
        String formmated = category.name().toLowerCase();
        return formmated.substring(0,1).toUpperCase() + formmated.substring(1);
    }
}
