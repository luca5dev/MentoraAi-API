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
    public static final String NIVEL_SENIORIDADE_INVALIDO = "Nível de senioridade inválido!";
    public static final String SKILL_INVALIDA = "Skill inválida!";
    public static final String VALOR_OBRIGATORIO_NAO_INFORMADO = "Valor obrigatório não informado!";
    public static final String CARGA_HORARIA_EXCEDIDA = "A soma das horas dos mentorados ultrapassa a capacidade mensal do mentor.";
    public static final String DURACAO_TRILHA_INVALIDA = "A duração da trilha deve ser maior que zero.";
    public static final String COMPATIBILIDADE_SKILLS_INVALIDA = "Compatibilidade de skills menor que 70%";
    public static final String SENIORIDADE_MENTOR_INVALIDA = "O mentor deve ter senioridade superior a todos os mentorados.";
    public static final String MAXIMO_MENTORADOS_ATINGIDO = "Máximo de mentorados atingido para este mentor.";
    public static final String MENTOR_NAO_ENCONTRADO = "Mentor não encontrado: ";
    public static final String MENTORADO_NAO_ENCONTRADO = "Um ou mais mentorados informados não foram encontrados.";
    public static final String MENTORADO_OBRIGATORIO_TRILHA = "É necessário informar ao menos um mentorado para a trilha.";
    public static final String MENTOR_VINCULADO_TRILHA = "Não é possível excluir mentor vinculado a uma trilha.";
    public static final String HORAS_MENTOR_OBRIGATORIAS = "Horas dedicadas do mentor devem ser informadas.";
    public static final String HORAS_MENTOR_INVALIDAS = "Horas dedicadas do mentor devem ser maiores que zero.";
    public static final String HORAS_MENTOR_EXCEDIDAS = "Horas dedicadas do mentor não podem ultrapassar 20h.";
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
