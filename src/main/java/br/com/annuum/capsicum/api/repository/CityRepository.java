package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.City;
import br.com.annuum.capsicum.api.domain.dto.CityDto;
import br.com.annuum.capsicum.api.domain.dto.SkillDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CityRepository extends PagingAndSortingRepository<City, Long> {

    @Query("SELECT new br.com.annuum.capsicum.api.domain.dto.CityDto(c.id, c.name) FROM City c")
    List<CityDto> retrieveAllCitiesAsCityDtoByFederatedUnity(Long idFederatedUnity);

}
