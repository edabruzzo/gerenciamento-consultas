package br.com.abruzzo.med.voll.exceptions;

import java.text.MessageFormat;

public class MedVollRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String codigoErro;

    public MedVollRuntimeException(IMensagem mensagemErro) {
        super(mensagemErro.getMensagem());
        this.codigoErro = mensagemErro.getCodigoCompleto();
    }

    public MedVollRuntimeException(IMensagem mensagemErro, Throwable excecao) {
        super(mensagemErro.getMensagem(), excecao);
        this.codigoErro = mensagemErro.getCodigoCompleto();
    }

    public MedVollRuntimeException(IMensagem mensagemErro, Throwable excecao, Object... parametros) {
        super(MessageFormat.format(mensagemErro.getMensagem(), parametros), excecao);
        this.codigoErro = mensagemErro.getCodigoCompleto();
    }

    public MedVollRuntimeException(IMensagem mensagemErro, Object... parametros) {
        this(mensagemErro, (Throwable)null, parametros);
    }

    public <T> MedVollRuntimeException(RespostaErro respostaRequisicao) {
        super(extrairMensagemErro(respostaRequisicao));
        this.codigoErro = null;
    }

    public <T> MedVollRuntimeException(RespostaErro respostaRequisicao, MensagemErroEnum mensagemErro) {
        super(mensagemErro.getMensagem() + extrairMensagemErro(respostaRequisicao));
        this.codigoErro = mensagemErro.getCodigoCompleto();
    }

    private static <T> String extrairMensagemErro(RespostaErro respostaRequisicao) {
        String msgErro = "";
        if (!respostaRequisicao.getMessages().isEmpty()) {
            respostaRequisicao.getMessages().forEach((mensagem) -> {
                msgErro.concat(mensagem + "\n");
            });
        }

        return msgErro;
    }

    public String getCodigoErro() {
        return this.codigoErro;
    }

    public String toString() {
        String var10000 = this.codigoErro;
        return var10000 + "::" + this.getMessage();
    }
}

