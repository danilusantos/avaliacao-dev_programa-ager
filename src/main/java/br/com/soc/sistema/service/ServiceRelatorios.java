package br.com.soc.sistema.service;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import br.com.soc.sistema.business.AgendamentoBusiness;
import br.com.soc.sistema.infra.agendamento.RelatorioExcelAgendamentos;
import br.com.soc.sistema.vo.agendamento.AgendamentoVo;

public class ServiceRelatorios {
	private AgendamentoBusiness agendamentoBusiness = new AgendamentoBusiness();
	private List<AgendamentoVo> agendamentos = new ArrayList<>();

	public void gerarExcelRelatorios(String dataInicio, String dataFim) {
		try {
			agendamentos = agendamentoBusiness
					.filtrarAgendamentosPorDatas(dataInicio, dataFim);

			ByteArrayInputStream fileInputStream = RelatorioExcelAgendamentos
					.gerarExcelRelatorios(agendamentos);

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + pegarNomeArquivo());

			ServletOutputStream outputStream = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int bytesRead;

			while ((bytesRead = fileInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			outputStream.flush();
			outputStream.close();
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String pegarNomeArquivo() {
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd_HH-mm-ss");
		String dataFormatada = agora.format(formatter);
		
		return "agendamentos-" + dataFormatada + ".xls";
	}
}
