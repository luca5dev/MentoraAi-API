package app.config;

import org.slf4j.Logger;

public class MensagensLogger {

    public static final String TITULO_DEMONSTRACAO = "AC6: DEMONSTRAÇÃO AUTOMÁTICA";
    public static final String INICIO_DEMONSTRACAO = "========== AC6: DEMONSTRAÇÃO AUTOMÁTICA ==========";
    public static final String FIM_DEMONSTRACAO = "========== FIM DA DEMONSTRAÇÃO AUTOMÁTICA ==========";
    public static final String MENSAGEM_SUCESSO = "Demonstração executada com sucesso";

    public static final String NOME_TRILHA_SOBRECARREGADA = "Trilha Sobrecarregada e Editada";
    public static final String NOME_TRILHA_SKILLS = "Trilha Skills";
    public static final String NOME_TRILHA_NIVEL = "Trilha Nível";

    public static final String CENARIO_1_CARGA_HORARIA = ">> Cenário 1: Carga horária excedida (limite 20h/mês)";
    public static final String CENARIO_2_SKILLS = ">> Cenário 2: Skills incompatíveis (mentor não tem 70% das skills desejadas)";
    public static final String CENARIO_3_NIVEL = ">> Cenário 3: Nível desproporcional (PLENO, mentorando PLENO)";
    public static final String CENARIO_4_AJUSTE = ">> Cenário 4: Ajuste dos dados da trilha inválida e persistência";
    public static final String TRILHA_PERSISTIDA = "Trilha persistida com sucesso!";
    public static final String RESULTADO_TRILHA_PERSISTIDA = "SUCESSO: Trilha persistida com sucesso";
    public static final String RESULTADO_ERRO = "ERRO";
    public static final String RESULTADO_EXCECAO_CAPTURADA = "EXCEÇÃO CAPTURADA";
    public static final String RESULTADO_INESPERADO = "INESPERADO: Trilha passou nas validações";
    public static final String TITULO_TRILHA_VALIDA = "Trilha Válida e Persistida";

    public static final String ERRO_PROCESSAMENTO_ENTIDADE = "Erro de processamento da entidade";
    public static final String ERRO_REGRA_NEGOCIO_TRATADO = "Erro de regra de negocio tratado. tipo={}";
    public static final String FALHA_TRILHA_AJUSTADA = "Falha ao validar ou persistir trilha ajustada da demonstracao. nome={}";
    public static final String FALHA_LIMPEZA_TRILHA_RETRY = "Falha ao limpar trilha da demonstracao. Tentando novamente. id={}, nome={}";
    public static final String INTERRUPCAO_LIMPEZA_TRILHA = "Thread interrompida ao tentar limpar trilha da demonstracao. id={}, nome={}";
    public static final String FALHA_SEGUNDA_TENTATIVA_LIMPEZA = "Falha na segunda tentativa de limpar trilha da demonstracao. id={}, nome={}";
    public static final String ERRO_LIMPEZA_DEMONSTRACAO = "Erro ao limpar dados anteriores da demonstracao.";

    private MensagensLogger() {
    }

    public static void info(Logger logger, String mensagem) {
        logger.info(mensagem);
    }

    public static void warn(Logger logger, String mensagem, Object... argumentos) {
        logger.warn(mensagem, argumentos);
    }
}
