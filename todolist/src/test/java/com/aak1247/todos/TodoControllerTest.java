package com.aak1247.todos;


import com.aak1247.Application;
import com.aak1247.common.AbstractDBService;
import com.aak1247.todos.entity.Todo;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@SpringBootConfiguration
public class TodoControllerTest {

    private static final String apiUrl = "/api/tasks";
    private static final Todo existedTodo = new Todo();
    private static final Todo todo = new Todo();
    private MockMvc mockMvc;
    @Autowired
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private AbstractDBService dbService;
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    private HttpHeaders httpHeaders = new HttpHeaders();

    @BeforeClass
    public static void initTest() {
        existedTodo.setId(0);
        existedTodo.setContent("已经存在了");
        existedTodo.setCreatedTime(new Date());

        todo.setId(2);
        todo.setContent("Restful API homework");
        todo.setCreatedTime(new Date());
    }

    @Before
    public void before() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();//加载上下文
        dbService.insert(String.valueOf(existedTodo.getId()), existedTodo);
    }

    @Test
    public void getTodoListTest() throws Exception {
        var rep = mockMvc.perform(get(apiUrl).contentType(contentType).headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();
        var resObj = rep.getAsyncResult();
        Assert.assertEquals(((List) resObj).size(), 1);
        Assert.assertEquals(((List) resObj).get(0), existedTodo);
    }

    @Test
    public void addTodoTest() throws Exception {
        mockMvc.perform(post(apiUrl).contentType(contentType).content(json(todo)).headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();
        var rep = mockMvc.perform(get(apiUrl).contentType(contentType).headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();
        var resObj = rep.getAsyncResult();
        Assert.assertEquals(((List) resObj).size(), 2);
    }

    @Test
    public void removeTodoTest() throws Exception {
        mockMvc.perform(delete(apiUrl + "/" + existedTodo.getId()).headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();
        var rep = mockMvc.perform(get(apiUrl).contentType(contentType).headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();
        var resObj = rep.getAsyncResult();
        Assert.assertEquals(((List) resObj).size(), 0);
    }

    @Test
    public void getTodoTest() throws Exception {
        var res = mockMvc.perform(get(apiUrl + "/" + existedTodo.getId()))
                .andExpect(status().isOk())
                .andReturn();
        var resObj = res.getAsyncResult();
        Assert.assertEquals(resObj, existedTodo);
    }

    @After
    public void removeTodos() {
        dbService.remove(String.valueOf(todo.getId()));
        dbService.remove(String.valueOf(existedTodo.getId()));
    }


    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
