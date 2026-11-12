package br.com.senai.deliveryavalicao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.senai.deliveryavalicao.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
}
