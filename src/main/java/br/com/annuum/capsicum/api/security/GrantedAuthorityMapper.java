package br.com.annuum.capsicum.api.security;

import br.com.annuum.capsicum.api.mapper.Mapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class GrantedAuthorityMapper implements Mapper<String, GrantedAuthority> {

  @Override
  public GrantedAuthority map(final String role) {
    return new SimpleGrantedAuthority(role);
  }

}
