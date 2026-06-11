//package autocenter;

import java.util.Scanner;

// =============================================
// MÓDULO: Cadastros.java
// Responsabilidade: Cadastro de Mecânicos, Veículos e Peças
// =============================================

public class Cadastros {

    static void cadastrarMecanico(Scanner sc,
                                   Mecanico[] mecanicos,
                                   int[] totalMecanicos,
                                   int MAX) {
        if (totalMecanicos[0] >= MAX) { System.out.println("Limite atingido!"); return; }

        Mecanico m = new Mecanico();
        System.out.print("Código do mecânico: ");
        m.codigo = sc.nextInt(); sc.nextLine();

        for (int i = 0; i < totalMecanicos[0]; i++) {
            if (mecanicos[i].codigo == m.codigo) {
                System.out.println("Erro: código já cadastrado!"); return;
            }
        }

        System.out.print("Nome: ");
        m.nome = sc.nextLine();
        System.out.print("Especialidade: ");
        m.especialidade = sc.nextLine();

        mecanicos[totalMecanicos[0]++] = m;
        Arquivos.salvarMecanicos(mecanicos, totalMecanicos[0]);
        System.out.println("✔ Mecânico cadastrado e salvo!");
    }

    static void cadastrarVeiculo(Scanner sc,
                                  Veiculo[] veiculos,
                                  int[] totalVeiculos,
                                  int MAX) {
        if (totalVeiculos[0] >= MAX) { System.out.println("Limite atingido!"); return; }

        Veiculo v = new Veiculo();
        System.out.print("Placa do veículo: ");
        v.placa = sc.nextLine().toUpperCase();

        for (int i = 0; i < totalVeiculos[0]; i++) {
            if (veiculos[i].placa.equals(v.placa)) {
                System.out.println("Erro: placa já cadastrada!"); return;
            }
        }

        System.out.print("Nome do dono: ");
        v.nomeDono = sc.nextLine();
        System.out.print("Modelo do veículo: ");
        v.modelo = sc.nextLine();

        veiculos[totalVeiculos[0]++] = v;
        Arquivos.salvarVeiculos(veiculos, totalVeiculos[0]);
        System.out.println("✔ Veículo cadastrado e salvo!");
    }

    static void cadastrarPeca(Scanner sc,
                               Peca[] pecas,
                               int[] totalPecas,
                               int MAX) {
        if (totalPecas[0] >= MAX) { System.out.println("Limite atingido!"); return; }

        Peca p = new Peca();
        System.out.print("Código da peça: ");
        p.codigo = sc.nextInt(); sc.nextLine();

        for (int i = 0; i < totalPecas[0]; i++) {
            if (pecas[i].codigo == p.codigo) {
                System.out.println("Erro: código já cadastrado!"); return;
            }
        }

        System.out.print("Descrição: ");
        p.descricao = sc.nextLine();
        System.out.print("Quantidade em estoque: ");
        p.quantidade = sc.nextInt();
        System.out.print("Preço unitário: R$ ");
        p.precoUnitario = sc.nextDouble(); sc.nextLine();

        pecas[totalPecas[0]++] = p;
        Arquivos.salvarPecas(pecas, totalPecas[0]);
        System.out.println("✔ Peça cadastrada e salva!");
    }

    static void reporEstoque(Scanner sc, Peca[] pecas, int[] totalPecas) {
        System.out.print("Código da peça para repor: ");
        int codigo = sc.nextInt(); sc.nextLine();

        int idx = -1;
        for (int i = 0; i < totalPecas[0]; i++) {
            if (pecas[i].codigo == codigo) { idx = i; break; }
        }

        if (idx == -1) {
            System.out.println("Erro: peça não encontrada!");
            return;
        }

        System.out.println("Peça encontrada: " + pecas[idx].descricao);
        System.out.println("Estoque atual  : " + pecas[idx].quantidade);
        System.out.print("Quantidade a adicionar: ");
        int qtd = sc.nextInt(); sc.nextLine();

        if (qtd <= 0) {
            System.out.println("Erro: quantidade deve ser maior que zero!");
            return;
        }

        pecas[idx].quantidade += qtd;
        Arquivos.salvarPecas(pecas, totalPecas[0]);

        System.out.println("✔ Estoque atualizado!");
        System.out.println("  Novo estoque de " + pecas[idx].descricao + ": " + pecas[idx].quantidade);
    }
}