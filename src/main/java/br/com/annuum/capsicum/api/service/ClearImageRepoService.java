package br.com.annuum.capsicum.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.function.Supplier;

@Service
@Slf4j
public class ClearImageRepoService {

    @Value("${annuum.image-repo.url}")
    private String imageRepoUrl;

    @Value("${annuum.image-repo.token}")
    private String imageRepoToken;

    @Autowired
    private RestTemplate restTemplate;

    public <T> T clear(final String imageKey, final Supplier<T> action) {

        try {
            return action.get();
        } catch (Exception ex) {
            delete(imageKey);
            throw ex;
        }

    }

    private void delete(final String imageKey) {
        final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", "Bearer " + imageRepoToken);

        final HttpEntity<?> request = new HttpEntity<>(headers);
        final String deleteImageKeyUrl = imageRepoUrl + imageKey;

        try {
            restTemplate.exchange(deleteImageKeyUrl, HttpMethod.DELETE, request, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
