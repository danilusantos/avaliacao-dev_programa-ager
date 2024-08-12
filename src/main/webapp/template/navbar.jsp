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
<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm mb-3">
	<div class="container">
		<a
			class="navbar-brand"
			href="#">
			<img src='<s:url value="/resources/img/logo-soc.png" />' alt="SOC" class="img-fluid" id="logo" />	
		</a>
		<button
			class="navbar-toggler"
			type="button"
			data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent"
			aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div
			class="collapse navbar-collapse"
			id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item"><s:url
						action="todosExames"
						var="exames" /> <a
					href="${exames}"
					class="nav-link"> <s:text name="label.template.navbar.exames" />
				</a></li>
				<li class="nav-item"><s:url
						action="todosFuncionarios"
						var="funcionarios" /> <a
					href="${funcionarios}"
					class="nav-link"> <s:text
							name="label.template.navbar.funcionarios" />
				</a></li>
				<li class="nav-item"><s:url
						action="todosAgendamentos"
						var="agendamentos" /> <a
					href="${agendamentos}"
					class="nav-link"> <s:text
							name="label.template.navbar.agendamentos" />
				</a></li>
				<li class="nav-item"><s:url
						action="gerarRelatorios"
						var="relatorios" /> <a
					href="${relatorios}"
					class="nav-link"> <s:text
							name="label.template.navbar.relatorios" />
				</a></li>
			</ul>
		</div>
	</div>
</nav>