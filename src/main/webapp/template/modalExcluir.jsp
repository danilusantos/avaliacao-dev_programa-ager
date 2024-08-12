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
<div
	class="modal fade"
	id="confirmarExclusao"
	data-bs-backdrop="static"
	data-bs-keyboard="false"
	tabindex="-1"
	aria-labelledby="staticBackdropLabel"
	aria-hidden="true">
	<div
		class="modal-dialog"
		role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">
					<s:text name="label.modal.excluir.titulo" />
				</h5>
				<button
					type="button"
					class="btn-close"
					data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<span><s:text name="label.modal.excluir.corpo" /></span>
			</div>
			<div class="modal-footer">
				<a
					class="btn btn-secondary"
					data-bs-dismiss="modal"
					aria-label="Close"> <s:text name="label.nao" />
				</a> <a
					id="excluir"
					class="btn btn-primary"
					style="width: 75px"> <s:text name="label.sim" />
				</a>
			</div>
		</div>
	</div>
</div>
