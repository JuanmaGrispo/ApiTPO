package com.apiTPO.technologyHouse.app.dtos;

import com.apiTPO.technologyHouse.app.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDTO {
    private long id;
    private String email;
    private Role role;
}