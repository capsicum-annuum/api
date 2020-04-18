package br.com.annuum.capsicum.api.security;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;

@ExtendWith(MockitoExtension.class)
class GrantedAuthorityMapperTest {

  @InjectMocks
  private GrantedAuthorityMapper mapper;

  @Test
  public void shouldMapRoleAsStringToGrantedAuthority() {

    final String randomRole = RandomStringUtils.randomAlphabetic(10);
    final GrantedAuthority authority = mapper.map(randomRole);

    Assertions.assertEquals(randomRole, authority.getAuthority());
  }
}