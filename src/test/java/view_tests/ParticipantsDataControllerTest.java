package view_tests;

/**
 * Created by Jorge.
 */
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import domain.UserLoginData;
import main.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.gargoylesoftware.htmlunit.WebClient;

import domain.User;
import services.ParticipantsService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes ={Application.class})
@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTest("local.server.port=1")
public class ParticipantsDataControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ParticipantsService service;

	//MockMvc --> Para realizar peticiones y comprobar resultado, usado para respuestas con informacion json.
	private MockMvc mockMvc;
	//WebClient --> We simmulate a user using our web interface,
    //interacting with the html components and navigability in the web application.
	private WebClient webClient;
	
	
	@Before
	public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		webClient = new WebClient();
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);

	}



    @Test
	public void userInsertInformation() throws Exception{
	    /*
	    {
            "_id" : ObjectId("58a8670df086e81dc034d7fc"),
            "firstName" : "Prueba01",
            "lastName" : "Apellido01",
            "email" : "prueba01@prueba.es",
            "address" : "c/Prueba n0 1a",
            "nationality" : "España",
            "userId" : "00000001J",
            "dateOfBirth" : ISODate("1981-12-27T23:00:00.000Z"),
            "unencrypted" : "4[[j[frVCUMJ>hU",
            "password" : "khZZwjdhWwVbMdmOvz9eqBfKR1N6A+CdFBDM9c1dQduUnGewQyPRlBxB4Q6wT7Cq"
        }
	     */

	    /*
	    Response
	    {"firstName":"Prueba01","lastName":"Apellido01","age":35,"userId":"00000001J","email":"prueba01@prueba.es"}
	    * */
		
		//Here would be more appropiate to get the user from the database.
        User prueba = new User("Prueba01", "Apellido01",
                    "prueba01@prueba.es", "4[[j[frVCUMJ>hU");
        UserLoginData data = new UserLoginData("prueba01@prueba.es", "4[[j[frVCUMJ>hU");
		String payload = String.format("{\"login\":\"%s\", \"password\":\"%s\"}", data.getLogin(), data.getPassword());
        //We send a POST request to that URI (from http:localhost...)
        MockHttpServletRequestBuilder request = post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(payload.getBytes());
		mockMvc.perform(request)
                            .andDo(print())//AndDoPrint it is very usefull to see the http response and see if something went wrong.
							.andExpect(status().isOk()) //The state of the response must be OK. (200);
							.andExpect(jsonPath("$.firstName",is(prueba.getFirstName()))) //We can do jsonpaths in order to check that the json information displayes its ok.
                            .andExpect(jsonPath("$.lastName", is(prueba.getLastName())))
                            .andExpect(jsonPath("$.age", is(35)))//Born in 1996
                            .andExpect(jsonPath("$.userId", is("00000001J")))
                            .andExpect(jsonPath("$.email", is("prueba01@prueba.es")));
		
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
    
//    @Test
//	public void userInterfaceInsertInfo() throws Exception{
//    	/*
//	    {
//            "_id" : ObjectId("58a8670df086e81dc034d7fc"),
//            "firstName" : "Prueba01",
//            "lastName" : "Apellido01",
//            "email" : "prueba01@prueba.es",
//            "address" : "c/Prueba n0 1a",
//            "nationality" : "España",
//            "userId" : "00000001J",
//            "dateOfBirth" : ISODate("1981-12-27T23:00:00.000Z"),
//            "unencrypted" : "4[[j[frVCUMJ>hU",
//            "password" : "khZZwjdhWwVbMdmOvz9eqBfKR1N6A+CdFBDM9c1dQduUnGewQyPRlBxB4Q6wT7Cq"
//        }
//	     */
    
    //No funciona por cambio en el método
////        MockHttpServletRequestBuilder request = post("/userForm").param("login", "prueba01@prueba.es")
////															.param("password", "4[[j[frVCUMJ>hU");
////    	
////    	mockMvc.perform(request).andExpect(status().isOk());
//    }
  }


