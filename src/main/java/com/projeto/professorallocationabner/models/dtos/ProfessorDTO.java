package com.projeto.professorallocationabner.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProfessorDTO(@NotNull @NotEmpty String name, @NotNull @NotEmpty String cpf, @NotNull DepartmentDTO department) {
}