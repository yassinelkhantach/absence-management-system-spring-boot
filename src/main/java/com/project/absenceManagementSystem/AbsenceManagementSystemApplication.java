package com.project.absenceManagementSystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.project.absenceManagementSystem.entities.Account;
import com.project.absenceManagementSystem.entities.Element;
import com.project.absenceManagementSystem.entities.Filiere;
import com.project.absenceManagementSystem.entities.Level;
import com.project.absenceManagementSystem.entities.Module;
import com.project.absenceManagementSystem.entities.SessionType;
import com.project.absenceManagementSystem.entities.Student;
import com.project.absenceManagementSystem.entities.Teacher;
import com.project.absenceManagementSystem.entities.User;
import com.project.absenceManagementSystem.repositories.AccountRepository;
import com.project.absenceManagementSystem.repositories.FiliereRepository;
import com.project.absenceManagementSystem.repositories.ModuleRepository;
import com.project.absenceManagementSystem.repositories.SessionTypeRepository;
import com.project.absenceManagementSystem.repositories.UserRepository;

@SpringBootApplication
@Transactional
public class AbsenceManagementSystemApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;

	
	@Autowired
	private FiliereRepository filiereRepository;
	
	@Autowired
	private ModuleRepository moduleRepository;
	
	@Autowired
	private SessionTypeRepository sessionTypeRepository;

	
	public static void main(String[] args) {
		SpringApplication.run(AbsenceManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*User s1  = new Student("Yassine", "EL KHANTACH", "zaza", "efds", "0637844314", "yassine.elkhantach@gmail.com", null, "S13414265", "z636484", "img", new Date());
		Account acc = new Account("yassine.2000", "02082", "student", true, false, new Date(), null, null);
		acc.setUser(s1);
		s1.setAccount(acc);
		
		User s2  = new Teacher("Tarik", "Boudaa", "Tar", "bda", "0637844314", "tarik.boudaa@gmail.com", null, "D598951");
		Account acc2 = new Account("tarik.1982", "12956", "teacher", true, false, new Date(), null, null);
		acc2.setUser(s2);
		s2.setAccount(acc2);

		
		Filiere gi = new Filiere("Génie Informatique", "GI", new Date(18092024), new Date(20092024), null);
		List<Level> levels = new ArrayList<>();
		levels.add(new Level("Génie Informatique 1", "GI1",gi,null));
		levels.add(new Level("Génie Informatique 2", "GI2",gi,null));
		levels.add(new Level("Génie Informatique 3", "GI3",gi,null));
		gi.setLevels(levels);
		
		Module m = new Module("M01GI2", "Frameworks JEE et .NET", levels.get(1), null);
		List<Element> e = new ArrayList<>();
		e.add(new Element("Frameworks Java/JEE",m));
		m.setElements(e);
		
		List<SessionType> sessions = new ArrayList<>();
		sessions.add(new SessionType("Cours"));
		sessions.add(new SessionType("TD"));
		sessions.add(new SessionType("TP"));
		sessions.add(new SessionType("Exam"));
				
		
		sessionTypeRepository.saveAll(sessions);
		moduleRepository.save(m);
		userRepository.save(s1);
		userRepository.save(s2);
		filiereRepository.save(gi);
		
		
		accountRepository.delete(s1.getAccount());
		userRepository.delete(s1);

		for(User user : userRepository.findAll()) {
			System.out.println(user);
		}
*/
		
	}

}
