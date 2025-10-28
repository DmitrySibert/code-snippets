import com.boot.starters.HeadphoneData;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Component
class HeadphonesShop {

@Value("target.uri")
private String warehouseApiUri;
@Value("target.port")
private int port;
@Value("target.path")
private String path;

@Autowired
private RestTemplate template;

public void enrichWithStock(List<HeadphoneData> models) {
    try {
        models.stream()
                .map(headphoneData -> {
                    HttpEntity<Long> request = new HttpEntity<>(headphoneData.getCode());
                    Map<String, String> response = template.postForObject(warehouseApiUri, request, Map.class, port, path);
                    headphoneData.setStock(Integer.getInteger(response.get("stock")));
                    return headphoneData;
                });
    } catch (Exception e) {
        //exception
        models.forEach(headphoneData -> headphoneData.setStock(0));
    }
}
}
