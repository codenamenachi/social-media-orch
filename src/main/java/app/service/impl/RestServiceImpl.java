package app.service.impl;

import app.exception.ServiceException;
import app.model.User;
import app.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class RestServiceImpl implements RestService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${db.service.url}")
    private String dbServiceUrl;

    @Value("${db.get.friends.endpoint}")
    private String getFriendsEndpoint;

    @Value("${db.get.user.endpoint}")
    private String getUserDetailsEndpoint;


    @Override
    public User getUserDetails(String userId) throws ServiceException {
        try {
            String getUserDetailsUrl = dbServiceUrl+getUserDetailsEndpoint;
            getUserDetailsUrl = getUserDetailsUrl.replace("{userId}",userId);

            ResponseEntity<User> dbServiceResponse = restTemplate.getForEntity(getUserDetailsUrl, User.class);

            if(dbServiceResponse.getStatusCode().isError() || dbServiceResponse.getBody() == null)
                throw new ServiceException(dbServiceResponse.getStatusCode(), "Error occurred trying to call "+getUserDetailsUrl);

            return Objects.requireNonNull(dbServiceResponse.getBody());
        } catch (RestClientException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<User> getFriendsList(String userId) throws ServiceException {

        try {
            String getFriendsListUrl = dbServiceUrl+getFriendsEndpoint;
            getFriendsListUrl = getFriendsListUrl.replace("{userId}",userId);

            ResponseEntity<User[]> dbServiceResponse = restTemplate.getForEntity(getFriendsListUrl, User[].class);

            if(dbServiceResponse.getStatusCode().isError() || dbServiceResponse.getBody() == null)
                throw new ServiceException(dbServiceResponse.getStatusCode(), "Error occurred trying to call "+getFriendsListUrl);
            return Arrays.asList(Objects.requireNonNull(dbServiceResponse.getBody()));
        } catch (RestClientException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
