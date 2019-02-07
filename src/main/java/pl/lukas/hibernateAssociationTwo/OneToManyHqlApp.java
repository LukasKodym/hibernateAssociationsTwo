package pl.lukas.hibernateAssociationTwo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.lukas.hibernateAssociationTwo.entity.Company;
import pl.lukas.hibernateAssociationTwo.entity.CompanyDetail;
import pl.lukas.hibernateAssociationTwo.entity.Property;

import javax.persistence.Query;
import java.util.List;

public class OneToManyHqlApp {

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

        String getCompany = "select c.name from Property p join p.company c where p.city='Sevilla'";
        String getCompanyTwo = "select c.name from Property p join p.company c join c.companyDetail cd where p.city='Barcelona' and cd.residence='Switzerland'";
        String getCompanyThree = "select c.name from Company c where size(c.properties) > 4";

        session.beginTransaction();

        Query query = session.createQuery(getCompanyThree);
        List<String> resultList = query.getResultList();
        session.getTransaction().commit();

        for (String result : resultList){
            System.out.println(result);
        }

        // zakończeine obiektu SessionFactory
        factory.close();
    }

}
