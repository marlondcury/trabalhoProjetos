package seeder;

import java.time.LocalDate;
import model.Categoria;
import model.Cliente;
import model.Perfil;
import model.Produto;
import model.Usuario;
import repository.IUsuarioRepository;
import service.CalculoProdutoService;
import service.CategoriaService;
import service.ClienteService;
import service.ProdutoService;

public class Seeder {

    private final CategoriaService categoriaService;
    private final ProdutoService produtoService;
    private final CalculoProdutoService calculoProdutoService;
    private final ClienteService clienteService;
    private final IUsuarioRepository usuarioRepo;

    public Seeder(CategoriaService categoriaService, ProdutoService produtoService,
            CalculoProdutoService calculoProdutoService, ClienteService clienteService,
            IUsuarioRepository usuarioRepo) {
        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
        this.calculoProdutoService = calculoProdutoService;
        this.clienteService = clienteService;
        this.usuarioRepo = usuarioRepo;
    }

    public void executar() {
        carregarProdutos();
        carregarClientesEUsuarios();
    }

    private void carregarProdutos() {
        Categoria educacao = new Categoria("Educação", 25.0);
        Categoria alimentacao = new Categoria("Alimentação", 22.0);
        Categoria papelaria = new Categoria("Papelaria", 30.0);
        Categoria lazer = new Categoria("Lazer", 35.0);
        Categoria entretenimento = new Categoria("Entretenimento", 40.0);
        Categoria higiene = new Categoria("Higiene", 28.0);
        Categoria limpeza = new Categoria("Limpeza", 25.0);

        categoriaService.incluirCategoriaService(educacao);
        categoriaService.incluirCategoriaService(alimentacao);
        categoriaService.incluirCategoriaService(papelaria);
        categoriaService.incluirCategoriaService(lazer);
        categoriaService.incluirCategoriaService(entretenimento);
        categoriaService.incluirCategoriaService(higiene);
        categoriaService.incluirCategoriaService(limpeza);

        produtoService.incluirProduto(new Produto("Livro didático", 45.0, educacao));
        produtoService.incluirProduto(new Produto("Livro paradidático", 30.0, educacao));
        produtoService.incluirProduto(new Produto("Mochila escolar", 70.0, educacao));
        produtoService.incluirProduto(new Produto("Caderno universitário", 16.0, papelaria));
        produtoService.incluirProduto(new Produto("Lápis grafite HB", 1.2, papelaria));
        produtoService.incluirProduto(new Produto("Caneta esferográfica azul", 2.2, papelaria));
        produtoService.incluirProduto(new Produto("Borracha branca", 1.0, papelaria));
        produtoService.incluirProduto(new Produto("Apontador com depósito", 3.5, papelaria));
        produtoService.incluirProduto(new Produto("Jogo de tabuleiro", 55.0, lazer));
        produtoService.incluirProduto(new Produto("Bola recreativa", 40.0, lazer));
        produtoService.incluirProduto(new Produto("Quebra-cabeça 500 peças", 35.0, lazer));
        produtoService.incluirProduto(new Produto("Fone de ouvido", 48.0, entretenimento));
        produtoService.incluirProduto(new Produto("Caixa de som portátil", 80.0, entretenimento));
        produtoService.incluirProduto(new Produto("Revista de passatempos", 12.0, entretenimento));
        produtoService.incluirProduto(new Produto("Biscoito integral", 5.5, alimentacao));
        produtoService.incluirProduto(new Produto("Suco de uva 1 L", 9.0, alimentacao));
        produtoService.incluirProduto(new Produto("Barra de cereal", 3.2, alimentacao));
        produtoService.incluirProduto(new Produto("Sabonete", 2.8, higiene));
        produtoService.incluirProduto(new Produto("Creme dental", 5.5, higiene));
        produtoService.incluirProduto(new Produto("Detergente líquido", 2.6, limpeza));
        produtoService.incluirProduto(new Produto("Esponja multiuso", 1.7, limpeza));

        calculoProdutoService.calcular(LocalDate.now().minusDays(10));
    }

    private void carregarClientesEUsuarios() {
      
        Cliente c1 = new Cliente("Ana Paula Souza", "Rua das Flores, 120", "Centro", "Alegre", "ES");
        Cliente c2 = new Cliente("Bruno Henrique Lima", "Av. Brasil, 45", "Vila Nova", "Vitória", "ES");
        Cliente c3 = new Cliente("Carla Mendes", "Rua Sete, 300", "Jardim", "Cachoeiro de Itapemirim", "ES");
        clienteService.incluirCliente(c1);
        clienteService.incluirCliente(c2);
        clienteService.incluirCliente(c3);

        usuarioRepo.incluirUsuario(new Usuario("Administrador do Sistema", "admin",
                "admin@supermercado.com", "admin", Perfil.ADMINISTRADOR, null));
        usuarioRepo.incluirUsuario(new Usuario("Marlon Domingos", "atendente",
                "atendente@supermercado.com", "123", Perfil.ATENDENTE, null));
        usuarioRepo.incluirUsuario(new Usuario("Ana Paula Souza", "ana",
                "ana@email.com", "123", Perfil.CLIENTE, c1));
    }
}