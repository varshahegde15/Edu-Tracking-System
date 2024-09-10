package com.jsp.ets.batch;

import java.util.Optional;

import com.jsp.ets.utility.CacheHelper;
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
    private final CacheHelper cacheHelper;

    public BatchResponseDTO saveBatch(BatchRequestDTO batchRequestDTO) {
//        return Optional.ofNullable(batchRequestDTO)
//                .map(dto -> batchMapper.mapToBatchEntity(dto, new Batch()))
//                .map(batchRepo::save)
//                .map(batchMapper::mapToBatchResponse)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid Batch Request Data"));
        Batch batch = batchMapper.mapToBatchEntity(batchRequestDTO,new Batch());
        Batch cbatch = cacheHelper.batchCache(batch);
        Batch dbatch = cacheHelper.getBatchCache(cbatch.getTitle());
        System.out.println(dbatch.getTitle());
        return batchMapper.mapToBatchResponse(batch);
    }

    public BatchResponseDTO updateBatch(@Valid BatchRequestDTO batchRequestDTO, String batchId) {
        return batchRepo.findById(batchId)
                .map(existingBatch -> batchMapper.mapToBatchEntity(batchRequestDTO, existingBatch))
                .map(batchRepo::save)
                .map(batchMapper::mapToBatchResponse)
                .orElseThrow(() -> new BatchNotFoundByIdException("Batch not found by id"));
    }

    public BatchResponseDTO updateBatchStatus(@Valid String batchId, BatchStatus batchStatus) {
        return batchRepo.findById(batchId)
                .map(existingBatch -> {
                    existingBatch.setBatchStatus(batchStatus);
                    return batchRepo.save(existingBatch);
                })
                .map(batchMapper::mapToBatchResponse)
                .orElseThrow(() -> new BatchNotFoundByIdException("Batch not found by id"));
    }
}