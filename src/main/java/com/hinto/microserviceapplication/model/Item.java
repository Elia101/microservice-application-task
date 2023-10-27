package com.hinto.microserviceapplication.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class Item {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long Id;

  @NotBlank
  private String title;

  private LocalDate date;

  @Min(value = 1, message = "Quantity must be greater than zero")
  private int qty;

}
