package br.com.senai.deliveryavalicao.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import br.com.senai.deliveryavalicao.dto.ProdutoRequest;
import br.com.senai.deliveryavalicao.model.Produto;
import br.com.senai.deliveryavalicao.model.Restaurante;
import br.com.senai.deliveryavalicao.repository.ProdutoRepository;
import br.com.senai.deliveryavalicao.repository.RestauranteRepository;

@Service
public class ProdutoService {
    
    private  final ProdutoRepository produtoRepository;
    private  final RestauranteRepository restauranteRepository;

    public ProdutoService (ProdutoRepository produtoRepository, RestauranteRepository restauranteRepository){
        this.produtoRepository = produtoRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public  Produto cadastrar(ProdutoRequest produto){
        Produto novoProduto = new Produto();
        novoProduto.setPreco(produto.getPreco());

        Restaurante restauranteEncontrado = restauranteRepository.findById(produto.getRestauranteId())
        .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        novoProduto.setRestaurante(restauranteEncontrado);
        
        return produtoRepository.save(novoProduto);
    }

    public Optional<Produto> buscarPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    public Optional<Produto> atualizar(Integer id, Produto novoProduto) {
    Optional<Produto> produtoEncontrado = produtoRepository.findById(id);

    if(produtoEncontrado.isEmpty()){
        return Optional.empty();
    }

     Produto produto = produtoEncontrado.get();

     produto.setNome(novoProduto.getNome());
     produto.setRestaurante(novoProduto.getRestaurante());

     return Optional.of(produtoRepository.save(produto));
    }

    public boolean excluir(Integer id) {
        Optional<Produto> produtoEncontrado = produtoRepository.findById(id);

        if(produtoEncontrado.isEmpty()){
            return false;
        }
        
        produtoRepository.deleteById(id);
        return true;
    }

      public List<Produto> listar(){
        return  produtoRepository.findAll();
    }
}

