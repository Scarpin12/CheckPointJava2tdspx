package br.com.fiap.cervejariaOrg.repository;

import br.com.fiap.cervejariaOrg.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BeerRepository extends JpaRepository<Beer, Long> {

    List<Beer> findByBreweryId(Long breweryId);

}