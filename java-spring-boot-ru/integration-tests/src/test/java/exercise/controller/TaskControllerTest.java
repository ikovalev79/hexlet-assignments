package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
//import org.junit.jupiter.api.BeforeAll;

@SpringBootTest
@AutoConfigureMockMvc
// END
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testCreate() throws Exception {
        var task = new Task();
        task.setTitle(faker.lorem().sentence(2));
        task.setDescription(faker.lorem().sentence(5));

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task));

        mockMvc.perform(request)
                .andExpect(status().isCreated());
        
        var task1 = taskRepository.findByTitle(task.getTitle());
        assertThat(task1.isPresent());
        assertThat(task1.get().getDescription().equals(task.getDescription()));
        
        System.out.println("testCreate(). id: " + task1.get().getId() + "; title: " + task1.get().getTitle() + "; description: " + task1.get().getDescription());
    }
    
    @Test
    public void testShow() throws Exception {
        var task = new Task();
        task.setTitle("showing task title");
        task.setDescription("showing task description");
        task = taskRepository.save(task);
        
        System.out.println("testShow(). id: " + task.getId() + "; title: " + task.getTitle() + "; description: " + task.getDescription());
    	
        var result = mockMvc.perform(get("/tasks/{id}", task.getId())).andReturn();
        var body = result.getResponse().getContentAsString();
        System.out.println("body: " + body);
        
        mockMvc.perform(get("/tasks/{id}", task.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(task)));
    }
    
    @Test
    public void testShowNegative() throws Exception {
        var result = mockMvc.perform(get("/tasks/{id}", 100))
                .andExpect(status().isNotFound())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Task with id 100 not found");
    }
    
    @Test
    public void testUpdate() throws Exception {
        var task = new Task();
        task.setTitle("updating task title");
        task.setDescription("updating task description");
        task = taskRepository.save(task);

        var data = new HashMap<>();
        data.put("title", "updating task title 1");
        data.put("description", "updating task description 1");

        var request = put("/tasks/{id}", task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        var actualTask = taskRepository.findById(task.getId()).get();

        assertThat(actualTask.getTitle()).isEqualTo(data.get("title"));
        assertThat(actualTask.getDescription()).isEqualTo(data.get("description"));
    }
    // END
}