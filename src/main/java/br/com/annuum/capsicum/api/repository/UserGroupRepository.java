package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.UserGroup;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserGroupRepository extends PagingAndSortingRepository<UserGroup, Long> {
}
