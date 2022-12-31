package com.example.alba_prokect.security;


import com.example.alba_prokect.entity.User;
import com.example.alba_prokect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
     
     private final UserRepository userRepository;

     // username으로 UserDetails 반환
     @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          User user = userRepository.findByUserId(username)
               .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

          
          return new UserDetailsImpl(user, user.getUserId(), user.getPassword(), user.getNickname());
     }
}