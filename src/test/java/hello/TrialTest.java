package hello;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebConnection;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TrialTest {
	
	//MockMvc nos permite hacer peticiones y otras operaciones en una aplicación desarrollada
	//con el patrón arquitectónico Modelo Vista Controlador (De ahí que se necesiten @Controller
	//esta marca indica, o mejor dicho, CONTROLA lo que realizará la aplicacion en respuesta a diferentes peticiones URI)
	private MockMvc mockMvc;
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
		//post es una especie de clase estática que viene del paquete MockMvcBuilder, con ella somos capaces de hacer
		//Peticiones [post,get...]
		MvcResult result= mockMvc.perform(post("/info")//Es una petición POST a esta dirección URI
							.param("name", "Pepe") //Los parámetros que necesitamos para el formulario
							.param("password", "NoMeSeMiPassword")).andDo(print())//AndDoPrint it is very usefull to see the http response and see if something went wrong.
							.andExpect(MockMvcResultMatchers.status().isOk())//The state of the response must be OK. (200);
							.andReturn(); //We get the whole response.
		
		HtmlPage createMsgFromPage = webClient.getPage("http://localhost:8080/");
		HtmlForm form = createMsgFromPage.getHtmlElementById("Datos");
		HtmlTextInput summaryInput= createMsgFromPage.getHtmlElementById("name");
		summaryInput.setValueAttribute("Pepe");
		HtmlButton submit = form.getOneHtmlElementByAttribute("button", "type", "submit");
		HtmlPage newMessagePage = submit.click();
		
		assertTrue(newMessagePage.getUrl().toString().endsWith("/info"));
		String hola= newMessagePage.getElementById("Hola").getTextContent();
		String contraseña = newMessagePage.getElementById("contraseña").getTextContent();
		assertTrue(hola.equals("Hola Pepe"));
		assertTrue(contraseña.equals("Tu contraseña es: "));
		
		
	}
	
//	@Test
//	public void user() throws Exception{
//		//post es una especie de clase estática que viene del paquete MockMvcBuilder, con ella somos capaces de hacer
//		//Peticiones [post,get...]
//		MvcResult result= mockMvc2.perform(get("/user"))//Es una petición POST a esta dirección URI).andDo(print())//AndDoPrint it is very usefull to see the http response and see if something went wrong.
//							.andExpect(MockMvcResultMatchers.status().isOk())//The state of the response must be OK. (200);
//							.andDo(print())
//							.andReturn(); //We get the whole response.
//		
////		String contenido= result.getResponse().getbo; //We take just the content as a string in order to further test the application.
////		System.out.println(contenido);
////		assertTrue(contenido.contains("Hola Pepe"));
////		assertTrue(contenido.contains("Tu contraseña es: NoMeSeMiContraseña)"));
//		
//		
//	}

}
