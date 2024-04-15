package ai.now.client;

import ai.now.enums.AIModule;
import ai.now.enums.AIProvider;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.image.ImageClient;
import org.springframework.ai.openai.OpenAiImageClient;
import org.springframework.ai.stabilityai.StabilityAiImageClient;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangtao <zhangtao@acegear.com>
 * @date 2024年04月15日 16:01
 */
public class AIClient implements ApplicationContextAware {

    /**
     * 图片客户端
     */
    Map<String, ImageClient> IMAGE_CLIENT = new HashMap<>() ;

    /**
     * 聊天客户端
     */
    Map<String , ChatClient> CHAT_CLIENT = new HashMap<>() ;




    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        IMAGE_CLIENT.put(AIProvider.open_ai_prefix + AIModule.image_prefix , applicationContext.getBean(OpenAiImageClient.class)) ;
        IMAGE_CLIENT.put(AIProvider.stability_ai_prefix + AIModule.image_prefix , applicationContext.getBean(StabilityAiImageClient.class)) ;
    }

    public <T extends ImageClient> T getImageClient(AIProvider provider){
        return (T)IMAGE_CLIENT.get(provider.getBeanPrefix() + AIModule.image_prefix) ;
    }

    public <T extends ChatClient> T getChatClient(AIProvider provider){
        return (T)CHAT_CLIENT.get(provider.getBeanPrefix() + AIModule.chat_prefix) ;
    }



}
