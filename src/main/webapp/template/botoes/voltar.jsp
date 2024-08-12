<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	prefix="s"
	uri="/struts-tags"%>

<s:set var="todos" scope="page">${param.todos}</s:set>
<s:property value="#attr.param1"/>	

<a
	href="${todos}"
	class="btn btn-secondary"> <s:text
		name="label.botao.voltar" />
</a>