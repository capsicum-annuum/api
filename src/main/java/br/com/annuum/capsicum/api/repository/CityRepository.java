package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.City;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CityRepository extends PagingAndSortingRepository<City, Long> {

}
