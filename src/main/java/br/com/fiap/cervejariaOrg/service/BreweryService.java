package br.com.fiap.cervejariaOrg.service;

import br.com.fiap.cervejariaOrg.entity.Brewery;
import br.com.fiap.cervejariaOrg.repository.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BreweryService {

    @Autowired
    private BreweryRepository repository;

    @Cacheable(value = "breweries")
    public List<Brewery> findAll() {
        //return repository.findAll();
        return repository.findAll();
    }

    @Cacheable(value = "brewery", key = "#id")
    public Optional<Brewery> findById(Long id) {
        return repository.findById(id);
    }

    @CacheEvict(value = {"breweries", "brewery"}, allEntries = true)
    public Brewery save(Brewery brewery) {
        return repository.save(brewery);
    }

    @CacheEvict(value = {"breweries", "brewery"}, allEntries = true)
    public Brewery update(Long id, Brewery updatedBrewery) {
        if (repository.existsById(id)) {
            updatedBrewery.setId(id);
            return repository.save(updatedBrewery);
        }
        return null;
    }

    @CacheEvict(value = {"breweries", "brewery"}, allEntries = true)
    public void delete(Long id) {
        repository.deleteById(id);
    }
}