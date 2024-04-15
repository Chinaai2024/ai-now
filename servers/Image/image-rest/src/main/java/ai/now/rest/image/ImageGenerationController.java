package ai.now.rest.image;

import ai.now.client.AIClient;
import ai.now.enums.AIProvider;
import ai.now.rest.beans.GenerationImageRequest;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.ai.image.ImageClient;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.stabilityai.api.StabilityAiImageOptions;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


/**
 * @author zhangtao <zhangtao@acegear.com>
 * @date 2024年04月14日 12:04
 */
@RestController
@RequestMapping(value = "/image")
public class ImageGenerationController {

    final AIClient aiClient ;


    public ImageGenerationController(AIClient aiClient ) {
        this.aiClient = aiClient;
    }

    @PostMapping(value = "/generation" , consumes = {MediaType.APPLICATION_JSON_VALUE} ,produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<InputStream> generation(@RequestBody GenerationImageRequest request){
        try {
            ImageClient client =  aiClient.getImageClient(AIProvider.STABILITY_AI) ;
            ImageResponse response = client.call(new ImagePrompt(request.getPrompt(),
                    StabilityAiImageOptions.builder()
                            .withStylePreset("cinematic")
                            .withN(request.getN())
                            .withHeight(1024)
                            .withWidth(1024).build())) ;
            byte[] bytes = null ;
            if(!ObjectUtils.isEmpty(response.getResults())){
                bytes = Base64.decodeBase64(response.getResults().get(0).getOutput().getB64Json()) ;
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(new ByteArrayInputStream(bytes)) ;
            }
            return ResponseEntity.ok().build() ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build() ;

    }
}
