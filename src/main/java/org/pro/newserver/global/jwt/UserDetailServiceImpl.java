package org.pro.newserver.global.jwt;

import lombok.RequiredArgsConstructor;
import org.pro.newserver.domain.user.infrastructure.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    return new UserDetailsImpl(
        userRepository
            .findByEmail(name)
            .orElseThrow(() -> new UsernameNotFoundException("회원이 존재하지 않습니다.")));
  }
}
