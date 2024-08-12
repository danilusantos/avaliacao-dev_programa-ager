<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.full.min.js"></script>
<script>
	function abrirModalDeletarExame(rowid) {
		let excluirUrl = '<s:url action="deletarExames"/>';
		excluirUrl += "?exameVo.rowid=" + rowid;
		$("#excluir").attr("href", excluirUrl);
		$("#confirmarExclusao").modal("show");
	}

	function abrirModalDeletarFuncionario(rowid) {
		let excluirUrl = '<s:url action="deletarFuncionarios"/>';
		excluirUrl += "?funcionarioVo.rowid=" + rowid;
		$("#excluir").attr("href", excluirUrl);
		$("#confirmarExclusao").modal("show");
	}
	
	function abrirModalDeletarAgendamento(rowid) {
		let excluirUrl = '<s:url action="deletarAgendamentos"/>';
		excluirUrl += "?agendamentoVo.rowid=" + rowid;
		$("#excluir").attr("href", excluirUrl);
		$("#confirmarExclusao").modal("show");
	}

	$(document).ready(function() {
		
		
		function setSearchFieldRestrictions() {
            let opcoesCombo = $('#opcoesCombo').val();
            let campoPesquisar = $('#nome');
            if (opcoesCombo === '2') {
                // Permitir apenas números
                campoPesquisar.attr('pattern', '\\d*');
                campoPesquisar.attr('title', 'Apenas números são permitidos.');
                campoPesquisar.on('input', function() {
                    this.value = this.value.replace(/\D/g, '');
                });
            } else {
                // Permitir qualquer caractere
                campoPesquisar.removeAttr('pattern');
                campoPesquisar.removeAttr('title');
                campoPesquisar.off('input');
            }
        }
		
		$('#opcoesCombo').on('change', function() {
			$('#nome').val('');
            setSearchFieldRestrictions();
        });
		
		$('#exameSelect, #funcionarioSelect').select2({
		    theme: 'bootstrap-5'
		});

        setSearchFieldRestrictions();
	});
</script>
</body>
</html>