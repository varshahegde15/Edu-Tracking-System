package com.jsp.ets.mapping;

import com.jsp.ets.batch.Batch;
import com.jsp.ets.batch.BatchRequestDTO;
import com.jsp.ets.batch.BatchResponseDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class BatchMapper {

    public Batch mapToBatchEntity(@Valid BatchRequestDTO batchRequestDTO, Batch batch) {
        batch.setSubjects(batchRequestDTO.getSubjects());
        batch.setTitle(batchRequestDTO.getTitle());
        batch.setBeginsAt(batchRequestDTO.getBeginsAt());
        batch.setEndsAt(batchRequestDTO.getEndsAt());
        batch.setStartingDate(batchRequestDTO.getStartingDate());

        return batch;
    }

    public BatchResponseDTO mapToBatchResponse(Batch batch) {
        BatchResponseDTO response = new BatchResponseDTO();
        response.setBatchId(batch.getBatchId());
        response.setStartingDate(batch.getStartingDate());
        response.setStudents(batch.getStudents());
        response.setSubjects(batch.getSubjects());
        response.setTitle(batch.getTitle());
        response.setBeginsAt(batch.getBeginsAt());
        response.setEndsAt(batch.getEndsAt());

        return response;
    }
}
