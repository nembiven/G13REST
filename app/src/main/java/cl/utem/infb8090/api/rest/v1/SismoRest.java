package cl.utem.infb8090.api.rest.v1;

import cl.utem.infb8090.api.Webscrapping.Storage;
import cl.utem.infb8090.api.exception.CpydException;
import cl.utem.infb8090.api.persistence.manager.SismoManager;
import cl.utem.infb8090.api.persistence.model.Sismo;
import cl.utem.infb8090.api.persistence.repository.SismoRepository;
import cl.utem.infb8090.api.rest.vo.ErrorVO;
import cl.utem.infb8090.api.rest.vo.SismoVO;
import cl.utem.infb8090.api.utils.IPUtils;
import cl.utem.infb8090.api.utils.JwtUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.InetAddressValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import java.util.List;

import java.io.IOException;

import org.jsoup.Jsoup;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


@RestController
@RequestMapping(value = "/earthquakes", consumes = {"application/json;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
public class SismoRest implements Serializable {
    
    

    private static final long serialVersionUID = 1L;

    @Autowired
    private transient SismoManager sismoManager;

    @Autowired
    private transient HttpServletRequest httpRequest;

    
    private static final Logger LOGGER = LoggerFactory.getLogger(SismoRest.class);
    @Autowired
	SismoRepository repository;
    @ApiOperation(value = "Permite obtener el listado de sismos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Respuesta fue exitosa", response = SismoVO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Acceso no autorizado", response = ErrorVO.class),
        @ApiResponse(code = 403, message = "No tiene permisos", response = ErrorVO.class),
        @ApiResponse(code = 404, message = "No se ha encontrado la información solicitada", response = ErrorVO.class),
        @ApiResponse(code = 412, message = "Falló alguna precondición", response = ErrorVO.class)
    })
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/", consumes = {"*/*"}, produces = {"application/json;charset=utf-8"})
    public ResponseEntity getAll(@ApiParam(name = "Authentication", value = "Cabecera de autenticación", required = true, example = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
            @RequestHeader(name = "Authentication", required = true) String bearer) throws IOException {
        if (StringUtils.isBlank(bearer)) {
            LOGGER.error("Sin token de autenticación");
            throw new CpydException(401, "Credenciales inválidas");
        }
        
        final String ip = IPUtils.getClientIpAddress(httpRequest);
        if (!InetAddressValidator.getInstance().isValid(ip)) {
            LOGGER.error("IP NO válida, posible ataque");
            throw new CpydException(403, "No tiene permiso para acceder a este recurso");
        }

        final String jwt = StringUtils.trimToEmpty(StringUtils.removeStartIgnoreCase(bearer, "bearer"));
        boolean valid = JwtUtils.isValid(ip, jwt);
        if (!valid) {
            LOGGER.error("El token de autenticación no es válido");
            throw new CpydException(401, "Credenciales inválidas");
        }
        /*
        
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
                          System.out.println("Aun no existe esta fecha :"+A[0]);
                        }   
                    }

                
                }
                for(int i=cont2-1;i>-1;i--)
                    {
                        repository.save(new Sismo(X[i].getA1(),X[i].getA2(),X[i].getA3(),X[i].getA4(),X[i].getA5(),X[i].getA6(),X[i].getA7(),X[i].getA8()));
                    }
                */

       
        
        List<Sismo> sismos = sismoManager.getSismos();
        if (CollectionUtils.isEmpty(sismos)) {
            LOGGER.error("Lista de sismos vacía");
            throw new CpydException(404, "No se han encontrado sismos");
        }

        
        
        List<SismoVO> resultList = new ArrayList<>();
        for (Sismo sismo : sismos) {
            resultList.add(new SismoVO(sismo));
        }
        sismos.clear();

        return ResponseEntity.ok(resultList);
    }
    @SneakyThrows
    @PostConstruct
    public void getall()  {
        
    }

    
}
