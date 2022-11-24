package com.archiiro.app.Core.Service;

import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Dto.UserDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto getCurrentUser();
    UserDto getByUsername(String username);
    UserDto saveUser(UserDto dto, Long id);
    Boolean deleteDto(Long id);
    Boolean isExist(String username);
    Page<UserDto> searchByPage(SearchDto searchDto);
}
