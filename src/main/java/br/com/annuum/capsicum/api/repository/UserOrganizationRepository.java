package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.UserOrganization;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserOrganizationRepository extends PagingAndSortingRepository<UserOrganization, Long> {
}
