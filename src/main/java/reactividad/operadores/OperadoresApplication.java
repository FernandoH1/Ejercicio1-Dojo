package reactividad.operadores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sofka.correo.Correo;

@SpringBootApplication
public class OperadoresApplication {

    public void reactor() {
        Mono.just(new Correo("email", "@gmail.com",false))
                .subscribe();
    }

    public static Flux<Correo> flux(List<Correo> correos) {
        return Flux.fromIterable(correos);
    }

    public static void fluxmono(Flux<Correo> flux_persona) {
        flux_persona.collectList();
    }

    public static Flux<Correo> ejemploMap(Flux<Correo> flux_persona) {
        return flux_persona.map(e -> {
            e.setNombre(e.getNombre() + "agregado" + e.getDominio());
            return e;
        });
    }

    public static Flux<Correo> correoEnviado(Flux<Correo> flux_persona) {
        return flux_persona.map(e -> {
            e.cambiarEstado();
            return e;
        });
    }

    public static Flux<Correo> ejemploFiltrado(Flux<Correo> flux_persona) {
        return flux_persona.filter(e -> e.getDominio().equals("@sofka.com.co"));
    }

    public static Flux<Correo> ejemploFiltradoGmail(Flux<Correo> flux_persona) {
        return flux_persona.filter(e -> e.getDominio().equals("@gmail.com"));
    }

    public static Flux<Correo> ejemploFiltradoOutlook(Flux<Correo> flux_persona) {
        return flux_persona.filter(e -> e.getDominio().equals("@outlook.com"));
    }

    public static Flux<Correo> ejemploFiltradoHotmail(Flux<Correo> flux_persona) {
        return flux_persona.filter(e -> e.getDominio().equals("@hotmail.com"));
    }

    public static Flux<Correo> ejemploDistinto(Flux<Correo> flux_persona) {
        return flux_persona.distinct((e) -> e.getNombre());
    }


    public static void main(String[] args) {
        SpringApplication.run(OperadoresApplication.class, args);


        List<Correo> lista_personas1 = new ArrayList<Correo>();
        lista_personas1.add(new Correo("eddi.garcia", "@sofka.com.co",false));
        lista_personas1.add(new Correo("odriozolaleo1", "@gmail.com",false));
        lista_personas1.add(new Correo("fernandohernandezclivio", "@gmail.com",false));
        lista_personas1.add(new Correo("lucasmilessi98", "@gmail.com",false));
        lista_personas1.add(new Correo("henryferreira133", "@gmail.com",false));
        lista_personas1.add(new Correo("darieldesosa", "@gmail.com",false));
        lista_personas1.add(new Correo("pablo.acland", "@outlook.com",false));
        lista_personas1.add(new Correo("mathias.collazo.episcopo", "@gmail.com",false));
        lista_personas1.add(new Correo("franccesco.giordano11", "@gmail.com",false));
        lista_personas1.add(new Correo("athiaslabora", "@hotmail.com",false));
        lista_personas1.add(new Correo("matias4371", "@outlook.com",false));
        lista_personas1.add(new Correo("degonzalez6", "@hotmail.com",false));
        lista_personas1.add(new Correo("otarksk", "@gmail.com",false));
        lista_personas1.add(new Correo("martinmartincorena", "@gmail.com",false));
        lista_personas1.add(new Correo("eddi.garcia", "@sofka.com.co",false));
        lista_personas1.add(new Correo("rosanoesteban2", "@gmail.com",false));
        lista_personas1.add(new Correo("eddi.garcia", "@sofka.com.co",false));
        lista_personas1.add(new Correo("eddi.garcia", "@sofka.com.co",false));
        lista_personas1.add(new Correo("eddi.garcia", "@sofka.com.co",false));
        lista_personas1.add(new Correo("eddi.garcia", "@sofka.com.co",false));
        lista_personas1.add(new Correo("eddi.garcia", "@sofka.com.co",false));
        lista_personas1.add(new Correo("eddi.garcia", "@sofka.com.co",false));
        lista_personas1.add(new Correo("eddi.garcia", "@sofka.com.co",true));
        lista_personas1.add(new Correo("eddi.garcia", "@sofka.com.co",true));
        lista_personas1.add(new Correo("eddi.garcia", "@sofka.com.co",true));
        lista_personas1.add(new Correo("eddi.garcia", "@sofka.com.co",true));
        lista_personas1.add(new Correo("eddi.garcia", "@sofka.com.co",true));
        lista_personas1.add(new Correo("eddi.garcia", "@sofka.com.co",true));
        lista_personas1.add(new Correo("eddi.garcia", "@sofka.com.co",true));
        lista_personas1.add(new Correo("eddi.garcia", "@sofka.com.co",true));
        lista_personas1.add(new Correo("eddi.garcia", "@sofka.com.co",true));
        lista_personas1.add(new Correo("ludef15", "@gmail.com",true));
        lista_personas1.add(new Correo("federzvz", "@gmail.com",true));


        //lista_personas1.forEach(e -> System.out.println("\nLista1:\n" + e.toString()));

        Flux<Correo> flux_persona = flux(lista_personas1);

        Flux<Correo> personaFlux = ejemploMap(flux_persona);  // c.MAP: para saber si todos los correos cumple con todas las condiciones (Que cuente con el @ y el dominio)
        Flux<Correo> personaFlux1 = ejemploFiltrado(flux_persona); // b.Filtro: para saber si hay correos con dominio gmail, hotmail y outlook.
        Flux<Correo> personaFlux2 = ejemploFiltradoGmail(flux_persona);
        Flux<Correo> personaFlux3 = ejemploFiltradoHotmail(flux_persona);
        Flux<Correo> personaFlux4 = ejemploFiltradoOutlook(flux_persona);
        Flux<Correo> personaFlux5 = ejemploDistinto(flux_persona); //a.Distinct: para ver si hay correo repetidos, si hay correos repetidos eliminarlos


        Flux<Correo> personaFlux6 = correoEnviado(flux_persona);

        System.out.println("\nCANTIDAD ELEMENTOS: " + lista_personas1.size()); // d.Saber la cantidad de correos que hay, sin usar un ciclo

        System.out.println("\nResultado MAP:\n");
        personaFlux.subscribe(e -> System.out.println(e.toString()));

        System.out.println("\nResultado FILTER (@sofka.com.co) :\n");
        personaFlux1.subscribe(e -> System.out.println(e.toString()));

        System.out.println("\nCANTIDAD ELEMENTOS: " + personaFlux1.count().block().longValue());

        System.out.println("\nResultado FILTER (@gmail.com) :\n");
        personaFlux2.subscribe(e -> System.out.println(e.toString()));

        System.out.println("\nCANTIDAD ELEMENTOS: " + personaFlux2.count().block().longValue());

        System.out.println("\nResultado FILTER (@hotmail.com):\n");
        personaFlux3.subscribe(e -> System.out.println(e.toString()));

        System.out.println("\nCANTIDAD ELEMENTOS: " + personaFlux3.count().block().longValue());

        System.out.println("\nResultado FILTER (@outlook.com):\n");
        personaFlux4.subscribe(e -> System.out.println(e.toString()));

        System.out.println("\nCANTIDAD ELEMENTOS: " + personaFlux4.count().block().longValue());

        System.out.println("\nResultado DISTINT:\n");
        personaFlux5.subscribe(e -> System.out.println(e.toString()));

        System.out.println("\nResultado ENVIADO:\n");
        personaFlux6.subscribe(e -> System.out.println(e.toStringBool()));
    }

}
