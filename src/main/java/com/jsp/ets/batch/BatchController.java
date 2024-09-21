package com.jsp.ets.batch;

import com.jsp.ets.user.response_dtos.StudentResponseDTO;
import com.jsp.ets.utility.ErrorStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Batch Controller", description = "APIs for managing Batches including creating the Batch and updating the batch")
public class BatchController {

    private BatchService batchService;
    private CustomResponseBuilder responseBuilder;


    @Operation(description = "The API endpoint is used to create a new Batch."
            + " The endpoint validates the BatchRequestDTO and converts it into a batch entity and stores it into the database.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Batch created"),
                    @ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(anyOf = RuntimeException.class)))
            })
    @PostMapping("/batches")
    public ResponseEntity<ResponseStructure<BatchResponseDTO>> saveBatch(
            @RequestBody @Valid BatchRequestDTO batchRequestDTO) {
        BatchResponseDTO response = batchService.saveBatch(batchRequestDTO);
        return responseBuilder.success(HttpStatus.CREATED, "Batch created", response);
    }


    @Operation(description = "The API endpoint is used to update the details of an existing Batch based on a unique Identifier."
            + " The endpoint requires the path variable batchId and the batch details that are to be updated.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Batch updated"),
                    @ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
                    @ApiResponse(responseCode = "404", description = "Student not found", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(anyOf = RuntimeException.class)))
            })
    @PutMapping("/batches/{batchId}")
    public ResponseEntity<ResponseStructure<BatchResponseDTO>> updateBatch(
            @RequestBody @Valid BatchRequestDTO batchRequestDTO, @PathVariable String batchId) {
        BatchResponseDTO response = batchService.updateBatch(batchRequestDTO, batchId);
        return responseBuilder.success(HttpStatus.OK, "Batch updated", response);
    }


    @Operation(
            summary = "Update Batch status to cancelled",
            description = "The API endpoint is used to update the status of an existing Batch to cancelled based on a unique Identifier. "
                    + "The endpoint requires the path variable batchId.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Batch is been cancelled",
                            content = @Content(schema = @Schema(implementation = StudentResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Batch not found",
                            content = @Content(schema = @Schema(implementation = ErrorStructure.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(schema = @Schema(implementation = RuntimeException.class))
                    )
            }
    )
    @PatchMapping("/batches/{batchId}")
    public ResponseEntity<ResponseStructure<BatchResponseDTO>> updateBatchStatusToCancelled(
            @Valid @PathVariable String batchId) {
        BatchResponseDTO response = batchService.updateBatchStatus(batchId, BatchStatus.CANCELLED);
        return responseBuilder.success(HttpStatus.OK, "Batch status updated to cancelled", response);
    }


    @Operation(
            summary = "Update Batch status to closed",
            description = "The API endpoint is used to update the status of an existing Batch to closed based on a unique Identifier. "
                    + "The endpoint requires the path variable batchId.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Batch is been closed",
                            content = @Content(schema = @Schema(implementation = StudentResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Batch not found",
                            content = @Content(schema = @Schema(implementation = ErrorStructure.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(schema = @Schema(implementation = RuntimeException.class))
                    )
            }
    )
    @PatchMapping("/batches/{batchId}/closed")
    public ResponseEntity<ResponseStructure<BatchResponseDTO>> updateBatchStatusToClossed(
            @Valid @PathVariable String batchId) {
        BatchResponseDTO response = batchService.updateBatchStatus(batchId, BatchStatus.CLOSED);
        return responseBuilder.success(HttpStatus.OK, "Batch status updated to clossed", response);
    }

}
