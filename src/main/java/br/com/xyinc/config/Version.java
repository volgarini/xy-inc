package br.com.xyinc.config;

public class Version {
	private Version() {
	}

	/*
	 * ESSE PARAMETRO NÃO DEVE SER MODIFICADO MANUALMENTE, UTILIZE O SCRIPT
	 * 'changeversion.sh'
	 */
	public static final String VERSAO = "1.0.1";
	public static final String BUILDTIME = "10/07/2020 10:44";

	public static final String NOME_SISTEMA = "XY Inc";
	public static final String DESCRICAO_SISTEMA = "Serviço Gerenciamento GPS";

	public static final String VERSAO_BUILD = VERSAO + " {" + BUILDTIME + "}";
}
