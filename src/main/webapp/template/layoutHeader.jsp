<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	prefix="s"
	uri="/struts-tags"%>
<s:set var="paginaTitulo" scope="page">${param.paginaTitulo}</s:set>
<s:property value="#attr.param1"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Avaliação - ${paginaTitulo}</title>
<link
	rel="stylesheet"
	href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css" />
<link href="<s:url value='/resources/css/main.css'/>" rel="stylesheet" type="text/css"/>
<s:head/>
</head>
<body class="bg-secondary">
	<%@ include file="/template/navbar.jsp"%>
	