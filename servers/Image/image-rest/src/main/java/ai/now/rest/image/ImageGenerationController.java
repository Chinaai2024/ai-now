package ai.now.rest.image;

import ai.now.rest.beans.GenerationImageRequest;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.stabilityai.StabilityAiImageClient;
import org.springframework.ai.stabilityai.api.StabilityAiImageOptions;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zhangtao <zhangtao@acegear.com>
 * @date 2024年04月14日 12:04
 */
@RestController
@RequestMapping(value = "/image")
public class ImageGenerationController {

    final StabilityAiImageClient stabilityAiImageClient ;

    public ImageGenerationController(StabilityAiImageClient stabilityAiImageClient) {
        this.stabilityAiImageClient = stabilityAiImageClient;
    }

    @PostMapping(value = "/generation")
    public ResponseEntity<byte[]> generation(@RequestBody GenerationImageRequest request){
        try {
            ImageResponse response = stabilityAiImageClient.call(new ImagePrompt(request.getPrompt(),
                    StabilityAiImageOptions.builder()
                            .withStylePreset("cinematic")
                            .withN(1)
                            .withHeight(1024)
                            .withResponseFormat("url")
                            .withWidth(1024).build())) ;
            byte[] bytes = null ;
            if(!ObjectUtils.isEmpty(response.getResults())){
                bytes = Base64.decodeBase64(response.getResults().get(0).getOutput().getB64Json()) ;
            }
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytes) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build() ;

    }
}
