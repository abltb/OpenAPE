import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.openape.api.usercontext.UserContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class ParsingTest {
@Test
public void testUserContextParsing() throws JsonParseException, JsonMappingException, IOException{
//assertEquals(new File("test.json").getAbsolutePath(), null );
	ObjectMapper mapper = new ObjectMapper();
	mapper.readValue(new File("src/test/resources/test.json"), UserContext.class);
}

@Test
public void testWriting() throws JsonParseException, JsonMappingException, IOException{
	ObjectMapper mapper = new ObjectMapper();
	UserContext uc1 = mapper.readValue(new File("src/test/resources/test.json"), UserContext.class);
	String str = new Gson().toJson(uc1);
	FileWriter fw = new FileWriter("src/test/resources/toWrite.json");
fw.write(str);;
UserContext uc2 = mapper.readValue(new File("src/test/resources/toWrite.json"), UserContext.class);
fw.close();
}
}


