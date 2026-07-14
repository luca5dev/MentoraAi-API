package app.config;

import org.slf4j.Logger;

public class MensagensLogger {

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
