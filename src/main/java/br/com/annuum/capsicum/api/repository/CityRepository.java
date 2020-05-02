package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.City;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CityRepository extends PagingAndSortingRepository<City, Long> {

    Optional<City> findByGooglePlaceCityIdentifier(String googlePlaceIdentifier);

}
