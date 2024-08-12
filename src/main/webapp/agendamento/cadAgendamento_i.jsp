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

<s:if test="agendamentoVo.rowid == '' || agendamentoVo.rowid == null">
    <s:set var="paginaTitulo">
        <s:text name="label.agendamento.card.titulo.novo" />
    </s:set>
</s:if>
<s:else>
    <s:set var="paginaTitulo">
        <s:text name="label.agendamento.card.titulo.editar" />
    </s:set>
</s:else>

<s:include value="/template/layoutHeader.jsp">
    <s:param name="paginaTitulo">
        ${paginaTitulo}
    </s:param>
</s:include>

<div class="container">
    <c:set var="isUpdate" value="${not empty agendamentoVo.rowid}" />
    <s:form action="/novoAgendamentos.action">
        <div class="card mt-5">
            <div class="card-header">
                <div class="row">
                    <div class="col-sm-5">
                        <s:include value="/template/botoes/voltar.jsp">
                           <s:param name="todos">todosAgendamentos.action</s:param>
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
                        <label for="id" class="text-center"><s:text name="label.agendamento.form.rowid" /></label>
                        <s:textfield
                            cssClass="form-control"
                            id="id"
                            name="agendamentoVo.rowid"
                            readonly="true" />
                    </div>
                    
                    <div class="form-group col-12 col-md-8">
                        <label for="funcionario" class="text-center"><s:text name="label.agendamento.form.funcionario" /></label>
                     	<s:select 
	                    	cssClass="form-select"
					        id="funcionarioSelect" 
					        name="agendamentoVo.funcionario.rowid"
					        list="funcionarios" 
					        listKey="rowid" 
					        listValue="nome"
					        headerKey="" 
					        headerValue="Selecione um Funcionário"
					    />
						<s:fielderror fieldName="agendamentoVo.funcionario.nm_funcionario" />			
                    </div>

                    <div class="form-group col-12 col-md-8">
                        <label for="exame" class="text-center"><s:text name="label.agendamento.form.exame" /></label>

                        <s:select 
	                    	cssClass="form-select"
					        id="exameSelect" 
					        name="agendamentoVo.exame.rowid"
					        list="exames" 
					        listKey="rowid" 
					        listValue="nome"
					        headerKey="" 
					        headerValue="Selecione um Exame"
					    />
                        <s:fielderror fieldName="agendamentoVo.exame.nm_exame" />
                    </div>

                    <div class="form-group col-12 col-md-4">
                        <label for="dataAgendamento" class="text-center"><s:text name="label.agendamento.form.dataAgendamento" /></label>
                        <s:textfield
                            cssClass="form-control"
                            id="dataAgendamento"
                            name="agendamentoVo.dataAgendamento"
                            type="date" />
                        <s:fielderror fieldName="agendamentoVo.dataAgendamento" />
                    </div>
                </div>
            </div>
            <div class="card-footer">
                <div class="form-row">
                    <button
                        type="submit"
                        class="btn btn-primary btn-sm col-sm-4 offset-sm-1">
                        <s:text name="label.agendamento.form.botao.salvar" />
                    </button>
                    <button
                        type="reset"
                        class="btn btn-secondary btn-sm col-sm-4 offset-sm-2">
                        <s:text name="label.agendamento.form.botao.limpar" />
                    </button>
                </div>
            </div>
        </div>
    </s:form>
</div>
<jsp:directive.include file="/template/layoutFooter.jsp" />
