package br.com.abruzzo.med.voll.exceptions;

public enum MensagemErroEnum implements IMensagem {
    ERRO_DESCONHECIDO("Ocorreu um erro desconhecido no processamento da requisição."),
    ERRO_AUTENTICACAO_USUARIO("Erro na autenticação: {0}"),
    PARAMETRO_NAO_ENCONTRADO("Parâmetro não encontrado: {0}."),
    ERRO_ORDENACAO_INVALIDA("É necessário configurar a ordenação para a consulta paginada."),
    ERRO_RECURSO_NAO_ENCONTRADO("{0} não encontrado."),
    ENTIDADE_NAO_ENCONTRADA("Informação não encontrada no banco de dados."),
    ENTIDADE_JA_CADASTRADA("Não é possível gravar informações duplicadas no sistema."),
    ERRO_SALVAR_ENTIDADE("Erro ao salvar informações."),
    ERRO_ALTERAR_REGISTRO_NAO_RELACIONADO("Não é possível alterar informação distinta do identificador da requisição."),
    ERRO_NA_EXCLUSAO_ENTIDADE("Erro ao excluir informações: {0}"),
    ERRO_OPERACAO_NAO_SUPORTADA("Essa operação não é suportada."),
    USUARIO_NAO_AUTENTICADO("Acesso negado, usuário não autenticado."),
    USUARIO_AUTENTICADO_SEM_AUTORIZACAO("Acesso não possui autorização para realizar a operação.");

    private final String mensagem;

    private MensagemErroEnum(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getModulo() {
        return "CORE";
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public int getCodigo() {
        return this.ordinal() + 1;
    }
}
