package projet.util;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static  ServiceRegistry serviceRegistry;
	private static Session session = null ;

    private static SessionFactory buildSessionFactory() {
        try {
            // Créer la configuration en chargeant le fichier hibernate.cfg.xml
            Configuration configuration = new Configuration().configure("config/hibernate.cfg.xml");
            
            // Créer un registre de services avec la configuration
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            
            // Créer une SessionFactory avec la configuration et le registre de services
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            // Gestion des erreurs de création de la SessionFactory, telles que des erreurs de configuration ou de connexion à la base de données.
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static Session openSession() {
    	return sessionFactory.openSession();
    }
    
    public  Session getCurrentSession() {
    	return sessionFactory.getCurrentSession();
    }

    public static void shutdown() {
        // Ferme la SessionFactory, libère toutes les ressources.
        getSessionFactory().close();
    }  }
