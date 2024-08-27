package com.example.WebSocketCoummunication;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Optional;

import com.example.WebSocketCoummunication.model.User;
import com.example.WebSocketCoummunication.model.UserInfo;
import com.example.WebSocketCoummunication.service.FormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FormService formService;

    @BeforeEach
    public void setUp() {
        when(formService.addNewUser(anyString(), anyString())).thenReturn(Optional.of(new User()));
        when(formService.saveUserDetails(any(UserInfo.class))).thenReturn(Optional.of(new UserInfo()));
    }

    @Test
    public void testRegisterNewUser() throws Exception {
        mockMvc.perform(post("/register/new")
                        .param("nickname", "testUser")
                        .param("password", "securePass123")
                        .param("email", "test@example.com")
                        .param("phone", "1234567890")
                        .param("country", "POLAND")
                        .param("gender", "MALE")
                        .param("birthDate", "2000-01-01"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }
}
