
package service;

import dao.ApplicationDAO;
import model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationDAO applicationDAO;

    public boolean apply(Application application) {
        return applicationDAO.apply(application);
    }
}
