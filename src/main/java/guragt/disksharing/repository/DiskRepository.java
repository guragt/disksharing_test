package guragt.disksharing.repository;

import guragt.disksharing.model.Disk;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DiskRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Disk> getFreeDisk(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Disk d WHERE takenItem.holder is null ");
        return query.list();
    }

    public List<Disk> getDiskTakenByUser(Long userId){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Disk d WHERE takenItem.holder.id = :userId ");
        query.setParameter("userId", userId);
        return query.list();
    }

    public List<Disk> getDiskTakenFromUser(Long userId){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Disk d WHERE owner.id = :userId AND takenItem.holder is not null ");
        query.setParameter("userId", userId);
        return query.list();
    }

    public Disk save(Disk disk) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(disk);
        session.getTransaction().commit();
        session.close();
        return disk;
    }
}
