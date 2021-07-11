package cl.utem.infb8090.api.persistence.manager;
import cl.utem.infb8090.api.persistence.model.Sismo;
import cl.utem.infb8090.api.persistence.repository.SismoRepository;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SismoManager implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private transient SismoRepository sismoRepository;
    /**
     * 
     * @return Listado de sismos
     */
    public List<Sismo> getSismos() {
        return sismoRepository.findAll();
    }
}
