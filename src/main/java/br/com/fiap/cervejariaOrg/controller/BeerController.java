package br.com.fiap.cervejariaOrg.controller;

import br.com.fiap.cervejariaOrg.entity.Beer;
import br.com.fiap.cervejariaOrg.service.BeerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/beers")
@Tag(name = "Beer", description = "Endpoints para gerenciamento de Cervejas")
public class BeerController {

    @Autowired
    private BeerService service;

    @GetMapping
    @Operation(summary = "Lista todas as cervejas", description = "Retorna uma lista de todas as cervejas cadastradas")
    public ResponseEntity<List<Beer>> getAllBeers() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca cerveja por ID", description = "Retorna uma cerveja específica baseada no seu ID")
    public ResponseEntity<Beer> getBeerById(@PathVariable Long id) {
        Optional<Beer> beer = service.findById(id);
        return beer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/brewery/{id}")
    @Operation(summary = "Lista cervejas por cervejaria", description = "Retorna todas as cervejas associadas ao ID de uma cervejaria específica")
    public ResponseEntity<List<Beer>> getBeersByBreweryId(@PathVariable Long id) {
        List<Beer> beers = service.findByBreweryId(id);
        return ResponseEntity.ok(beers);
    }

    @PostMapping
    @Operation(summary = "Cadastra nova cerveja", description = "Adiciona uma nova cerveja ao banco de dados")
    public ResponseEntity<Beer> createBeer(@RequestBody Beer beer) {
        Beer savedBeer = service.save(beer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBeer);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza cerveja", description = "Atualiza os dados de uma cerveja existente")
    public ResponseEntity<Beer> updateBeer(@PathVariable Long id, @RequestBody Beer beer) {
        Beer updatedBeer = service.update(id, beer);
        if (updatedBeer != null) {
            return ResponseEntity.ok(updatedBeer);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove cerveja", description = "Deleta uma cerveja do banco de dados baseada no seu ID")
    public ResponseEntity<Void> deleteBeer(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


