package pl.lukas.hibernateAssociationTwo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.lukas.hibernateAssociationTwo.entity.Company;
import pl.lukas.hibernateAssociationTwo.entity.CompanyDetail;
import pl.lukas.hibernateAssociationTwo.entity.Property;

public class OneToManyGetApp {

    public static void main(String[] args) {

        // tworzenie obiektu Configuration
        Configuration conf = new Configuration();
        // wczytanie pliku konfiguracyjnego
        conf.configure("hibernate.cfg.xml");
        // wczytanie adnotacje klasy
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        conf.addAnnotatedClass(Property.class);
        // tworzenie obiektu SessionFactory
        SessionFactory factory = conf.buildSessionFactory();
        // pobieranie sesji
        Session session = factory.getCurrentSession();

        String getCompany = "select c from Company c where c.name='Strefakursow'";

        session.beginTransaction();

        Query query = session.createQuery(getCompany);

        Company company = (Company) query.getSingleResult();

        System.out.println(company);

        for (Property property : company.getProperties()) {

            System.out.println(property);
        }

        session.getTransaction().commit();


        // zakończeine obiektu SessionFactory
        factory.close();
    }
}
