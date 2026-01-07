package com.lucasgfbatista.AgilStore.view;

import com.lucasgfbatista.AgilStore.dto.CategoriaRequestDTO;
import com.lucasgfbatista.AgilStore.dto.CategoriaResponseDTO;
import com.lucasgfbatista.AgilStore.dto.ProdutoRequestDTO;
import com.lucasgfbatista.AgilStore.dto.ProdutoResponseDTO;
import com.lucasgfbatista.AgilStore.service.CategoriaService;
import com.lucasgfbatista.AgilStore.service.ProdutoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Component
@Order(2)
public class TerminalRunner implements CommandLineRunner {

    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;
    private final Scanner scanner = new Scanner(System.in);

    public TerminalRunner(ProdutoService produtoService, CategoriaService categoriaService) {
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
    }


    @Override
    public void run(String... args) {
        while (true) {
            mostrarMenu();

            int opcao;
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Digite um número válido!");
                continue;
            }

            switch (opcao) {
                case 1 -> adicionarProduto();
                case 2 -> listarProdutos();
                case 3 -> atualizarProduto();
                case 4 -> excluirProduto();
                case 5 -> buscarProduto();
                case 6 -> listarCategorias();
                case 7 -> adicionarCategoria();
                case 8 -> atualizarCategoria();
                case 9 -> excluirCategoria();
                case 10 -> buscarCategoria();


                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println(" Opção inválida!");
            }
        }
    }

    // -------------------------
    // Menu principal
    private void mostrarMenu() {
        System.out.println("\n===================================");
        System.out.println("         AGILSTORE INVENTÁRIO      ");
        System.out.println("===================================");
        //Produtos
        System.out.println("1 - Adicionar Produto");
        System.out.println("2 - Listar Produtos");
        System.out.println("3 - Atualizar Produto");
        System.out.println("4 - Excluir Produto");
        System.out.println("5 - Buscar Produto");

        //Categorrias
        System.out.println("6 - Listar Categorias");
        System.out.println("7 - Adicionar Categoria");
        System.out.println("8 - Atualizar Categoria");
        System.out.println("9 - Excluir Categoria");
        System.out.println("10 - Buscar Categoria");

        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    // -------------------------
    // Tela Adicionar Produto
    private void adicionarProduto() {
        try {
            System.out.println("\n=== ADICIONAR PRODUTO ===");
            System.out.print("Nome do produto: ");
            String nome = scanner.nextLine();

            System.out.print("ID da Categoria: ");
            long categoriaId = Long.parseLong(scanner.nextLine());

            System.out.print("Quantidade em estoque: ");
            int qnt = Integer.parseInt(scanner.nextLine());

            System.out.print("Preço: ");
            BigDecimal preco = new BigDecimal(scanner.nextLine());

            ProdutoRequestDTO dto = new ProdutoRequestDTO(nome, categoriaId, qnt, preco);
            produtoService.criarProduto(dto);

            System.out.println(" Produto adicionado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println(" Entrada inválida! Tente novamente.");
        } catch (RuntimeException e) {
            System.out.println(" Erro: " + e.getMessage());
        }
    }

    // -------------------------
    // Tela Listar Produtos
    private void listarProdutos() {
        System.out.println("\n=== LISTA DE PRODUTOS ===");

        List<ProdutoResponseDTO> produtos = produtoService.listarTodosProdutos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("+----+----------------+----------+-------+---------+");
        System.out.println("| ID | Nome           | Categoria| Qnt   | Preço   |");
        System.out.println("+----+----------------+----------+-------+---------+");

        for (ProdutoResponseDTO p : produtos) {
            System.out.printf("| %-2d | %-14s | %-8d | %-5d | %7.2f |\n",
                    p.id(), p.nome(), p.categoriaId(), p.qntEstoque(), p.preco());
        }

        System.out.println("+----+----------------+----------+-------+---------+");
    }

    // -------------------------
    // Tela Atualizar Produto
    private void atualizarProduto() {
        try {
            System.out.println("\n=== ATUALIZAR PRODUTO ===");

            System.out.print("ID do produto a atualizar: ");
            Long id = Long.parseLong(scanner.nextLine());

            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();

            System.out.print("Novo ID da Categoria: ");
            long categoriaId = Long.parseLong(scanner.nextLine());

            System.out.print("Nova quantidade: ");
            int qnt = Integer.parseInt(scanner.nextLine());

            System.out.print("Novo preço: ");
            BigDecimal preco = new BigDecimal(scanner.nextLine());

            ProdutoRequestDTO dto = new ProdutoRequestDTO(nome, categoriaId, qnt, preco);
            produtoService.atualizarProduto(id, dto);

            System.out.println(" Produto atualizado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println(" Entrada inválida! Tente novamente.");
        } catch (RuntimeException e) {
            System.out.println(" Erro: " + e.getMessage());
        }
    }

    // -------------------------
    // Tela Excluir Produto
    private void excluirProduto() {
        try {
            System.out.println("\n=== EXCLUIR PRODUTO ===");

            System.out.print("ID do produto a excluir: ");
            Long id = Long.parseLong(scanner.nextLine());

            produtoService.deletarProduto(id);

            System.out.println(" Produto excluído com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println(" Entrada inválida! Tente novamente.");
        } catch (RuntimeException e) {
            System.out.println(" Erro: " + e.getMessage());
        }
    }

    // -------------------------
    // Tela Buscar Produto
    private void buscarProduto() {
        try {
            System.out.println("\n=== BUSCAR PRODUTO ===");

            System.out.print("ID do produto: ");
            Long id = Long.parseLong(scanner.nextLine());

            ProdutoResponseDTO produto = produtoService.buscarProduto(id);

            System.out.println("\n--- DETALHES DO PRODUTO ---");
            System.out.println("ID: " + produto.id());
            System.out.println("Nome: " + produto.nome());
            System.out.println("Categoria: " + produto.categoriaId());
            System.out.println("Quantidade: " + produto.qntEstoque());
            System.out.println("Preço: " + produto.preco());
            System.out.println("----------------------------");

        } catch (NumberFormatException e) {
            System.out.println(" Entrada inválida! Tente novamente.");
        } catch (RuntimeException e) {
            System.out.println(" Erro: " + e.getMessage());
        }
    }

    // Tela Adicionar Categoria
    private void adicionarCategoria() {
        try {
            System.out.println("\n=== ADICIONAR CATEGORIA ===");

            System.out.print("Nome da categoria: ");
            String nome = scanner.nextLine();

            CategoriaRequestDTO dto = new CategoriaRequestDTO(nome);
            categoriaService.criarCategoria(dto);

            System.out.println(" Categoria criada com sucesso!");
        } catch (RuntimeException e) {
            System.out.println(" Erro: " + e.getMessage());
        }
    }

    // Tela Listar Categoria
    private void listarCategorias() {
        System.out.println("\n=== LISTA DE CATEGORIAS ===");

        List<CategoriaResponseDTO> categorias =
                categoriaService.listarTodosCategorias();

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
            return;
        }

        System.out.println("+----+----------------------+");
        System.out.println("| ID | Nome                 |");
        System.out.println("+----+----------------------+");

        for (CategoriaResponseDTO c : categorias) {
            System.out.printf("| %-2d | %-20s |\n", c.id(), c.nome());
        }

        System.out.println("+----+----------------------+");
    }


    // Tela Atualizar Produto
    private void atualizarCategoria() {
        try {
            System.out.println("\n=== ATUALIZAR CATEGORIA ===");

            System.out.print("ID da categoria: ");
            Long id = Long.parseLong(scanner.nextLine());

            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();

            CategoriaRequestDTO dto = new CategoriaRequestDTO(nome);
            categoriaService.atualizarCategoria(id, dto);

            System.out.println(" Categoria atualizada com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println(" ID inválido!");
        } catch (RuntimeException e) {
            System.out.println(" Erro: " + e.getMessage());
        }
    }


    // -------------------------
    // Tela Excluir Produto
    private void excluirCategoria() {
        try {
            System.out.println("\n=== EXCLUIR CATEGORIA ===");

            System.out.print("ID da categoria: ");
            Long id = Long.parseLong(scanner.nextLine());

            categoriaService.deletarCategoria(id);

            System.out.println(" Categoria excluída com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println(" ID inválido!");
        } catch (RuntimeException e) {
            System.out.println(" Erro: " + e.getMessage());
        }
    }


    // -------------------------
    // Tela Buscar Produto
    private void buscarCategoria() {
        try {
            System.out.println("\n=== BUSCAR CATEGORIA ===");
            System.out.println("1 - Buscar por ID");
            System.out.println("2 - Buscar por Nome");
            System.out.print("Escolha: ");

            int opcao = Integer.parseInt(scanner.nextLine());

            CategoriaResponseDTO categoria;

            if (opcao == 1) {
                System.out.print("ID da categoria: ");
                Long id = Long.parseLong(scanner.nextLine());
                categoria = categoriaService.buscarCategoria(id);

            } else if (opcao == 2) {
                System.out.print("Nome da categoria: ");
                String nome = scanner.nextLine();
                categoria = categoriaService.buscarCategoria(nome);

            } else {
                System.out.println(" Opção inválida!");
                return;
            }

            System.out.println("\n--- DETALHES DA CATEGORIA ---");
            System.out.println("ID: " + categoria.id());
            System.out.println("Nome: " + categoria.nome());
            System.out.println("-----------------------------");

        } catch (NumberFormatException e) {
            System.out.println(" Entrada inválida!");
        } catch (RuntimeException e) {
            System.out.println(" Erro: " + e.getMessage());
        }
    }


}
