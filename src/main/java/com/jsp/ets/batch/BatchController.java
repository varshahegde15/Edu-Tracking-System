package com.jsp.ets.batch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ets.utility.CustomResponseBuilder;
import com.jsp.ets.utility.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class BatchController {

	private BatchService batchService;
	private CustomResponseBuilder responseBuilder;

	@PostMapping("/batches")
	public ResponseEntity<ResponseStructure<BatchResponseDTO>> saveBatch(
			@RequestBody @Valid BatchRequestDTO batchRequestDTO) {
		BatchResponseDTO response = batchService.saveBatch(batchRequestDTO);
		return responseBuilder.success(HttpStatus.CREATED, "Batch created", response);
	}

	@PutMapping("/batches/{batchId}")
	public ResponseEntity<ResponseStructure<BatchResponseDTO>> updateBatch(
			@RequestBody @Valid BatchRequestDTO batchRequestDTO, @PathVariable String batchId) {
		BatchResponseDTO response = batchService.updateBatch(batchRequestDTO, batchId);
		return responseBuilder.success(HttpStatus.OK, "Batch updated", response);
	}

	@PatchMapping("/batches/{batchId}")
	public ResponseEntity<ResponseStructure<BatchResponseDTO>> updateBatchStatusToCancelled(
			@Valid @PathVariable String batch_id) {
		BatchResponseDTO response = batchService.updateBatchStatus(batch_id, BatchStatus.CANCELLED);
		return responseBuilder.success(HttpStatus.OK, "Batch status updated to cancelled", response);
	}

	@PatchMapping("/batches/{batchId}/closed")
	public ResponseEntity<ResponseStructure<BatchResponseDTO>> updateBatchStatusToClossed(
			@Valid @PathVariable String batchId) {
		BatchResponseDTO response = batchService.updateBatchStatus(batchId, BatchStatus.CLOSED);
		return responseBuilder.success(HttpStatus.OK, "Batch status updated to clossed", response);
	}

}
