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
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/fmt"
	prefix="fmt"%>

<s:set var="paginaTitulo">
	<s:text name="label.relatorio.title" />
</s:set>

<s:include value="/template/layoutHeader.jsp">
	<s:param name="paginaTitulo">
        ${paginaTitulo}
    </s:param>
</s:include>

<div class="container">
	<s:form action="/gerarRelatorios.action">
		<div class="card mt-5">
			<div class="card-header">
				<h5 class="card-title">
					<s:text name="label.relatorio.title" />
				</h5>
			</div>
			<div class="card-body">
				<div class="row align-items-center">
					<div class="form-group col-12 col-md-6">
						<label
							for="dataInicio"
							class="text-center"> <s:text
								name="label.relatorio.form.dataInicio" />
						</label>
						<s:textfield
							cssClass="form-control"
							id="dataInicio"
							name="dataInicio"
							type="date" />
						<s:fielderror fieldName="dataInicio" />
					</div>

					<div class="form-group col-12 col-md-6">
						<label
							for="dataFim"
							class="text-center"> <s:text
								name="label.relatorio.form.dataFim" />
						</label>
						<s:textfield
							cssClass="form-control"
							id="dataFim"
							name="dataFim"
							type="date" />
						<s:fielderror fieldName="dataFim" />
					</div>
				</div>
			</div>
			<div class="card-footer">
				<div class="form-row mx-auto d-flex justify-content-center">
					<button
						type="submit"
						class="btn btn-primary btn-sm col-sm-4 me-3">
						<s:text name="label.relatorio.form.botao.gerar" />
					</button>
					
					<a href="gerarRelatorios.action"
						class="btn btn-secondary btn-sm col-sm-4">
						<s:text name="label.relatorio.form.botao.limpar" />
					</a>
				</div>
			</div>
		</div>
	</s:form>

	<s:if test="agendamentos != null || !agendamentos.isEmpty()">
		<div class="row mt-3">
			<div class="col-12">
				<div class="card p-0">
					<div class="card-header">
						<div
							class="card-title d-flex justify-content-between align-items-center">
							<h5>Relatório</h5>
							<s:form
								action="/baixarExcelRelatorios.action"
								method="post"
								cssClass="mb-0">
								<input
									type="hidden"
									name="dataInicio"
									value="${dataInicio}">
								<input
									type="hidden"
									name="dataFim"
									value="${dataFim}">
								<button
									type="submit"
									class="btn btn-success">Baixar Excel</button>
							</s:form>
						</div>
					</div>
					<div class="card-body p-0">
						<table
							class="table table-hover table-light table-striped align-middle table-bordered table-sm table-condensed shadow-sm mb-0">
							<thead>
								<tr>
									<th class="text-end">ID</th>
									<th>Funcionário</th>
									<th>Exame</th>
									<th>Data de Agendamento</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="agendamentos">
									<tr>
										<td class="text-end">${rowid}</td>
										<td>${funcionario.nome}</td>
										<td>${exame.nome}</td>
										<td>
											<fmt:parseDate
												value="${dataAgendamento}"
												var="dataBr"
												pattern="yyyy-MM-dd" />
											<fmt:formatDate
												value="${dataBr}"
												pattern="dd/MM/yyyy" />
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</s:if>
</div>
<jsp:directive.include file="/template/layoutFooter.jsp" />
