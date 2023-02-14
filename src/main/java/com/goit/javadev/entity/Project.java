package com.goit.javadev.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private Long id;
    private Long clientId;
    private String name;
    private LocalDate startDate;
    private LocalDate finishDate;
}
