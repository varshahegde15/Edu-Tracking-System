package com.jsp.ets.batch;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jsp.ets.exception.BatchNotFoundByIdException;
import com.jsp.ets.mapping.BatchMapper;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BatchService {

    private final BatchRepository batchRepo;
    private final BatchMapper batchMapper;

    public BatchResponseDTO saveBatch(BatchRequestDTO batchRequestDTO) {
        return Optional.ofNullable(batchRequestDTO)
                .map(dto -> batchMapper.mapToBatchEntity(dto, new Batch()))
                .map(batchRepo::save)
                .map(batchMapper::mapToBatchResponse)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Batch Request Data"));
    }

    public BatchResponseDTO updateBatch(@Valid BatchRequestDTO batchRequestDTO, String batch_id) {
        return batchRepo.findById(batch_id)
                .map(existingBatch -> batchMapper.mapToBatchEntity(batchRequestDTO, existingBatch))
                .map(batchRepo::save)
                .map(batchMapper::mapToBatchResponse)
                .orElseThrow(() -> new BatchNotFoundByIdException("Batch not found by id"));
    }

    public BatchResponseDTO updateBatchStatus(@Valid String batch_id, BatchStatus batchStatus) {
        return batchRepo.findById(batch_id)
                .map(existingBatch -> {
                    existingBatch.setBatchStatus(batchStatus);
                    return batchRepo.save(existingBatch);
                })
                .map(batchMapper::mapToBatchResponse)
                .orElseThrow(() -> new BatchNotFoundByIdException("Batch not found by id"));
    }
}