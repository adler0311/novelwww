package com.example.webnovel.episode.service;

import com.example.webnovel.episode.persistence.Episode;
import com.example.webnovel.episode.persistence.EpisodeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


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

    public void increaseViewCount(Long episodeId) {
        Optional<Episode> episodeOptional = episodeRepository.findEpisodeById(episodeId);
        if (episodeOptional.isPresent()) {
            Episode episode = episodeOptional.get();
            episode.increaseViewCount(1);
            episodeRepository.save(episode);
        }
    }
}
