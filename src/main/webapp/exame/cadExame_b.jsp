<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	prefix="s"
	uri="/struts-tags"%>

<s:include value="/template/layoutHeader.jsp">
	<s:param name="paginaTitulo">
		<s:text name="label.exame.titulo.pagina.consulta" />
	</s:param>
</s:include>

<s:include value="toolbar.jsp" />

<div class="container">
	<div class="row">
		<div class="col-12">
			<div class="card p-0">
				<div class="card-header">
					<div class="card-title d-flex justify-content-between">
						<s:include value="/template/botoes/novo.jsp">
							<s:param name="novo">novoExames.action</s:param>
						</s:include>
						<s:include value="pesquisarExame.jsp"></s:include>
					</div>
				</div>
				<div class="card-body p-0">
					<table
						class="table table-hover table-light table-striped align-middle table-bordered table-sm table-condensed shadow-sm mb-0">
						<thead>
							<tr>
								<th class="text-end"><s:text name="label.id" /></th>
								<th><s:text name="label.nome" /></th>
								<th
									colspan="2"
									class="text-center mt-5"><s:text name="label.acao" /></th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="exames">
								<tr>
									<td class="td-acao text-end">${rowid}</td>
									<td>${nome}</td>
									<td class="text-center td-acao text-nowrap"><s:url
											action="editarExames"
											var="editar">
											<s:param
												name="exameVo.rowid"
												value="rowid"></s:param>
										</s:url> <a
										href="${editar}"
										class="btn btn-warning btn-sm text-white">
										<i class="fa-solid fa-pen-to-square"></i> <s:text
												name="label.editar" />
									</a></td>
									<td class="text-center td-acao text-nowrap">
										<a href="#"
										class="btn btn-danger btn-sm"
										onclick="abrirModalDeletarExame('${rowid}')"> 
											<i class="fa-solid fa-trash"></i> <s:text name="label.excluir" />
										</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<s:include value="/template/modalExcluir.jsp"></s:include>

<jsp:directive.include file="/template/layoutFooter.jsp" />