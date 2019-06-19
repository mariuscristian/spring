package spring.training.resurse;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Proprietati implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Proprietati.class);
    }

    @Value("${parola.critica:12345678}")
    String parola;


    @Bean
    public ClasaVeche clasaVeche() {
        return ClasaVeche.getInstance();
    }

    @Bean
    public AltaClasa altaClasa() {
        System.out.println("In altaClasa()");
        return new AltaClasa(clasaVeche(), parola);
    }
    @Bean
    public FinalaMica mica() {
        System.out.println("Instantiez finala mic");
        return new FinalaMica(altaClasa());
    }
    @Bean
    public FinalaMica finalaMica2() {
        System.out.println("Instantiez finala mic");
        return new FinalaMica(altaClasa());
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Parola este: " +parola);
    }
}

//class CeFaceSpring extends Proprietati {
//    @Override
//    public AltaClasa altaClasa() {
//        if (amdeja creeata AltaClasa) {
//            return din galeata;
//        } else {
//        AltaClasa instanta = super.altaClasa();
//        instanta.hello();
//        pune in galeata
//        return instanta;
//    }
//}

//@Component
@RequiredArgsConstructor
class AltaClasa {
    private final ClasaVeche veche;
    private final String parola;
    @PostConstruct
    public void hello() {
        System.out.println("Post Construct in AltaClasa");
        veche.metoda();
    }
}

//@Component
@RequiredArgsConstructor
class FinalaMica {
    private final AltaClasa altaClasa;
    @PostConstruct
    public void hellomica() {
        System.out.println("Finala mica");
    }
}


// n-ai access s-o schimbi
class ClasaVeche {
    private static ClasaVeche INSTANCE;
    private ClasaVeche() {
        //chestii
    }
    public static ClasaVeche getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClasaVeche();
        }
        return INSTANCE;
    }

    public void metoda() {
        System.out.println("Hello worlds");
    }
}