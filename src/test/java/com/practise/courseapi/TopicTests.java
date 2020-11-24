package com.practise.courseapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicTests {

    @Autowired
    private TopicService service;

    @MockBean
    private TopicRepository repository;

    @Test
    public void getAllTopicsTest() {
        when(repository.findAll()).thenReturn(Stream.of(
                new Topic("Spring", "Spring Framework", "Spring Framework Description"))
                .collect(Collectors.toList()));
        assertEquals(1, service.getAllTopics().size());
    }

    @Test
    public void getTopicTest() {
        when(repository.findById("Spring")).thenReturn(java.util.Optional.of(
                new Topic("Spring", "Spring Framework", "Spring Framework Description")));
        assertEquals("Spring Framework", service.getTopic("Spring").get().getName());
        assertEquals("Spring Framework Description", service.getTopic("Spring").get().getDescription());
    }

    @Test
    public void addTopicTest() {
        Topic topic = new Topic("Spring", "Spring Framework", "Spring Framework Description");
        service.addTopic(topic);
        verify(repository, times(1)).save(topic);
    }

    @Test
    public void deleteTopicTest() {
        Topic topic = new Topic("Spring", "Spring Framework", "Spring Framework Description");
        service.deleteTopic(topic.getId());
        verify(repository, times(1)).deleteById(topic.getId());
    }
}
