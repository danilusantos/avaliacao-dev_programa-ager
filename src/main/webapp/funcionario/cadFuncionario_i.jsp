<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	prefix="s"
	uri="/struts-tags"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>

<s:if test="funcionarioVo.rowid == '' || funcionarioVo.rowid == null">
	<s:set var="paginaTitulo">
		<s:text name="label.funcionario.card.titulo.novo" />
	</s:set>
</s:if>
<s:else>
	<s:set var="paginaTitulo">
		<s:text name="label.funcionario.card.titulo.editar" />
	</s:set>
</s:else>

<s:if test="funcionarioVo.rowid == '' || funcionarioVo.rowid == null">
	<s:include value="/template/layoutHeader.jsp">
		<s:param name="paginaTitulo">
			${paginaTitulo}
		</s:param>
	</s:include>
</s:if>
<s:else>
	<s:include value="/template/layoutHeader.jsp">
		<s:param name="paginaTitulo">
			${paginaTitulo}
		</s:param>
	</s:include>
</s:else>

<div class="container">
	<c:set
		var="isUpdate"
		value="${not empty funcionarioVo.rowid}" />
	<s:form action="/novoFuncionarios.action">
		<div class="card mt-5">
			<div class="card-header">
				<div class="row">
					<div class="col-sm-5">
						<s:include value="/template/botoes/voltar.jsp">
						   <s:param name="todos">todosFuncionarios.action</s:param>
						</s:include>
					</div>
					<div class="col-sm">
						<h5 class="card-title">
							${paginaTitulo}
						</h5>
					</div>
				</div>
			</div>
			<div class="card-body">
				<div class="row align-items-center">
					<div class="form-group col-12 col-md-4">
						<label
							for="id"
							class="text-center"><s:text name="label.funcionario.form.rowid" /></label>
						<s:textfield
							cssClass="form-control"
							id="id"
							name="funcionarioVo.rowid"
							readonly="true" />
					</div>

					<div class="form-group col-12 col-md-8">
						<label
							for="nome"
							class="text-center"><s:text name="label.funcionario.form.nome" /></label>
						<s:textfield
							cssClass="form-control"
							id="nome"
							name="funcionarioVo.nome" />
						<s:fielderror fieldName="funcionarioVo.nome" />
					</div>
				</div>
			</div>
			<div class="card-footer">
				<div class="form-row">
					<button
						type="submit"
						class="btn btn-primary btn-sm col-sm-4 offset-sm-1">
						<s:text name="label.funcionario.form.botao.salvar" />
					</button>
					<button
						type="reset"
						class="btn btn-secondary btn-sm col-sm-4 offset-sm-2">
						<s:text name="label.funcionario.form.botao.limpar" />
					</button>
				</div>
			</div>
		</div>
	</s:form>
</div>
<jsp:directive.include file="/template/layoutFooter.jsp" />