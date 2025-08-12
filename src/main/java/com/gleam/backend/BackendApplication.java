package com.gleam.backend;

import com.gleam.backend.enums.Acabamento;
import com.gleam.backend.model.Categoria;
import com.gleam.backend.model.Cliente;
import com.gleam.backend.model.Fornecedor;
import com.gleam.backend.model.Produto;
import com.gleam.backend.repository.CategoriaRepository;
import com.gleam.backend.repository.ClienteRepository;
import com.gleam.backend.repository.FornecedorRepository;
import com.gleam.backend.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	/**
	 * Este método é um CommandLineRunner para testes.
	 * Ele será executado assim que a aplicação iniciar.
	 * Use para popular o banco de dados com dados iniciais para teste.
	 */
	@Bean
	public CommandLineRunner init(
			CategoriaRepository categoriaRepository,
			FornecedorRepository fornecedorRepository,
			ClienteRepository clienteRepository,
			ProdutoRepository produtoRepository
	) {
		return args -> {
			System.out.println("--- INICIANDO TESTE DE INSERÇÃO DE DADOS ---");

			// 1. Criando e salvando uma Categoria
			System.out.println("Criando categoria 'Anéis'...");
			Categoria anelCategoria = new Categoria();
			anelCategoria.setNome("Anéis");
			anelCategoria.setDescricao("Anéis de diversos materiais e estilos.");
			categoriaRepository.save(anelCategoria);
			System.out.println(">>> Categoria 'Anéis' salva com sucesso!");

			// 2. Criando e salvando um Fornecedor
			System.out.println("Criando fornecedor 'Joias Finas LTDA'...");
			Fornecedor fornecedor = new Fornecedor();
			fornecedor.setNome("Joias Finas LTDA");
			fornecedor.setCnpj("11.222.333/0001-44");
			fornecedor.setTelefone("(47) 99999-8888");
			fornecedorRepository.save(fornecedor);
			System.out.println(">>> Fornecedor 'Joias Finas LTDA' salvo com sucesso!");

			// 3. Criando e salvando um Cliente
			System.out.println("Criando cliente 'Maria Silva'...");
			Cliente cliente = new Cliente();
			cliente.setNome("Maria Silva");
			cliente.setEmail("maria.silva@email.com");
			cliente.setCpf("123.456.789-00");
			cliente.setTelefone("(11) 98765-4321");
			clienteRepository.save(cliente);
			System.out.println(">>> Cliente 'Maria Silva' salvo com sucesso!");

			// 4. Criando e salvando um Produto que depende da Categoria e Fornecedor
			System.out.println("Criando produto 'Anel Solitário Banhado a Ouro'...");
			Produto anelProduto = new Produto();
			anelProduto.setNome("Anel Solitário Banhado a Ouro");
			anelProduto.setDescricao("Anel solitário com pedra de zircônia, banhado a ouro 18k.");
			anelProduto.setPrecoCusto(new BigDecimal("75.50"));
			anelProduto.setPrecoVenda(new BigDecimal("149.90"));

			anelProduto.setCodigoFornecedor("ANL-SOL-001");
			anelProduto.setAcabamento(Acabamento.BANHADO_OURO); // Usando o enum
			anelProduto.setCategoria(anelCategoria); // Associando a categoria criada
			anelProduto.setFornecedor(fornecedor); // Associando o fornecedor criado
			produtoRepository.save(anelProduto);
			System.out.println(">>> Produto 'Anel Solitário' salvo com sucesso!");

			System.out.println("--- TESTE DE INSERÇÃO FINALIZADO COM SUCESSO ---");
		};
	}
}