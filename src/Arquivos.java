import java.io.*;

// =============================================
// MÓDULO: Arquivos.java
// Responsabilidade: Leitura e gravação dos arquivos CSV
// =============================================

public class Arquivos {

    static final String ARQ_MECANICOS = "mecanicos.csv";
    static final String ARQ_VEICULOS  = "veiculos.csv";
    static final String ARQ_PECAS     = "pecas.csv";
    static final String ARQ_ORDENS    = "ordens.csv";

    // ---- SALVAR ----

    static void salvarMecanicos(Mecanico[] mecanicos, int total) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(ARQ_MECANICOS));
            for (int i = 0; i < total; i++) {
                pw.println(mecanicos[i].codigo + ";" +
                           mecanicos[i].nome + ";" +
                           mecanicos[i].especialidade);
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar mecânicos: " + e.getMessage());
        }
    }

    static void salvarVeiculos(Veiculo[] veiculos, int total) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(ARQ_VEICULOS));
            for (int i = 0; i < total; i++) {
                pw.println(veiculos[i].placa + ";" +
                           veiculos[i].nomeDono + ";" +
                           veiculos[i].modelo);
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar veículos: " + e.getMessage());
        }
    }

    static void salvarPecas(Peca[] pecas, int total) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(ARQ_PECAS));
            for (int i = 0; i < total; i++) {
                pw.println(pecas[i].codigo + ";" +
                           pecas[i].descricao + ";" +
                           pecas[i].quantidade + ";" +
                           pecas[i].precoUnitario);
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar peças: " + e.getMessage());
        }
    }

    static void salvarOrdens(OrdemServico[] ordens, int total) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(ARQ_ORDENS));
            for (int i = 0; i < total; i++) {
                pw.println(ordens[i].numero + ";" +
                           ordens[i].placaVeiculo + ";" +
                           ordens[i].codigoMecanico + ";" +
                           ordens[i].codigoPeca + ";" +
                           ordens[i].quantidadePeca + ";" +
                           ordens[i].valorMaoDeObra);
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar ordens: " + e.getMessage());
        }
    }

    // ---- CARREGAR ----

    static int carregarMecanicos(Mecanico[] mecanicos, int MAX) {
        int total = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(ARQ_MECANICOS));
            String linha;
            while ((linha = br.readLine()) != null && total < MAX) {
                String[] p = linha.split(";");
                if (p.length < 3) continue;
                Mecanico m = new Mecanico();
                m.codigo        = Integer.parseInt(p[0]);
                m.nome          = p[1];
                m.especialidade = p[2];
                mecanicos[total++] = m;
            }
            br.close();
        } catch (FileNotFoundException e) {
            // Primeira execução, arquivo ainda não existe
        } catch (IOException e) {
            System.out.println("Erro ao carregar mecânicos: " + e.getMessage());
        }
        return total;
    }

    static int carregarVeiculos(Veiculo[] veiculos, int MAX) {
        int total = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(ARQ_VEICULOS));
            String linha;
            while ((linha = br.readLine()) != null && total < MAX) {
                String[] p = linha.split(";");
                if (p.length < 3) continue;
                Veiculo v = new Veiculo();
                v.placa    = p[0];
                v.nomeDono = p[1];
                v.modelo   = p[2];
                veiculos[total++] = v;
            }
            br.close();
        } catch (FileNotFoundException e) {
            // Primeira execução
        } catch (IOException e) {
            System.out.println("Erro ao carregar veículos: " + e.getMessage());
        }
        return total;
    }

    static int carregarPecas(Peca[] pecas, int MAX) {
        int total = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(ARQ_PECAS));
            String linha;
            while ((linha = br.readLine()) != null && total < MAX) {
                String[] p = linha.split(";");
                if (p.length < 4) continue;
                Peca pc = new Peca();
                pc.codigo        = Integer.parseInt(p[0]);
                pc.descricao     = p[1];
                pc.quantidade    = Integer.parseInt(p[2]);
                pc.precoUnitario = Double.parseDouble(p[3]);
                pecas[total++] = pc;
            }
            br.close();
        } catch (FileNotFoundException e) {
            // Primeira execução
        } catch (IOException e) {
            System.out.println("Erro ao carregar peças: " + e.getMessage());
        }
        return total;
    }

    static int carregarOrdens(OrdemServico[] ordens, int MAX) {
        int total = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(ARQ_ORDENS));
            String linha;
            while ((linha = br.readLine()) != null && total < MAX) {
                String[] p = linha.split(";");
                if (p.length < 6) continue;
                OrdemServico os = new OrdemServico();
                os.numero         = Integer.parseInt(p[0]);
                os.placaVeiculo   = p[1];
                os.codigoMecanico = Integer.parseInt(p[2]);
                os.codigoPeca     = Integer.parseInt(p[3]);
                os.quantidadePeca = Integer.parseInt(p[4]);
                os.valorMaoDeObra = Double.parseDouble(p[5]);
                ordens[total++] = os;
            }
            br.close();
        } catch (FileNotFoundException e) {
            // Primeira execução
        } catch (IOException e) {
            System.out.println("Erro ao carregar ordens: " + e.getMessage());
        }
        return total;
    }
}