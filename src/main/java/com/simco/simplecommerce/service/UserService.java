package com.simco.simplecommerce.service;

import com.simco.simplecommerce.auth.Role;
import com.simco.simplecommerce.dto.UserDTO;
import com.simco.simplecommerce.entity.User;
import com.simco.simplecommerce.mapper.UserMapper;
import com.simco.simplecommerce.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    public void saveUser(UserDTO user) {
        User userEntity = mapper.toEntity(user);
        userEntity.setPassword(passwordEncoder.encode(user.password()));
        userRepository.save(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Role role = Role.fromString(userEntity.getRole());
        final UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                userEntity.getUsername(), userEntity.getPassword(),
                List.of(new SimpleGrantedAuthority(role.name()))
        );
        return userDetails;
    }
}
