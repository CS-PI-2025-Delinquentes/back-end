package com.pagil.teruel_express.config;

import com.pagil.teruel_express.service.CodigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class CodigoSchedulerConfig {

    @Autowired
    private CodigoService codigoService;

    @Scheduled(cron = "0 */15 * * * *")
    public void limparCodigosExpirados() {
        codigoService.limparCodigosExpirados();
    }
}
