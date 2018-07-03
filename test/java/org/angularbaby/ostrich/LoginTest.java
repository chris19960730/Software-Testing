package org.angularbaby.ostrich;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.angularbaby.ostrich.entity.User;
import org.angularbaby.ostrich.repository.ProjectsRepository;
import org.angularbaby.ostrich.repository.TaskGroupsRepository;
import org.angularbaby.ostrich.repository.TasksRepository;
import org.angularbaby.ostrich.repository.UsersRepository;
import org.angularbaby.ostrich.request.LoginRequest;
import org.angularbaby.ostrich.web.AuthenticationInterceptor;
import org.angularbaby.ostrich.web.SessionsController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class LoginTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
        MediaType.APPLICATION_JSON.getSubtype(),
        Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ProjectsRepository projectsRepository;

    @Autowired
    private TaskGroupsRepository taskGroupsRepository;

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private SessionsController sessionsController;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        this.tasksRepository.deleteAllInBatch();
        this.taskGroupsRepository.deleteAllInBatch();
        this.projectsRepository.deleteAllInBatch();
        this.usersRepository.deleteAllInBatch();
        this.usersRepository.save(new User("NMSL@tongji.edu.cn", "1234", "SUNXIAOCHUAN"));
        this.usersRepository.save(new User("tanyang666@tongji.edu.cn", "1234", "tanyang"));
        this.usersRepository.save(new User("zhuzhuxia@tongji.edu.cn", "1234", "zhuzhuxia"));

    }

    @Test
    public void loginSuccess() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setEmail("NMSL@tongji.edu.cn");
        request.setPassword("1234");
        String loginJson = json(request);
        boolean result = false;
        if (sessionsController.login(request)!= null) {
            if (request.getEmail() == "NMSL@tongji.edu.cn" && request.getPassword() == "1234") {
                this.mockMvc.perform(post("/api/sessions")
                        .contentType(contentType)
                        .content(loginJson))
                        .andExpect(status().isCreated());
                result = true;
            } else {
                this.mockMvc.perform(post("/api/sessions")
                        .contentType(contentType)
                        .content(loginJson))
                        .andExpect(status().isUnauthorized());

            }
            // System.out.print(result);
        }
        assertEquals(true, result);
    }



    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}