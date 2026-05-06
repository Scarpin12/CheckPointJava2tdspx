package br.com.fiap.cervejariaOrg.service;

import br.com.fiap.cervejariaOrg.entity.Beer;
import br.com.fiap.cervejariaOrg.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeerService {

    @Autowired
    private BeerRepository repository;

    @Cacheable(value = "beers")
    public List<Beer> findAll() {
        return repository.findAll();
    }

    @Cacheable(value = "beer", key = "#id")
    public Optional<Beer> findById(Long id) {
        return repository.findById(id);
    }

    @Cacheable(value = "beersByBrewery", key = "#breweryId")
    public List<Beer> findByBreweryId(Long breweryId) {
        return repository.findByBreweryId(breweryId);
    }

    @CacheEvict(value = {"beers", "beer", "beersByBrewery"}, allEntries = true)
    public Beer save(Beer beer) {
        return repository.save(beer);
    }

    @CacheEvict(value = {"beers", "beer", "beersByBrewery"}, allEntries = true)
    public Beer update(Long id, Beer updatedBeer) {
        if (repository.existsById(id)) {
            updatedBeer.setId(id);
            return repository.save(updatedBeer);
        }
        return null;
    }

    @CacheEvict(value = {"beers", "beer", "beersByBrewery"}, allEntries = true)
    public void delete(Long id) {
        repository.deleteById(id);
    }
}