package app.service;

import app.exception.ServiceException;
import app.model.User;

import java.util.List;

public interface RestService {

    User getUserDetails(String userId) throws ServiceException;

    List<User> getFriendsList(String userId) throws ServiceException;
}
