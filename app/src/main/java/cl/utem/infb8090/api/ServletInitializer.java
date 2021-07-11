package cl.utem.infb8090.api;

import cl.utem.infb8090.api.Webscrapping.Storage;
import cl.utem.infb8090.api.persistence.model.Sismo;
import cl.utem.infb8090.api.persistence.repository.SismoRepository;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer  {
@Autowired
	static SismoRepository repository;
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApiApplication.class);
    }
}
