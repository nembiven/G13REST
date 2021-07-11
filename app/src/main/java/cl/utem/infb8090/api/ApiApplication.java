package cl.utem.infb8090.api;

import cl.utem.infb8090.api.Webscrapping.Storage;
import cl.utem.infb8090.api.persistence.model.Sismo;
import cl.utem.infb8090.api.persistence.repository.SismoRepository;
import cl.utem.infb8090.api.rest.filter.SimpleCorsFilter;
import io.swagger.annotations.Api;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class ApiApplication {
    
    private static ConfigurableApplicationContext context;
    public static void restart() throws InterruptedException
    {
       ApplicationArguments args;
       args = context.getBean(ApplicationArguments.class);
       Thread thread = new Thread(()->{
           context.close();
           context = SpringApplication.run(ApiApplication.class, args.getSourceArgs());
       });
       thread.setDaemon(false);
        try {
            Thread.sleep(18000 * 1000); // cada 5 horas
         } catch (Exception e) {
            System.out.println(e);
         }
       thread.start();
       
       restart();
    }
    
        
@Autowired
	SismoRepository repository;
    @Bean
    public FilterRegistrationBean<SimpleCorsFilter> simpleCorsFilter() {
        FilterRegistrationBean<SimpleCorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SimpleCorsFilter());
        registrationBean.addUrlPatterns("/G13");
        return registrationBean;
    }
    @EventListener(ApplicationReadyEvent.class)
public void Scrapping() throws IOException {
    Document doc;
        String url = "http://www.sismologia.cl/links/ultimos_sismos.html";
        doc = Jsoup.connect(url).timeout(6000).get();
		Element body = doc.getElementsByTag("tbody").first();
                Storage [] X = new Storage[15];
                Storage aux;
                int cont;
                int cont2=0;
                for(Element e : body.getElementsByTag("tr"))
		{
                    cont=0;
                    
                    String [] A = new String[8];
                    List<Element> attributes = e.getElementsByTag("td");
                    for( Element k : attributes)
                    {
                          A[cont]=k.text();
                          cont++;  
 
                    }
                    if(!repository.existsByFecha(A[0]))
                    {
                     if(A[0]!=null)
                        {
                          aux = new Storage(A[0],A[1],A[2],A[3],A[4],A[5],A[6],A[7]);
                          X[cont2]= aux;
                          cont2++;
                          System.out.println("NUEVO SISMO REGISTRADO CON FECHA :"+A[0]);
                        }   
                    }
                }
                System.out.println("En 5 horas se realizará un reinicio para ver si hay mas datos");
                for(int i=cont2-1;i>-1;i--)
                    {
                        repository.save(new Sismo(X[i].getA1(),X[i].getA2(),X[i].getA3(),X[i].getA4(),X[i].getA5(),X[i].getA6(),X[i].getA7(),X[i].getA8()));
                    }

}

    public static void main(String[] args) throws InterruptedException {
        
        context = SpringApplication.run(ApiApplication.class, args);
        restart();
        
    }
    
    
}


