package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.PictureRequest;
import br.com.annuum.capsicum.api.domain.enums.PictureRelevance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClearImageRepoServiceTest {

    @InjectMocks
    private ClearImageRepoService service;

    @Mock
    private RestTemplate restTemplate;

    @Captor
    private ArgumentCaptor<HttpEntity<?>> captor;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(service, "imageRepoUrl", "http://foo.bar/");
        ReflectionTestUtils.setField(service, "imageRepoToken", "2OKjz7najk#");
    }

    @Test
    public void shouldSuccessfullyDeleteImageWhenErrorThrowingOriginalException() {
        final String message = randomAlphabetic(50);
        final String imageKey = randomAlphanumeric(20);
        final PictureRequest profilePictureRequest = new PictureRequest()
            .setPictureRelevance(PictureRelevance.PRIMARY)
            .setProfilePictureKey(imageKey);
        final PictureRequest backgroundPictureRequest = new PictureRequest()
            .setPictureRelevance(PictureRelevance.SECONDARY)
            .setProfilePictureKey(imageKey);
        final List<PictureRequest> pictureRequests = Arrays.asList(profilePictureRequest, backgroundPictureRequest);
        final String expectedUrl = "http://foo.bar/" + imageKey;
        final String expectedHeader = "Bearer 2OKjz7najk#";
        final Supplier action = () -> {
            throw new RuntimeException(message);
        };

        try {
            service.clear(pictureRequests, action);
        } catch (Exception ex) {
            assertEquals(message, ex.getMessage());
        }

        verify(restTemplate, times(2)).exchange(eq(expectedUrl), eq(HttpMethod.DELETE), captor.capture(), eq(String.class));
        assertEquals(expectedHeader, captor.getValue().getHeaders().get("Authorization").get(0));
    }

    @Test
    public void shouldIgnoreErrorDeletingImageWhenErrorThrowingOriginalException() {
        final String message = randomAlphabetic(50);
        final String imageKey = randomAlphanumeric(20);
        final PictureRequest profilePictureRequest = new PictureRequest()
            .setPictureRelevance(PictureRelevance.PRIMARY)
            .setProfilePictureKey(imageKey);
        final PictureRequest backgroundPictureRequest = new PictureRequest()
            .setPictureRelevance(PictureRelevance.SECONDARY)
            .setProfilePictureKey(imageKey);
        final List<PictureRequest> pictureRequests = Arrays.asList(profilePictureRequest, backgroundPictureRequest);
        final String expectedUrl = "http://foo.bar/" + imageKey;
        final String expectedHeader = "Bearer 2OKjz7najk#";
        final Supplier action = () -> {
            throw new RuntimeException(message);
        };

        doThrow(RuntimeException.class).when(restTemplate).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class));

        try {
            service.clear(pictureRequests, action);
        } catch (Exception ex) {
            assertEquals(message, ex.getMessage());
        }

        verify(restTemplate, times(2)).exchange(eq(expectedUrl), eq(HttpMethod.DELETE), captor.capture(), eq(String.class));
        assertEquals(expectedHeader, captor.getValue().getHeaders().get("Authorization").get(0));
    }

    @Test
    public void shouldDoNothingWhenNoErrorsOccursRunningAction() {
        final String imageKey = randomAlphanumeric(20);
        final PictureRequest pictureRequest = new PictureRequest()
            .setPictureRelevance(PictureRelevance.PRIMARY)
            .setProfilePictureKey(imageKey);
        final List<PictureRequest> pictureRequests = Collections.singletonList(pictureRequest);
        final String suppliedValue = randomAlphanumeric(20);
        final Supplier<String> action = () -> suppliedValue;

        final String actualValue = service.clear(pictureRequests, action);
        assertEquals(suppliedValue, actualValue);
        verifyNoInteractions(restTemplate);
    }
}
