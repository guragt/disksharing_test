package guragt.disksharing.repository;

import guragt.disksharing.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public User save(User user){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public User getByUsername(String username){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User u WHERE username = :username");
        query.setParameter("username", username);
        List<User> users = query.list();
        session.close();
        return users.get(0);
    }
}
