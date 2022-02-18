package com.wandson.food.core.validation;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

	private DataSize maxSize;

	@Override
	public void initialize(FileSize constraintAnnotation) {
		maxSize = DataSize.parse(constraintAnnotation.max());
	}

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		return Objects.isNull(value) || value.getSize() <= maxSize.toBytes();
	}

}