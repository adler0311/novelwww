package com.example.webnovel.batch.job;

import com.example.webnovel.batch.tasklet.EpisodePurchaseAggregateTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EpisodePurchaseAggregateConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final EpisodePurchaseAggregateTasklet episodePurchaseAggregateTasklet;

    public EpisodePurchaseAggregateConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, EpisodePurchaseAggregateTasklet episodePurchaseAggregateTasklet) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.episodePurchaseAggregateTasklet = episodePurchaseAggregateTasklet;
    }

    @Bean
    public Job purchaseAggregateJob(Step purchaseAggregateStep) {
        return jobBuilderFactory.get("purchaseAggregateJob")
                .start(purchaseAggregateStep)
                .build();
    }


    @Bean
    public Step purchaseAggregateStep() {
        return stepBuilderFactory.get("purchaseAggregateStep")
                .tasklet(episodePurchaseAggregateTasklet)
                .build();
    }


}
