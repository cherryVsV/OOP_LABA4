package com.donnu.oop_laba_4;

import com.donnu.oop_laba_4.Entities.Doctor;
import com.donnu.oop_laba_4.Entities.History;
import com.donnu.oop_laba_4.Entities.Owner;
import com.donnu.oop_laba_4.Entities.Pet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class OopLaba4Application {

    public static void main(String[] args) {
        SpringApplication.run(OopLaba4Application.class, args);
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Pet.class)
                .addAnnotatedClass(Doctor.class)
                .addAnnotatedClass(History.class)
                .addAnnotatedClass(Owner.class)
                .buildSessionFactory();
        Session session = null;
        try{
            session = factory.getCurrentSession();
            Owner owner1 = new Owner("Трипольская Валерия Сергеевна", "0994753635", "Донецк, улица Ходаковского 58");
            Owner owner2 = new Owner("Трипольская Маргарита Витальевна", "0994753688", "Горловка, улица Ленина 58");
            Pet pet1 = new Pet("Персик", 2, "Женский", "Кошка");
            Pet pet2 = new Pet("Моняша", 5, "Женский", "Кошка");
            Doctor doctor1 = new Doctor("Николаев Андрей Андреевич", "Ветеринар", 10);
            Doctor doctor2 = new Doctor("Николаева Ангелина Васильевна", "Мед.сестра", 8);
            History history1 = new History(new Date(), "Лишай", 1000, "Мазь антибактериальная");
            History history2 = new History(new Date(), "Микоз", 1000, "Антибиотики");
            owner1.setPets(pet1);
           // pet1.setOwners(owner1);
            owner2.setPets(pet2);
            history1.setDoctor(doctor1);
            history2.setDoctor(doctor2);
            history1.setPet(pet1);
            history2.setPet(pet2);

            session.beginTransaction();

            session.save(owner1);
            session.save(owner2);
            session.save(pet1);
            session.save(pet2);
            session.save(history1);
            session.save(history2);

            Pet db_pet1 = session.get(Pet.class, 1);
            System.out.println(db_pet1.toString());

            Pet db_pet2 = session.get(Pet.class, 2);
            System.out.println(db_pet2.toString());

            Doctor db_doctor1 = session.get(Doctor.class, 1);
            System.out.println(db_doctor1.toString());

            Doctor db_doctor2 = session.get(Doctor.class, 2);
            System.out.println(db_doctor2.toString());

            Owner db_owner1 = session.get(Owner.class, 1);
            System.out.println(db_owner1.toString());

            Owner db_owner2 = session.get(Owner.class, 2);
            System.out.println(db_owner2.toString());

            History db_history1 = session.get(History.class, 1);
            System.out.println(db_history1.toString());

            History db_history2 = session.get(History.class, 2);
            System.out.println(db_history2.toString());

            session.getTransaction().commit();

        }
        catch(Exception e){
            if(session !=null){
                session.close();
            }
            factory.close();
        }
    }

}
