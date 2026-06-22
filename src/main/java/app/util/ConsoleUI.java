package app.util;

import app.model.enums.Skill;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ConsoleUI {

    private static final int LARGURA = 60;
    private static final NumberFormat MOEDA =
            new DecimalFormat("'R$'#,##0.00", new DecimalFormatSymbols(Locale.forLanguageTag("pt-BR")));

    private ConsoleUI() {
        throw new UnsupportedOperationException("Classe utilitária não pode ser instânciada");
    }

    public static String linha() {
        return "=".repeat(LARGURA);
    }

    public static void cabecalho(String titulo) {
        System.out.println("\n" + linha());
        int espacos = (LARGURA - titulo.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, espacos)) + titulo);
        System.out.println(linha());
    }

    public static void separador() {
        System.out.println("-".repeat(LARGURA));
    }

    public static String moeda(double valor) {
        return MOEDA.format(valor);
    }

    public static String horas(double valor) {
        if (valor == Math.floor(valor)) {
            return ((long) valor) + "h";
        }
        return String.valueOf(valor).replace(".", ",") + "h";
    }

    public static String sucesso(String mensagem) {
        return "[OK] " +  mensagem;
    }

    public static String erro(String mensagem) {
        return "[ERRO] " + mensagem;
    }

    public static String formataListaSkills(List<Skill> skills) {
        if (skills == null || skills.isEmpty()) {
            return "Nenhuma.";
        }
        return skills.stream()
                .map(Skill::name)
                .collect(java.util.stream.Collectors.joining(", "));
    }
}
