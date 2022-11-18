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

//Upload images to Imgur
public void uploadImage(String image, Long id){
	
//Setting the request params
MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
map.add("link", image);
	
//Setting headers for the request
HttpHeaders headers = new HttpHeaders();
headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
headers.set("Authorization", "Client-ID" + clientId);

HttpEntity<String> entity = new HttpEntity<String>(map,headers);
/*curl --location --request POST 'https://api.imgur.com/3/image' \
--header 'Authorization: Client-ID {{clientId}}' \
--form 'image="R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7"'*/
	
try{
	ResponseEntity<User[]> responseEntity = restTemplate
		.postForEntity(imgurUri, HttpMethod.POST, entity, String.class);
}catch(Exception e) {
	logger.debug("Issue while uploading image to imgur"+ e.stackTrace());
}
}
}
