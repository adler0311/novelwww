package com.example.webnovel.service;

import com.example.webnovel.persistence.Episode;
import com.example.webnovel.persistence.EpisodeRepository;
import org.springframework.stereotype.Service;


@Service
public class EpisodeService {
    private final EpisodeRepository episodeRepository;

    public EpisodeService(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }


    public Episode saveEpisode(Episode episode) {
        Integer maxSeriesNumber = episodeRepository.findMaxSeriesNumber();
        episode.setSeriesNumber(maxSeriesNumber + 1);
        return episodeRepository.save(episode);

    }
}
