package com.archiiro.app.Core.Service.ServiceImpl;

import com.archiiro.app.Core.Domain.Person;
import com.archiiro.app.Core.Domain.Role;
import com.archiiro.app.Core.Domain.User;
import com.archiiro.app.Core.Dto.RoleDto;
import com.archiiro.app.Core.Dto.Function.SearchDto;
import com.archiiro.app.Core.Dto.UserDto;
import com.archiiro.app.Core.Other.Constants;
import com.archiiro.app.Core.Other.ProcessUtils;
import com.archiiro.app.Core.Repository.PersonRepository;
import com.archiiro.app.Core.Repository.RoleRepository;
import com.archiiro.app.Core.Repository.UserRepository;
import com.archiiro.app.Core.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service @Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = this.getByUsername(username);
        if(userDto == null) {
            System.out.println(username + " not found");
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(userDto.getRoles() != null && userDto.getRoles().size() > 0) {
            for(RoleDto item : userDto.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(item.getName()));
            }
        }
        return new org.springframework.security.core.userdetails.User(userDto.getUsername(), userDto.getPassword(), authorities);
    }
    @Override
    public List<UserDto> getAll() {
        return this.userRepository.getAll();
    }

    @Override
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            String currentUsername = authentication.getName();
            UserDto userDto = this.getByUsername(currentUsername);
            return userDto;
        }
        return null;
    }
    @Override
    public UserDto getByUsername(String username) {
        if(username != null) {
            return this.userRepository.getUserDto(username);
        }
        return null;
    }

    @Override
    public UserDto saveUser(UserDto dto, Long id) {
        if(dto == null) {
            return null;
        }
        User user = null;
        LocalDateTime currentDate = LocalDateTime.now();
        boolean isNew = false;
        if(id != null) {
            Optional<User> userOptional = this.userRepository.findById(id);
            if(userOptional.isPresent()) {
                user = userOptional.get();
                user.setModifyDate(currentDate);
                user.setModifiedBy(Constants.MODIFIED_BY);
            }
        }
        if(user == null && dto.getId() != null) {
            Optional<User> userOptional = this.userRepository.findById(id);
            if(userOptional.isPresent()) {
                user = userOptional.get();
                user.setModifyDate(currentDate);
                user.setModifiedBy(Constants.MODIFIED_BY);
            }
        }
        if(user == null) {
            user = new User();
            isNew = true;
            user.setCreateDate(currentDate);
            user.setCreateBy(Constants.CREATE_BY);
            user.setModifyDate(currentDate);
            user.setModifiedBy(Constants.MODIFIED_BY);
            if(dto.getUsername() != null && StringUtils.hasText(dto.getUsername())) {
                if(!this.isExist(dto.getUsername())) {
                    return null;
                }
                user.setUsername(dto.getUsername());
            }
        }
        if(dto.getPassword() != null && StringUtils.hasText(dto.getPassword())) {
            user.setPassword(ProcessUtils.getHashPassword(dto.getPassword()));
        }
        if(dto.getRoles() != null && dto.getRoles().size() > 0) {
            Iterator<RoleDto> iterator = dto.getRoles().iterator();
            HashSet<Role> roles = new HashSet<Role>();
            while ((iterator.hasNext())) {
                RoleDto roleDto = iterator.next();
                Role role = null;
                if(roleDto.getId() != null) {
                    Optional<Role> roleOptional = this.roleRepository.findById(roleDto.getId());
                    if(roleOptional.isPresent()) {
                        role = roleOptional.get();
                    }
                }
                if(role != null) {
                    roles.add(role);
                }
            }
            if(user.getRoles() != null && user.getRoles().size() > 0) {
                user.getRoles().clear(); // TH có dữ liệu
                user.getRoles().addAll(roles);
            } else {
                user.setRoles(roles);
            }
        }
        if(dto.getPersonId() != null) {
            Optional<Person> personOptional = this.personRepository.findById(dto.getPersonId());
            if(personOptional.isPresent()) {
                user.setPerson(personOptional.get());
            }
        }
        user = this.userRepository.save(user);
        return new UserDto(user);
    }

    @Override
    public Boolean deleteDto(Long id) {
        return null;
    }

    @Override
    public Boolean isExist(String username) {
        if(username != null) {
            Long number = this.userRepository.isExist(username);
            if(number == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Page<UserDto> searchByPage(SearchDto searchDto) {
        return null;
    }
}
