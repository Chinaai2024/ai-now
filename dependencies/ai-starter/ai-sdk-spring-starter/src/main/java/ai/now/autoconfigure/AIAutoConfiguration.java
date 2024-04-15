package ai.now.autoconfigure;

import ai.now.client.AIClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author zhangtao <zhangtao@acegear.com>
 * @date 2024年04月15日 15:50
 */
@AutoConfiguration
public class AIAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public AIClient client(){
        return new AIClient() ;
    }
}
