package com.heitor.venda.config;

import com.heitor.venda.domain.Categoria;
import com.heitor.venda.domain.Cliente;
import com.heitor.venda.domain.Endereco;
import com.heitor.venda.domain.Produto;
import com.heitor.venda.enums.PerfilCliente;
import com.heitor.venda.enums.TipoCliente;
import com.heitor.venda.repository.CategoriaRepository;
import com.heitor.venda.repository.ClienteRepository;
import com.heitor.venda.repository.EnderecoRepository;
import com.heitor.venda.repository.ProdutoRepository;
import com.heitor.venda.service.util.BuscaEnderecoPorCep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class DBinstanciando {

    private final CategoriaRepository catRepo;
    private final ProdutoRepository prodRepo;
    private final ClienteRepository cliRepo;
    private final EnderecoRepository endRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DBinstanciando(CategoriaRepository catRepo, ProdutoRepository prodRepo, ClienteRepository cliRepo, EnderecoRepository endRepo, BCryptPasswordEncoder passwordEncoder) {
        this.catRepo = catRepo;
        this.prodRepo = prodRepo;
        this.cliRepo = cliRepo;
        this.endRepo = endRepo;
        this.passwordEncoder = passwordEncoder;
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
        Categoria cat8 = new Categoria(null, "Calçados",           null);
        Categoria cat9 = new Categoria(null, "Perfumaria",         null);

        catRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9));
    }

    public void criarProduto(){

        log.info("CRIANDO PRODUTO");

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

    public  void criarCliente(){

        log.info("CRIANDO CLIENTE");

        String senha =  passwordEncoder.encode("123");

        Set<PerfilCliente> perfisBasico = new HashSet<>();
        perfisBasico.add(PerfilCliente.CLIENTE);

        Set<PerfilCliente> perfisAvancado = new HashSet<>();
        perfisAvancado.add(PerfilCliente.CLIENTE);
        perfisAvancado.add(PerfilCliente.ADMIN);

        Cliente cli1 = new Cliente(null, "Americo", "americo@email.com", senha, "698.135.831-00",TipoCliente.PESSOAFISICA, perfisBasico, new ArrayList<>(), new HashSet<>(), null);
        Cliente cli2 = new Cliente(null, "Amelia", "amelia@email.com", senha, "928.224.074-60", TipoCliente.PESSOAFISICA, perfisBasico, new ArrayList<>(), new HashSet<>(), null);
        Cliente cli3 = new Cliente(null, "Heitor", "heitorhfbr@gmail.com", senha, "964.298.249-85", TipoCliente.PESSOAFISICA, perfisAvancado, new ArrayList<>(), new HashSet<>(), null);

        cliRepo.saveAll(Arrays.asList(cli1, cli2, cli3));

        Endereco end1 = endRepo.save(BuscaEnderecoPorCep.buscarCep("06725-050"));
        Endereco end2 = endRepo.save(BuscaEnderecoPorCep.buscarCep("14175-340"));
        Endereco end3 = endRepo.save(BuscaEnderecoPorCep.buscarCep("09764-210"));
        Endereco end4 = endRepo.save(BuscaEnderecoPorCep.buscarCep("07950-140"));

        cli1.getEnderecoList().add(end1);
        cli2.getEnderecoList().add(end2);
        cli2.getEnderecoList().add(end3);
        cli3.getEnderecoList().add(end4);

        cli1.getTelefones().add("(11) 9895-8875");
        cli1.getTelefones().add("(11) 9291-8173");
        cli1.getTelefones().add("(11) 9678-8234");
        cli2.getTelefones().add("(11) 9378-7542");
        cli3.getTelefones().add("(11) 9345-6790");

        cliRepo.saveAll(Arrays.asList(cli1, cli2, cli3));
    }

}
