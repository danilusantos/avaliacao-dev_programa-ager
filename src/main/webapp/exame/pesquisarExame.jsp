<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	prefix="s"
	uri="/struts-tags"%>
<div class="w-75">
	<s:form
		cssClass="w-100 d-flex align-items-center"
		action="/filtrarExames.action"
		method="get">
		<div class="input-group input-group-sm justify-content-end me-2">
			<div class="input-group-text">
				<label
					for="opcoesCombo"
					class="text-nowrap me-2"><s:text
						name="label.buscar.por"></s:text></label>
				<s:select
					id="opcoesCombo"
					cssClass="form-select form-select-sm"
					name="filtrar.opcoesCombo"
					list="listaOpcoesCombo"
					headerValue="Escolha..."
					listKey="%{codigo}"
					listValueKey="%{descricao}"
					value="filtrar.opcoesCombo.codigo" />
			</div>
		</div>
		<div class="input-group">
			<s:textfield
				cssClass="form-control"
				id="nome"
				name="filtrar.valorBusca"
				placeholder="Digite o valor para buscar" />
			<button
				class="btn btn-primary col-sm-2"
				type="submit">
				<i class="fa-solid fa-magnifying-glass"></i>
			</button>
		</div>
		<s:url
			action="todosExames"
			var="todos" />
		<a
			href="${todos}"
			class="link-danger small text-nowrap ms-2"> <i
			class="fa-solid fa-circle-xmark me-1"></i> <s:text
				name="label.exame.botao.limpar" />
		</a>
	</s:form>
</div>