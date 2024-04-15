package ai.now.enums;

/**
 * AI 模块
 * @author zhangtao <zhangtao@acegear.com>
 * @date 2024年04月15日 16:00
 */
public enum AIModule {

    CHAT(AIModule.chat_prefix) ,
    IMAGE(AIModule.image_prefix) ,

    ;


    public final static  String chat_prefix  =  "chat";
    public final static  String image_prefix  =  "image";



    String beanPrefix ;

    AIModule(String beanPrefix) {
        this.beanPrefix = beanPrefix;
    }
}
