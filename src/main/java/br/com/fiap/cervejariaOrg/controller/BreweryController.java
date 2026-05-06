package br.com.fiap.cervejariaOrg.controller;

import br.com.fiap.cervejariaOrg.entity.Beer;
import br.com.fiap.cervejariaOrg.entity.Brewery;
import br.com.fiap.cervejariaOrg.service.BeerService;
import br.com.fiap.cervejariaOrg.service.BreweryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/breweries")
@Tag(name = "Brewery", description = "Endpoints para gerenciamento de Cervejarias")
public class BreweryController {

    @Autowired
    private BreweryService service;

    @GetMapping
    @Operation(summary = "Lista todas as cervejarias", description = "Retorna uma lista de todas as cervejarias cadastradas")
    public ResponseEntity<List<Brewery>> getAllBreweries() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca cervejaria por ID", description = "Retorna uma cervejaria específica baseada no seu ID")
    public ResponseEntity<Brewery> getBreweryById(@PathVariable Long id) {
        Optional<Brewery> brewery = service.findById(id);
        return brewery.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cadastra nova cervejaria", description = "Adiciona uma nova cervejaria ao banco de dados")
    public ResponseEntity<Brewery> createBrewery(@RequestBody Brewery brewery) {
        Brewery savedBrewery = service.save(brewery);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBrewery);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza cervejaria", description = "Atualiza os dados de uma cervejaria existente")
    public ResponseEntity<Brewery> updateBrewery(@PathVariable Long id, @RequestBody Brewery brewery) {
        Brewery updatedBrewery = service.update(id, brewery);
        if (updatedBrewery != null) {
            return ResponseEntity.ok(updatedBrewery);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove cervejaria", description = "Deleta uma cervejaria do banco de dados baseada no seu ID")
    public ResponseEntity<Void> deleteBrewery(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


