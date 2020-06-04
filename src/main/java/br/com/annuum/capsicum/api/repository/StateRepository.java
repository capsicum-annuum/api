package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.City;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StateRepository extends PagingAndSortingRepository<City, Long> {

}
