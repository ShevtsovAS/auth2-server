package com.ashevtsov.auth2server.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static com.ashevtsov.auth2server.TestAppConstants.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthorizationServerConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    private JacksonJsonParser jsonParser = new JacksonJsonParser();

    @Test
    public void getAndCheckAccessToken() throws Exception {
        checkAccessToken(obtainAccessToken());
    }

    private void checkAccessToken(String accessToken) throws Exception {

        assertNotNull(accessToken);

        String result = mockMvc.perform(get(CHECK_TOKEN_URL)
                .param(TOKEN_PARAM, accessToken)
                .with(httpBasic(HTTP_BASIC_USER_NAME, HTTP_BASIC_USER_PASSWORD))
                .accept(CONTENT_TYPE_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE_JSON))
                .andReturn().getResponse().getContentAsString();

        Map<String, Object> resultMap = jsonParser.parseMap(result);

        assertThat(resultMap.get("user_name").toString(), is(TEST_USER_NAME));
        assertThat(resultMap.get("client_id").toString(), is(HTTP_BASIC_USER_NAME));
    }

    private String obtainAccessToken() throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(USER_NAME_PARAM, TEST_USER_NAME);
        params.add(USER_PASSWORD_PARAM, TEST_USER_PASSWORD);
        params.add(GRANT_TYPE_PARAM, TEST_USER_GRANT_TYPE);

        String resultString = mockMvc.perform(post(OAUTH_TOKEN_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .params(params)
                .with(httpBasic(HTTP_BASIC_USER_NAME, HTTP_BASIC_USER_PASSWORD))
                .accept(CONTENT_TYPE_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE_JSON))
                .andReturn().getResponse().getContentAsString();

        return jsonParser.parseMap(resultString).get(ACCESS_TOKEN).toString();
    }

}