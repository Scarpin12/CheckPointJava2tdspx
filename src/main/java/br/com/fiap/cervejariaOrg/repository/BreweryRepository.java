package br.com.fiap.cervejariaOrg.repository;

import br.com.fiap.cervejariaOrg.entity.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreweryRepository extends JpaRepository<Brewery, Long> {
}