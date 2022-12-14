package com.heitor.venda.repository;

import com.heitor.venda.domain.Categoria;
import com.heitor.venda.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT prod FROM Produto prod INNER JOIN prod.categorias cat WHERE prod.nome LIKE %:nome% AND cat IN :cats")
    Page<Produto> search(@Param("nome") String nome, @Param("cats") List<Categoria> cats, Pageable pageRequest);
}
