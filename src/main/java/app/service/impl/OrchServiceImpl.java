package app.service.impl;

import app.exception.ServiceException;
import app.model.User;
import app.service.OrchService;
import app.service.RestService;
import app.util.OrchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrchServiceImpl implements OrchService {

    @Autowired
    private RestService restService;

    public User getUserDetails(String userId) throws ServiceException {
        OrchUtil.validateRequest(userId);
        return restService.getUserDetails(userId);
    }

    public List<User> getFriendsList(String userId) throws ServiceException {

        OrchUtil.validateRequest(userId);
        return restService.getFriendsList(userId);
    }
}
