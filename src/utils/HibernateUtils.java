package utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by erian on 17-6-1.
 */
final public class HibernateUtils{

    private static SessionFactory factory;

    public static Session getSession() {
        return factory.openSession();
    }

    private HibernateUtils(){}

    static {
        Configuration config = new Configuration().configure();
        factory = config.buildSessionFactory();
    }

    public static <T> Integer add(T obj) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            Integer id = (Integer) session.save(obj);
            transaction.commit();
            return id;
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
            throw he;
        } finally {
            if (session != null) session.close();
        }
    }

    public static <T> void update(T obj) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.update(obj);
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            throw he;
        } finally {
            if (session != null) session.close();
        }
    }

    public static <T> void delete(T obj) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.delete(obj);
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            throw he;
        } finally {
            if (session != null) session.close();
        }
    }

    public static List list(String table, Class<?> clazz, boolean isPrint) {
        Session session = null;
        Transaction transaction = null;
        if (table.equals(clazz.getSimpleName().toLowerCase()) == false)
            return new ArrayList();
        // If table name is not equal to class name, then it return an empty list

        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            List l = session.createQuery("FROM "+ clazz.getSimpleName()
            + " ORDER BY id").list();

            // Output the list the command line
            if (isPrint) {
                // Get private fields
                ArrayList<Field> privateFields = new ArrayList<>();
                for (Field field :
                        clazz.getDeclaredFields()) {
                    if (Modifier.isPrivate(field.getModifiers()))
                        privateFields.add(field);
                }

                for (Object o : l) {
                    privateFields.forEach(privateField -> {
                        String methodName = new StringBuilder()
                                .append("get")
                                .append(capitalizeFirst(privateField.getName()))
                                .toString();
                        try {
                            // Because all getter function is public
                            Method getter = clazz.getMethod(methodName, null);

                            System.out.println(capitalizeFirst(privateField.getName()) +
                                    ": " + getter.invoke(o, null));
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });

                    System.out.println();
                }

                printDelimeter(30);
            }

            transaction.commit();
            return l;
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            throw he;
        } finally {
            if (session != null) session.close();
        }
    }
    
    private static String capitalizeFirst(String str) {
        char[] strChars = str.toCharArray();
        strChars[0] = Character.toUpperCase(strChars[0]);
        return new String(strChars);
    }

    public static void printDelimeter(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}
