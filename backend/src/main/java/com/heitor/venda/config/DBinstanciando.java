package com.heitor.venda.config;

import com.heitor.venda.domain.Categoria;
import com.heitor.venda.domain.Produto;
import com.heitor.venda.repository.CategoriaRepository;
import com.heitor.venda.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
@Slf4j
public class DBinstanciando {

    private final CategoriaRepository catRepo;
    private final ProdutoRepository prodRepo;

    @Autowired
    public DBinstanciando(CategoriaRepository catRepo, ProdutoRepository prodRepo) {
        this.catRepo = catRepo;
        this.prodRepo = prodRepo;
    }

    public void criarCategorias(){
        log.info("CRIANDO CATEGORIAS");
        Categoria cat1 = new Categoria(null, "Cama, mesa e banho", null);
        Categoria cat2 = new Categoria(null, "Ferramentas",        null);
        Categoria cat3 = new Categoria(null, "Informática",        null);
        Categoria cat4 = new Categoria(null, "Eletrônicos",        null);
        Categoria cat5 = new Categoria(null, "Brinquedos",         null);
        Categoria cat6 = new Categoria(null, "Automotivos",        null);
        Categoria cat7 = new Categoria(null, "Animais",            null);
        Categoria cat8 = new Categoria(null, "Calçados",            null);

        catRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
    }

    public void criarProduto(){

        Produto prod1  = new Produto(null, "Cama", 150.0, new ArrayList<>(), null);
        Produto prod2  = new Produto(null, "Toalha", 5.87,  new ArrayList<>(), null);
        Produto prod3  = new Produto(null, "Chave de fenda", 5.87,  new ArrayList<>(), null);
        Produto prod4  = new Produto(null, "Mouse", 15.87,  new ArrayList<>(), null);
        Produto prod5  = new Produto(null, "Torradeira", 150.87,  new ArrayList<>(), null);
        Produto prod6  = new Produto(null, "Geladeira", 2150.87,  new ArrayList<>(), null);
        Produto prod7  = new Produto(null, "Notebook", 5150.87,  new ArrayList<>(), null);
        Produto prod8  = new Produto(null, "Ração de cahorro", 5.87,  new ArrayList<>(), null);
        Produto prod9  = new Produto(null, "Shampoo", 15.00,  new ArrayList<>(), null);
        Produto prod10 = new Produto(null, "Sapato", 100.00,  new ArrayList<>(), null);
        Produto prod11 = new Produto(null, "Chinelo", 15.00,  new ArrayList<>(), null);
        Produto prod12 = new Produto(null, "Guarda Roupa", 325.00,  new ArrayList<>(), null);
        Produto prod13 = new Produto(null, "Lavadoura", 856.00,  new ArrayList<>(), null);
        Produto prod14 = new Produto(null, "Liquidificador", 105.00,  new ArrayList<>(), null);
        Produto prod15 = new Produto(null, "Pneu", 100.00,  new ArrayList<>(), null);

        prod1.getCategorias().add(catRepo.getById(1));
        prod2.getCategorias().add(catRepo.getById(1));
        prod3.getCategorias().add(catRepo.getById(2));
        prod4.getCategorias().add(catRepo.getById(3));
        prod5.getCategorias().add(catRepo.getById(4));
        prod6.getCategorias().add(catRepo.getById(4));
        prod7.getCategorias().add(catRepo.getById(3));
        prod8.getCategorias().add(catRepo.getById(7));
        prod9.getCategorias().add(catRepo.getById(1));
        prod10.getCategorias().add(catRepo.getById(2));
        prod11.getCategorias().add(catRepo.getById(4));
        prod12.getCategorias().add(catRepo.getById(1));
        prod13.getCategorias().add(catRepo.getById(4));
        prod14.getCategorias().add(catRepo.getById(4));
        prod15.getCategorias().add(catRepo.getById(6));

        prodRepo.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11, prod12, prod13, prod14, prod15));
    }
}
