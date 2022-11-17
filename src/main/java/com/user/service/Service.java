package java.com.user.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class Service {
  
 private static final Logger logger = LogManager.getLogger(Service.class);
@Autowired
private RestTemplate restTemplate;

@Value("${imgur.client-id}")
private String clientId;

@Value("${imgur.uri}")
private String imgurUri;

public void uploadImage(String image, Long id){

MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
map.add("link", image);
map.add("id", id);

HttpHeaders headers = new HttpHeaders();
headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
headers.set("Client-ID", clientId);

HttpEntity<String> entity = new HttpEntity<String>(map,headers);

ResponseEntity<User[]> responseEntity = restTemplate
		.postForEntity(imgurUri, HttpMethod.POST, entity, String.class);
}
}
