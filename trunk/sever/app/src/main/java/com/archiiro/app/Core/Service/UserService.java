package com.archiiro.app.Core.Service;

import com.archiiro.app.Core.Domain.User;
import com.archiiro.app.Core.Dto.UserDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    UserDto saveUser(Long id, UserDto dto);

    Boolean deleteDto(Long id);

    UserDto findByUsername(String username);
}
