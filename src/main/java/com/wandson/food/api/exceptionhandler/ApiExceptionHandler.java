package com.wandson.food.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.wandson.food.api.exceptionhandler.Problem.Field;
import com.wandson.food.domain.exception.EntidadeEmUsoException;
import com.wandson.food.domain.exception.EntidadeNaoEncontradaException;
import com.wandson.food.domain.exception.NegocioException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. "
			+ "Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.";

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex,
			WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		var problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = ex.getMessage();
		var problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<Object> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		var problemType = ProblemType.ENTIDADE_EM_USO;
		String detail = ex.getMessage();
		var problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocioException(NegocioException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		var problemType = ProblemType.ERRO_NEGOCIO;
		String detail = ex.getMessage();
		var problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		var problemType = ProblemType.ERRO_DE_SISTEMA;

		log.error(ex.getMessage(), ex);

		var problem = createProblemBuilder(status, problemType, MSG_ERRO_GENERICA_USUARIO_FINAL)
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (Objects.isNull(body)) {
			body = Problem.builder().title(status.getReasonPhrase()).status(status.value())
					.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL).timestamp(LocalDateTime.now()).build();
		} else if (body instanceof String) {
			body = Problem.builder().title((String) body).status(status.value())
					.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL).timestamp(LocalDateTime.now()).build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		}
		if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
		}

		var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		var detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
		var problem = createProblemBuilder(status, problemType, detail).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
		}

		return super.handleTypeMismatch(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		var problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		var detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", ex.getRequestURL());
		var problem = createProblemBuilder(status, problemType, detail).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		var problemType = ProblemType.DADOS_INVALIDOS;
		var detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
		var bindingResult = ex.getBindingResult();
		List<Field> problemFields = bindingResult.getFieldErrors().stream().map(fieldError -> Field.builder()
				.name(fieldError.getField()).userMessage(fieldError.getDefaultMessage()).build())
				.collect(Collectors.toList());
		var problem = createProblemBuilder(status, problemType, detail).userMessage(detail).fields(problemFields)
				.build();
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
		return Problem.builder().status(status.value()).type(problemType.getUri()).title(problemType.getTitle())
				.timestamp(LocalDateTime.now()).detail(detail);
	}

	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String path = joinPath(ex.getPath());
		var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		var detail = String.format(
				"A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
				path, ex.getValue(), ex.getTargetType().getSimpleName());
		var problem = createProblemBuilder(status, problemType, detail).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String path = joinPath(ex.getPath());
		var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		var detail = String
				.format("A propriedade '%s' não existe. Corrija ou remova essa propriedade e tente novamente.", path);
		var problem = createProblemBuilder(status, problemType, detail).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private String joinPath(List<Reference> references) {
		return references.stream().map(Reference::getFieldName).collect(Collectors.joining("."));
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		var problemType = ProblemType.PARAMETRO_INVALIDO;
		var detail = String.format(
				"O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
				ex.getName(), ex.getValue(),
				Optional.ofNullable(ex.getRequiredType()).map(Class::getSimpleName).orElseThrow());
		var problem = createProblemBuilder(status, problemType, detail).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

}