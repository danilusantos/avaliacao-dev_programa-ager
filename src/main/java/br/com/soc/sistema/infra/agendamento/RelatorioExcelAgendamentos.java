package br.com.soc.sistema.infra.agendamento;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.*;

import br.com.soc.sistema.vo.agendamento.AgendamentoVo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RelatorioExcelAgendamentos {

	public static ByteArrayInputStream gerarExcelRelatorios(
			List<AgendamentoVo> agendamentos) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Agendamentos");

		sheet.setDefaultColumnWidth(15);
		sheet.setDefaultRowHeight((short) 400);

		Row headerRow = sheet.createRow(0);
		String[] headers = {"ID", "Funcionário", "Exame",
				"Data de Agendamento"};
		for (int i = 0; i < headers.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headers[i]);
		}

		int rownum = 1;
		for (AgendamentoVo agendamento : agendamentos) {
			Row row = sheet.createRow(rownum++);
			row.createCell(0).setCellValue(agendamento.getRowid());
			row.createCell(1)
					.setCellValue(agendamento.getFuncionario().getNome());
			row.createCell(2).setCellValue(agendamento.getExame().getNome());
			String dataAgendamento = LocalDate
					.parse(agendamento.getDataAgendamento())
					.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			row.createCell(3).setCellValue(dataAgendamento.toString());
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		workbook.write(out);
		return new ByteArrayInputStream(out.toByteArray());
	}
}
