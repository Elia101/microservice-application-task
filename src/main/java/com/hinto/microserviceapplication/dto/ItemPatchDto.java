package com.hinto.microserviceapplication.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemPatchDto {

    @NotBlank
    String title;

    LocalDate date;

    @Min(value = 1, message = "Quantity must be greater than zero")
    int qty;

}
