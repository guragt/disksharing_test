package guragt.disksharing.repository;

import guragt.disksharing.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TakenItemRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public int takeDisk(Long diskId, User user){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE TakenItem SET holder = :user" +
                " WHERE disk.id = :diskId AND holder is null");
        query.setParameter("user", user);
        query.setParameter("diskId", diskId);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public int returnDisk(Long diskId, User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE TakenItem SET holder = null" +
                " WHERE disk.id = :diskId AND holder.id = :userId");
        query.setParameter("userId", user.getId());
        query.setParameter("diskId", diskId);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result;
    }

}
