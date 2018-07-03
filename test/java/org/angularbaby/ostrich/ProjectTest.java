package org.angularbaby.ostrich;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.angularbaby.ostrich.config.JwtConfig;
import org.angularbaby.ostrich.entity.Project;
import org.angularbaby.ostrich.entity.TaskGroup;
import org.angularbaby.ostrich.entity.User;
import org.angularbaby.ostrich.repository.ProjectsRepository;
import org.angularbaby.ostrich.repository.TaskGroupsRepository;
import org.angularbaby.ostrich.repository.TasksRepository;
import org.angularbaby.ostrich.repository.UsersRepository;
import org.angularbaby.ostrich.request.LoginRequest;
import org.angularbaby.ostrich.request.ProjectRequest;
import org.angularbaby.ostrich.response.TaskGroupDetail;
import org.angularbaby.ostrich.response.UserDetail;
import org.angularbaby.ostrich.web.AuthenticationInterceptor;
import org.angularbaby.ostrich.web.ProjectsController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectTest {

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

    private String token;
    @Autowired
    private ProjectsController projectsController;

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
        this.projectsRepository.save(new Project("software","hello"));
        this.usersRepository.save(new User("123@tongji.edu.cn", "123456", "chris"));
        User user = this.usersRepository.findByEmail("123@tongji.edu.cn");

        // 生成 token
        Algorithm algorithm = Algorithm.HMAC256(JwtConfig.getSecret());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);
        this.token = JWT.create().withIssuer("ostrich")
                .withClaim("user_id", user.getId())
                .withClaim("exp", calendar.getTime())
                .sign(algorithm);
    }

    @Test
    public void createSuccess() throws Exception {


        ProjectRequest request = new ProjectRequest();

        boolean flag = false;

        request.setTitle("hello");
        request.setDescription("software testing project");
//        if(projectsController.createNewProject(request)!=null){
//            flag = true;
//        }
//
//        assertEquals(true,flag);



       String loginJson = json(request);

        this.mockMvc.perform(post("/api/projects")
                .contentType(contentType)
               .header("Authorization", "Bearer " + this.token)
               .content(loginJson))
                .andExpect(status().isCreated());
    }
//    @Test
//    public void joinSuccess () throws Exception{
//        boolean flag = false;
//        Long id =66l;
//        Project project = projectsRepository.findOne(id);
//        if(project!=null){
//            flag=true;
//    }
//        assertEquals(true,flag);}

//    @Test
//    public void listMembers () throws Exception{
//        boolean flag = false;
//        Long id =66l;
//        //Project project = projectsRepository.findOne(id);
//        if(projectsRepository.findOne(id)!=null){
//            flag=true;
//        }
//        assertEquals(true,flag);}
//
//
//    @Test
//    public void listTaskGroup () throws Exception{
//        boolean flag = false;
//        Long id =66l;
//        //Project project = projectsRepository.findOne(id);
//        if(projectsRepository.findOne(id)!=null){
//            flag=true;
//        }
//        assertEquals(true,flag);}
//
//
//    @Test
//    public void leaveGroup () throws Exception{
//        boolean flag = false;
//        Long id =66l;
//        //Project project = projectsRepository.findOne(id);
//        if(projectsRepository.findOne(id)!=null){
//            flag=true;
//        }
//        assertEquals(true,flag);}
//
//    @Test
//    public void getProjectInformation () throws Exception{
//        boolean flag = false;
//        Long id =66l;
//        //Project project = projectsRepository.findOne(id);
//        if(projectsRepository.findOne(id)!=null){
//            flag=true;
//        }
//        assertEquals(true,flag);}


//    @Test
//    public void addTaskGroup() throws Exception{
//        boolean flag = false;
//        Long id =66l;
//        Project project = projectsRepository.findOne(id);
//        if(projectsRepository.findOne(id)!=null){
//
//            TaskGroup group = new TaskGroup("",project);
//            taskGroupsRepository.save(group);
//            if(group.getName()!=""){
//                flag = true;
//            }
//        }
//        assertEquals(true,flag);}

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}


