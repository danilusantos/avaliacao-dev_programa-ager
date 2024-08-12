<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table class="table table-bordered">
    <thead>
        <tr>
            <th>ID</th>
            <th>Funcionário</th>
            <th>Exame</th>
            <th>Data de Agendamento</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="agendamento" items="${agendamentosFiltrados}">
            <tr>
                <td>${agendamento.rowid}</td>
                <td>${agendamento.funcionario.nome}</td>
                <td>${agendamento.exame.nome}</td>
                <td>${agendamento.dataAgendamento}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<!-- Botão para baixar o Excel -->
<form action="gerarRelatorioExcel.action" method="post">
    <input type="hidden" name="dataInicio" value="${dataInicio}">
    <input type="hidden" name="dataFim" value="${dataFim}">
    <button type="submit" class="btn btn-success">Baixar Excel</button>
</form>

</body>
</html>