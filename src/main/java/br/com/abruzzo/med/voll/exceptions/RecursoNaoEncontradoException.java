package br.com.abruzzo.med.voll.exceptions;

public class RecursoNaoEncontradoException extends MedVollRuntimeException {
    private static final long serialVersionUID = 1L;

    public RecursoNaoEncontradoException(String nomeRecurso) {
        this(MensagemErroEnum.ERRO_RECURSO_NAO_ENCONTRADO, nomeRecurso);
    }

    public RecursoNaoEncontradoException(IMensagem mensagemErro, Object... parametros) {
        super(mensagemErro, parametros);
    }

    public RecursoNaoEncontradoException(RespostaErro respostaRequisicao) {
        super(respostaRequisicao);
    }
}
