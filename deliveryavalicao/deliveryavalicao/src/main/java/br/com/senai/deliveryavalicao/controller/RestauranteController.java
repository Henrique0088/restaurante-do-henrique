package br.com.senai.deliveryavalicao.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.senai.deliveryavalicao.model.Restaurante;
import br.com.senai.deliveryavalicao.service.RestauranteService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @PostMapping
    public ResponseEntity<Restaurante> Cadastrar(@Valid @RequestBody Restaurante restaurante) {
        Restaurante restauranteCadastrado = restauranteService.Cadastrar(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> Listar() {
        List<Restaurante> restaurante = restauranteService.listar();
        return ResponseEntity.ok(restaurante);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarPorId(@PathVariable Integer id) {
        Optional<Restaurante> restaurante = restauranteService.buscarPorId(id);
        return restaurante.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Integer id, @Valid @RequestBody Restaurante novosDados) {
        Optional<Restaurante> restauranteAtualizado = restauranteService.atualizar(id, novosDados);

        if (restauranteAtualizado.isPresent()) {
            return ResponseEntity.ok(restauranteAtualizado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        boolean excluido = restauranteService.excluir(id);

        if (excluido) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

