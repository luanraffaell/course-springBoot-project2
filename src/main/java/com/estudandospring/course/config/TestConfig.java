package com.estudandospring.course.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.estudandospring.course.domain.Categoria;
import com.estudandospring.course.domain.Cidade;
import com.estudandospring.course.domain.Cliente;
import com.estudandospring.course.domain.Endereco;
import com.estudandospring.course.domain.Estado;
import com.estudandospring.course.domain.ItemPedido;
import com.estudandospring.course.domain.Pagamento;
import com.estudandospring.course.domain.PagamentoComBoleto;
import com.estudandospring.course.domain.PagamentoComCartao;
import com.estudandospring.course.domain.Pedido;
import com.estudandospring.course.domain.Produto;
import com.estudandospring.course.domain.enums.EstadoPagamento;
import com.estudandospring.course.domain.enums.TipoCliente;
import com.estudandospring.course.repositories.CategoriaRepository;
import com.estudandospring.course.repositories.CidadeRepository;
import com.estudandospring.course.repositories.ClienteRepository;
import com.estudandospring.course.repositories.EnderecoRepository;
import com.estudandospring.course.repositories.EstadoRepository;
import com.estudandospring.course.repositories.ItemPedidoRepository;
import com.estudandospring.course.repositories.PagamentoRepository;
import com.estudandospring.course.repositories.PedidoRepository;
import com.estudandospring.course.repositories.ProdutoRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	EstadoRepository estadoRepository;

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	PagamentoRepository pagamentoRepository;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "S??o Paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "S??o Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "395353105", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua flores", "300", "apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Av Matos", "105", "Sala 800", "Centro", "38770812", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p3, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}

}
