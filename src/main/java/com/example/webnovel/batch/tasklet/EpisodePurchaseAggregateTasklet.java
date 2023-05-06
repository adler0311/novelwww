package com.example.webnovel.batch.tasklet;

import com.example.webnovel.aggregation.dto.EpisodePurchaseAggregateDto;
import com.example.webnovel.aggregation.persistence.EpisodePurchaseAggregateRepository;
import com.example.webnovel.transaction.persistence.EpisodePurchaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
public class EpisodePurchaseAggregateTasklet implements Tasklet {
    private final EpisodePurchaseRepository episodePurchaseRepository;
    private final EpisodePurchaseAggregateRepository episodePurchaseAggregateRepository;
    public EpisodePurchaseAggregateTasklet(EpisodePurchaseRepository episodePurchaseRepository, EpisodePurchaseAggregateRepository episodePurchaseAggregateRepository) {

        this.episodePurchaseRepository = episodePurchaseRepository;
        this.episodePurchaseAggregateRepository = episodePurchaseAggregateRepository;
    }
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<EpisodePurchaseAggregateDto> episodePurchaseAggregateDtoList = episodePurchaseRepository.getEpisodeAggregatedResult();
        for (EpisodePurchaseAggregateDto episodePurchaseAggregateDto: episodePurchaseAggregateDtoList) {
            episodePurchaseAggregateRepository.upsert(
                    episodePurchaseAggregateDto.getNovelId(),
                    episodePurchaseAggregateDto.getEpisodeId(),
                    episodePurchaseAggregateDto.getPurchaseCount());

        }
        return RepeatStatus.FINISHED;
    }
}
