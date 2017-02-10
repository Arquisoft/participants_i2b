package hello;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import domain.User;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TrialTest {
	
	//MockMvc --> Para realizar peticiones y comprobar resultado, usado para respuestas con informacion json.
	private MockMvc mockMvc;
	//WebClient --> We simmulate a user using our web interface, interacting with the html components and navigability in the web application.
	private WebClient webClient;
	
	
	@Before
	public void setup() throws Exception {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		this.mockMvc = standaloneSetup(new MainController() ).setViewResolvers(viewResolver).build();
		webClient = new WebClient();
		  //webClient.setWebConnection(new MockMvcWebConnection(mockMvc));
		  webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		  webClient.getOptions().setThrowExceptionOnScriptError(false);
		  
		  //webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);

	}
	
	@Test
	public void userInsertInformation() throws Exception{
		
		//Here would be more appropiate to get the user from the database.
		User prueba= new User("Pepe", "holaApellidos", "hola@hola", "NoMeSeMiPassword");
		
		mockMvc.perform(post("/user")//We send a POST request to that URI (from http:localhost...)
							.param("name", "Pepe") //The needed parameter for the form.
							.param("password", "NoMeSeMiPassword")).andDo(print())//AndDoPrint it is very usefull to see the http response and see if something went wrong.
							.andExpect(MockMvcResultMatchers.status().isOk()) //The state of the response must be OK. (200); 
							.andExpect(jsonPath("$.firstName",is(prueba.firstName))); //We can do jsonpaths in order to check that the json information displayes its ok.
		
		//Just an example of how to manage the from of this page in case it is necessary further checking as a user using the web interface.
//		HtmlPage createMsgFromPage = webClient.getPage("http://localhost:8080/");
//		HtmlForm form = createMsgFromPage.getHtmlElementById("Datos");
//		HtmlTextInput summaryInput= createMsgFromPage.getHtmlElementById("name");
//		summaryInput.setValueAttribute("Pepe");
//		HtmlButton submit = form.getOneHtmlElementByAttribute("button", "type", "submit");
//		
		
//Este test sería apropiado si devuelve una página html y no información JSON
//		HtmlPage newMessagePage = submit.click();
//		
//		assertTrue(newMessagePage.getUrl().toString().endsWith("/info"));
//		String hola= newMessagePage.getElementById("Hola").getTextContent();
//		String contraseña = newMessagePage.getElementById("contraseña").getTextContent();
//		assertTrue(hola.equals("Hola Pepe"));
//		assertTrue(contraseña.equals("Tu contraseña es: "));
		
		
	}

}
