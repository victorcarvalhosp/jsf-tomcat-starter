package br.com.libercode.view.util;

import java.io.Serializable;
import java.util.Set;

public class MenuUtil implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Boolean apareceMenuCadastro;
	private Boolean apareceMenuFinanceiro;
	private Boolean apareceMenuGestao;
	private Boolean apareceMenuCondominio;
	private Boolean apareceMenuRelatorios;
	private Boolean apareceMenuGraficos;

	
	private Boolean apareceSubMenuCadastrosBasicos;
	private Boolean apareceSubMenuPermissoes;
	private Boolean apareceSubMenuBoletos;

	/**
	 * Futuras permiss�es 
	 * private Boolean apareceMenuDocumentos; 
	 * private Boolean apareceMenuRelat�rios;
	 * 
	 */

	private Set<String> recursos;

	public MenuUtil() {
		apareceMenuCadastro = null;
		apareceMenuFinanceiro = null;
		apareceMenuGestao = null;
		apareceMenuCondominio = null;

	}

	public boolean exibirMenuCadastro() {
//		if (apareceMenuCadastro == null) {
//			recursos = JSFUtil.getRecursos();
//			if (verificarSeUsuarioTemAcessoCadastro()) {
//				apareceMenuCadastro = true;
//			} else {
//				apareceMenuCadastro = false;
//
//			}
//		}
//		return apareceMenuCadastro;
		return true;
	}
	
	public boolean exibirSubMenuCadastrosBasicos() {
//		if (apareceSubMenuCadastrosBasicos == null) {
//			recursos = JSFUtil.getRecursos();
//			if (verificarSeUsuarioTemAcessoCadastrosBasicos()) {
//				apareceSubMenuCadastrosBasicos = true;
//			} else {
//				apareceSubMenuCadastrosBasicos = false;
//
//			}
//		}
//		return apareceSubMenuCadastrosBasicos;
		return true;
	}
	
	public boolean exibirSubMenuPermissoes() {
		if (apareceSubMenuPermissoes == null) {
			recursos = JSFUtil.getRecursos();
			if (verificarSeUsuarioTemAcessoPermissoes()) {
				apareceSubMenuPermissoes = true;
			} else {
				apareceSubMenuPermissoes = false;

			}
		}
		return apareceSubMenuPermissoes;
	}
	
	public boolean exibirSubMenuBoletos() {
		if (apareceSubMenuBoletos == null) {
			recursos = JSFUtil.getRecursos();
			if (verificarSeUsuarioTemAcessoBoletos()) {
				apareceSubMenuBoletos = true;
			} else {
				apareceSubMenuBoletos = false;
			}
		}
		return apareceSubMenuPermissoes;
	}
	
	public boolean exibirMenuRelatorios() {
//		if (apareceMenuRelatorios == null) {
//			recursos = JSFUtil.getRecursos();
//			if (verificarSeUsuarioTemAcessoRelatorios()) {
//				apareceMenuRelatorios = true;
//			} else {
//				apareceMenuRelatorios = false;
//
//			}
//		}
//		return apareceMenuRelatorios;
		return true;
	}
	
	public boolean exibirMenuGraficos() {
		if (apareceMenuGraficos == null) {
			recursos = JSFUtil.getRecursos();
			if (verificarSeUsuarioTemAcessoGraficos()) {
				apareceMenuGraficos = true;
			} else {
				apareceMenuGraficos = false;

			}
		}
		return apareceMenuGraficos;
	}
	
	private boolean verificarSeUsuarioTemAcessoBoletos() {
		return verificarSeUsuarioTemAcessoCadastrosBasicos()
				||verificarSeUsuarioTemAcessoPermissoes()
				|| recursos.contains("ges_gerar_boleto")
				|| recursos.contains("ges_controle_boleto");
	}
	
	private boolean verificarSeUsuarioTemAcessoRelatorios() {
		return recursos != null && (recursos.contains("rel_assembleias")
				|| recursos.contains("rel_queixas")
				|| recursos.contains("rel_fundo_reserva")
				|| recursos.contains("rel_usuarios")
				|| recursos.contains("rel_consumo_agua")
				|| recursos.contains("rel_consumo_gas")
				|| recursos.contains("rel_eventos")
				|| recursos.contains("rel_manutencao")
				|| recursos.contains("rel_patrimonio")
				|| recursos.contains("rel_patrimonio_balanco")
				|| recursos.contains("rel_empregados")
				|| recursos.contains("rel_agenda_area")
				|| recursos.contains("rel_despesas_previstas")
				|| recursos.contains("rel_despesas")
				|| recursos.contains("rel_receitas")
				|| recursos.contains("rel_balancete")
				|| recursos.contains("rel_demonstrativo_financeiro")
				|| recursos.contains("rel_balancete_resumo"));
	}
	
	private boolean verificarSeUsuarioTemAcessoGraficos() {
		return recursos.contains("gra_queixas")
				|| recursos.contains("gra_fundo_reserva")
				|| recursos.contains("gra_despesa_variavel")
				|| recursos.contains("gra_consumos")
				|| recursos.contains("gra_despesa_fixa");
	}

	private boolean verificarSeUsuarioTemAcessoCadastro() {
		return verificarSeUsuarioTemAcessoCadastrosBasicos()
				||verificarSeUsuarioTemAcessoPermissoes()
				|| recursos.contains("cad_condominio")
				|| recursos.contains("cad_apartamento")
				|| recursos.contains("cad_morador")
				|| recursos.contains("cad_mural")
				|| recursos.contains("cad_areas_comuns");
	}
	
	private boolean verificarSeUsuarioTemAcessoCadastrosBasicos() {
		return recursos.contains("cad_uf") || recursos.contains("cad_cidade")
				|| recursos.contains("cad_tipo_area_comum")
				|| recursos.contains("cad_tipo_queixa")
				|| recursos.contains("cad_tipo_forma_contato")
				|| recursos.contains("cad_tipo_de_contato")
				|| recursos.contains("cad_motivo_advertencia");
	}
	
	private boolean verificarSeUsuarioTemAcessoPermissoes() {
		return recursos != null && (recursos.contains("cad_recurso")
				|| recursos.contains("cad_grupo")
				|| recursos.contains("cad_usuario"));
	}

	public boolean exibirMenuFinanceiro() {
		return verificarSeUsuarioTemAcessoFinanceiro();
	}

	private boolean verificarSeUsuarioTemAcessoFinanceiro() {
		return recursos != null
				&& (
//				recursos.contains("fin_contas") ||
				recursos.contains("fin_receitas")
				|| recursos.contains("fin_despesas")
				|| recursos.contains("fin_somente_visualizacao")
//				|| recursos.contains("ges_consumo_agua_apartamento")
//				|| recursos.contains("ges_consumo_gas_apartamento")
//				|| recursos.contains("fin_novo_gerar_conta")
//				|| recursos.contains("fin_fechar_conta")
//				|| recursos.contains("fin_aprovar_contas")
//				|| recursos.contains("upload_balancete")
//				|| recursos.contains("upload_boleto")
		);
	}

	public boolean exibirMenuGestao() {
//		if (apareceMenuGestao == null) {
//			recursos = JSFUtil.getRecursos();
//
//			if (verificarSeUsuarioTemAcessoGestao()) {
//
//				apareceMenuGestao = true;
//
//			} else {
//				apareceMenuGestao = false;
//
//			}
//		}
//
//		return apareceMenuGestao;
		return true;
	}

	private boolean verificarSeUsuarioTemAcessoGestao() {
		return recursos != null && (recursos.contains("ges_assembleia")
				|| recursos.contains("ges_contatos_importantes")
				|| recursos.contains("ges_recado_geral")
				|| recursos.contains("ges_manutencao_condominio")
				|| recursos.contains("ges_empregados")
				|| recursos.contains("ges_gerar_conta"));
	}

	public boolean exibirMenuCondominio() {
		if (apareceMenuCondominio == null) {
			recursos = JSFUtil.getRecursos();

			if (verificarSeUsuarioTemAcessoCondominio()) {
				apareceMenuCondominio = true;

			} else {
				apareceMenuCondominio = false;
			}
		}
		return apareceMenuCondominio;
	}

	private boolean verificarSeUsuarioTemAcessoCondominio() {
		return recursos.contains("cond_agendar_area_comum")
				|| recursos.contains("cond_queixa")
				|| recursos.contains("cond_eventos")
				|| recursos.contains("cond_votacoes")
				|| recursos.contains("cond_atendimeto");
	}

	public Boolean getApareceMenuCadastro() {
		return apareceMenuCadastro;
	}

	public void setApareceMenuCadastro(Boolean apareceMenuCadastro) {
		this.apareceMenuCadastro = apareceMenuCadastro;
	}

	public Boolean getApareceMenuFinanceiro() {
		return apareceMenuFinanceiro;
	}

	public void setApareceMenuFinanceiro(Boolean apareceMenuFinanceiro) {
		this.apareceMenuFinanceiro = apareceMenuFinanceiro;
	}

	public Boolean getApareceMenuGestao() {
		return apareceMenuGestao;
	}

	public void setApareceMenuGestao(Boolean apareceMenuGestao) {
		this.apareceMenuGestao = apareceMenuGestao;
	}

	public Boolean getApareceMenuCondominio() {
		return apareceMenuCondominio;
	}

	public void setApareceMenuCondominio(Boolean apareceMenuCondominio) {
		this.apareceMenuCondominio = apareceMenuCondominio;
	}
	

	public Boolean getApareceSubMenuCadastrosBasicos() {
		return apareceSubMenuCadastrosBasicos;
	}

	public void setApareceSubMenuCadastrosBasicos(
			Boolean apareceSubMenuCadastrosBasicos) {
		this.apareceSubMenuCadastrosBasicos = apareceSubMenuCadastrosBasicos;
	}

	public Boolean getApareceSubMenuPermissoes() {
		return apareceSubMenuPermissoes;
	}

	public void setApareceSubMenuPermissoes(Boolean apareceSubMenuPermissoes) {
		this.apareceSubMenuPermissoes = apareceSubMenuPermissoes;
	}
	

	public Boolean getApareceSubMenuBoletos() {
		return apareceSubMenuBoletos;
	}

	public void setApareceSubMenuBoletos(Boolean apareceSubMenuBoletos) {
		this.apareceSubMenuBoletos = apareceSubMenuBoletos;
	}

	public Set<String> getRecursos() {
		return recursos;
	}

	public void setRecursos(Set<String> recursos) {
		this.recursos = recursos;
	}

	public Boolean getApareceMenuRelatorios() {
		return apareceMenuRelatorios;
	}

	public void setApareceMenuRelatorios(Boolean apareceMenuRelatorios) {
		this.apareceMenuRelatorios = apareceMenuRelatorios;
	}

	public Boolean getApareceMenuGraficos() {
		return apareceMenuGraficos;
	}

	public void setApareceMenuGraficos(Boolean apareceMenuGraficos) {
		this.apareceMenuGraficos = apareceMenuGraficos;
	}
	
}
