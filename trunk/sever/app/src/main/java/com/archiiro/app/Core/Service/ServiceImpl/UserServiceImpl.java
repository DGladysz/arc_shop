package com.archiiro.app.Core.Service.ServiceImpl;

import com.archiiro.app.Core.Domain.Role;
import com.archiiro.app.Core.Domain.User;
import com.archiiro.app.Core.Dto.RoleDto;
import com.archiiro.app.Core.Dto.UserDto;
import com.archiiro.app.Core.Repository.RoleRepository;
import com.archiiro.app.Core.Repository.UserRepository;
import com.archiiro.app.Core.Service.UserService;
import com.archiiro.app.Core.Other.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service @Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepos;

    @Autowired
    private RoleRepository roleRepos;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = this.userRepos.getUserByUsername(username);
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
    public UserDto saveUser(Long id, UserDto dto) {
        if(dto == null) {
            return null;
        }
        User user = null;
        if(id != null) {
            Optional<User> userOptional = this.userRepos.findById(id);
            if(userOptional.isPresent()) {
                user = userOptional.get();
            }
        }
        if(user == null && dto.getId() != null) {
            Optional<User> userOptional = this.userRepos.findById(dto.getId());
            if(userOptional.isPresent()) {
                user = userOptional.get();
            }
        }
        if(user == null) {
            user = new User();
        }
        if(dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }
        if(dto.getPassword() != null) {
            user.setPassword(Utils.getHashPassword(dto.getPassword()));
        }
        if(dto.getRoles() != null && dto.getRoles().size() > 0) {
            HashSet<Role> roles = new HashSet<Role>();
            Iterator<RoleDto> iterator = dto.getRoles().iterator();
            while (iterator.hasNext()) {
                RoleDto roleDto = iterator.next();
                Role role = null;
                if(roleDto.getId() != null) {
                    Optional<Role> roleOptional = this.roleRepos.findById(roleDto.getId());
                    if(roleOptional.isPresent()) {
                        role = roleOptional.get();
                    }
                }
                if(role != null) {
                    roles.add(role);
                }
            }
            if(user.getRoles() != null && user.getRoles().size() > 0) {
                user.getRoles().clear();  // Trường hợp có dl
                user.getRoles().addAll(roles);
            } else {
                user.setRoles(roles);   // Trường hợp chưa có dl
            }
        } else if(dto.getRoles() != null) {
            if(user.getRoles() != null) {
                user.getRoles().clear();
            }
        }
        user = this.userRepos.save(user);
        return new UserDto(user);
    }

    @Override
    public Boolean deleteDto(Long id) {
        if(id != null) {
            Optional<User> userOptional = this.userRepos.findById(id);
            if(userOptional.isPresent()) {
                User user = userOptional.get();
                this.userRepos.delete(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public UserDto findByUsername(String username) {
        UserDto result = this.userRepos.getUserByUsername(username);
        return result!= null ? result : null;
    }
}
