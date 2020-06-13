package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.UserOrganization;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserOrganizationRepository extends PagingAndSortingRepository<UserOrganization, Long> {

    Optional<UserOrganization> findByCnpj(String cnpj);

}
