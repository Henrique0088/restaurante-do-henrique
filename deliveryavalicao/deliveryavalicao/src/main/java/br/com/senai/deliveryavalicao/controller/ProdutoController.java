package br.com.senai.deliveryavalicao.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.deliveryavalicao.dto.ProdutoRequest;
import br.com.senai.deliveryavalicao.model.Produto;
import br.com.senai.deliveryavalicao.service.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping ("/Produto")
public class ProdutoController {
    
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping 
    public ResponseEntity<Produto> cadastrar(@Valid @RequestBody  ProdutoRequest produto){

        if(produto.getNome() == null || produto.getPreco() == null) {
         return  ResponseEntity.badRequest().build();
        }

        Produto salvo = produtoService.cadastrar(produto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        
    }

    @GetMapping 
    public ResponseEntity<List<Produto>> listar(){
        List<Produto> produto = produtoService.listar();
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id, @RequestBody Produto novoProduto) {
        Optional<Produto> produtoAtualizado = produtoService.atualizar(id, novoProduto);

        if(produtoAtualizado.isPresent()) {
            return ResponseEntity.ok(produtoAtualizado.get());
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        boolean excluido = produtoService.excluir(id);

        if(excluido) {
            return ResponseEntity.noContent().build();
        } else {
            return  ResponseEntity.notFound().build();
        }
    }
}