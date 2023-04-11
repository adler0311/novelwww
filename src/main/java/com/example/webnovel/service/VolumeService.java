package com.example.webnovel.service;

import com.example.webnovel.persistence.Volume;
import com.example.webnovel.persistence.VolumeRepository;
import org.springframework.dao.OptimisticLockingFailureException;


public class VolumeService {
    private final VolumeRepository volumeRepository;

    public VolumeService(VolumeRepository volumeRepository) {
        this.volumeRepository = volumeRepository;
    }


    public Volume saveVolume(Volume volume) {
        Integer maxSeriesNumber = volumeRepository.findMaxSeriesNumber();
        volume.setSeriesNumber(maxSeriesNumber + 1);
        return volumeRepository.save(volume);

    }
}
