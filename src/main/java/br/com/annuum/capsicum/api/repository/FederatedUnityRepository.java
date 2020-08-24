package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.FederatedUnity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FederatedUnityRepository extends PagingAndSortingRepository<FederatedUnity, Long> {

    List<FederatedUnity> findAllByOrderByName();

}
