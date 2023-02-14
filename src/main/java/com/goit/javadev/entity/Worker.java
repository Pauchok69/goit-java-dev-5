package com.goit.javadev.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Worker {
    private Long id;
    private String name;
    private LocalDate birthday;
    private Level level;
    private Integer salary;

    public enum Level {
        TRAINEE,
        JUNIOR,
        MIDDLE,
        SENIOR;

        @Override
        public String toString() {
            return this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase();
        }
    }
}
