package ai.now.rest.beans;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangtao <zhangtao@acegear.com>
 * @date 2024年04月14日 12:49
 */
@Data
public class GenerationImageRequest implements Serializable {
    private String prompt ;
}
