package com.wandson.food.core.validation;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

	private List<String> allowedContentTypes;

	@Override
	public void initialize(FileContentType constraint) {
		allowedContentTypes = Arrays.asList(constraint.allowed());
	}

	@Override
	public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
		return Objects.isNull(multipartFile) || allowedContentTypes.contains(multipartFile.getContentType());
	}

}