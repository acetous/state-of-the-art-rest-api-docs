package de.acetous.examples.restapidocs.springrestdocs;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringRestdocsApplication.class)
public class MyRestControllerTest {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @Test
    public void index() throws Exception {
        this.mockMvc.perform(get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("index"));
    }

    @Test
    public void time() throws Exception {
        this.mockMvc.perform(get("/time").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("time", responseFields(
                        subsectionWithPath("year").description("The current year."),
                        subsectionWithPath("month").description("The current month as human-readable."),
                        subsectionWithPath("monthValue").description("The current month."),
                        subsectionWithPath("dayOfYear").description("The current day of year."),
                        subsectionWithPath("dayOfMonth").description("The current day of month."),
                        subsectionWithPath("dayOfWeek").description("The current day of week."),
                        subsectionWithPath("hour").description("The current hour."),
                        subsectionWithPath("minute").description("The current minute."),
                        subsectionWithPath("second").description("The current second."),
                        subsectionWithPath("nano").description("The current nanosecond."),
                        subsectionWithPath("chronology.id").description("The time format."),
                        subsectionWithPath("chronology.calendarType").description("The calendar type.")

                )));
    }

    @Test
    public void name() throws Exception {
        this.mockMvc.perform(post("/name").param("name", "Sebastian").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("name"));
    }
}