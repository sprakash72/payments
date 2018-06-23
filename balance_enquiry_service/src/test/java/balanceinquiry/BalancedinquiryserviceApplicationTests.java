package balanceinquiry;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import balanceinquiry.models.MessageRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BalancedinquiryserviceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	void CheckBalancePass() {
		String uri = "/api/balance";

		MessageRequest message = new MessageRequest();
		message.setMessageid(1);
		message.setCustid(200);

	}

	/// Map java object to JSON
	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

	// Maps JSON to a java class T
	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, clazz);
	}

}
