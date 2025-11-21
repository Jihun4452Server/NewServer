package org.pro.newserver.global.config.swagger;

import org.pro.newserver.global.error.ErrorCode;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.util.*;

@Component
public class ApiErrorCodeOperationCustomizer implements OperationCustomizer {

	@Override
	public Operation customize(Operation operation, HandlerMethod handlerMethod) {

		ApiErrorCodeExample single = handlerMethod.getMethodAnnotation(ApiErrorCodeExample.class);
		ApiErrorCodeExamples multiple = handlerMethod.getMethodAnnotation(ApiErrorCodeExamples.class);

		List<ErrorCode> errorCodes = new ArrayList<>();

		if (single != null) {
			errorCodes.add(single.value());
		}

		if (multiple != null) {
			errorCodes.addAll(Arrays.asList(multiple.value()));
		}

		if (errorCodes.isEmpty()) {
			return operation;
		}

		ApiResponses responses = operation.getResponses();
		if (responses == null) {
			responses = new ApiResponses();
			operation.setResponses(responses);
		}

		for (ErrorCode errorCode : errorCodes) {
			String statusCode = String.valueOf(errorCode.getStatus());

			ExampleHolder holder = createExampleHolder(errorCode);

			ApiResponse apiResponse = responses.computeIfAbsent(statusCode, code -> new ApiResponse());

			if (apiResponse.getDescription() == null) {
				apiResponse.setDescription(errorCode.getMessage());
			}

			Content content = apiResponse.getContent();
			if (content == null) {
				content = new Content();
				apiResponse.setContent(content);
			}

			MediaType mediaType = content.get("application/json");
			if (mediaType == null) {
				mediaType = new MediaType();
				mediaType.setSchema(createErrorSchema());
				content.addMediaType("application/json", mediaType);
			}

			mediaType.addExamples(holder.getName(), holder.getHolder());
		}

		return operation;
	}

	private ExampleHolder createExampleHolder(ErrorCode errorCode) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", errorCode.getMessage());
		body.put("status", errorCode.getStatus());
		body.put("errors", Collections.emptyList());
		body.put("code", errorCode.getCode());

		Example example = new Example();
		example.setSummary(errorCode.name());
		example.setDescription(errorCode.getMessage());
		example.setValue(body);

		return ExampleHolder.builder()
			.name(errorCode.name())
			.status(errorCode.getStatus())
			.code(errorCode.getCode())
			.holder(example)
			.build();
	}

	private Schema<?> createErrorSchema() {
		Schema<?> schema = new Schema<>();
		schema.setType("object");
		schema.addProperty("message", new StringSchema().example("이미 사용 중인 이메일입니다."));
		schema.addProperty("status", new IntegerSchema().example(409));
		schema.addProperty("errors", new ArraySchema()
			.items(new StringSchema())
			.example(Collections.emptyList()));
		schema.addProperty("code", new StringSchema().example("U003"));
		return schema;
	}
}
