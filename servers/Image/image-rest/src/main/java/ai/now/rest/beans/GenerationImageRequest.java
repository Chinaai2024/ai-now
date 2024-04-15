package ai.now.rest.beans;

import ai.now.enums.AIProvider;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangtao <zhangtao@acegear.com>
 * @date 2024年04月14日 12:49
 */
@Data
public class GenerationImageRequest implements Serializable {
    private String prompt ;
    private int n = 1 ;
    private AIProvider provider = AIProvider.STABILITY_AI ;
}
