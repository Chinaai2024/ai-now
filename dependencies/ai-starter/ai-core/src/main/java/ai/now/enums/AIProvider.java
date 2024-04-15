package ai.now.enums;

/**
 * AI 提供方
 * @author zhangtao <zhangtao@acegear.com>
 * @date 2024年04月15日 15:52
 */
public enum AIProvider {
    OPEN_AI("openAI" , AIProvider.open_ai_prefix),
    STABILITY_AI("stabilityAI" , AIProvider.stability_ai_prefix),
    ;


    String name ;

    String beanPrefix ;

    public final static  String open_ai_prefix  =  "openAI_";
    public final static  String stability_ai_prefix  =  "stabilityAI_";

    AIProvider(String name , String beanPrefix) {
        this.name = name;
        this.beanPrefix = beanPrefix;
    }

    public String getName() {
        return name;
    }

    public String getBeanPrefix() {
        return beanPrefix;
    }
}
