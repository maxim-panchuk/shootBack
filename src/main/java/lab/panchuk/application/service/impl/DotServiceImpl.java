package lab.panchuk.application.service.impl;

import lab.panchuk.application.entity.Dot;
import lab.panchuk.application.repository.DotRepository;
import lab.panchuk.application.service.DotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import java.util.ArrayList;
import java.util.List;

@Component
public class DotServiceImpl implements DotService {

    private final DotRepository dotRepository;

    @Autowired
    public DotServiceImpl (DotRepository dotRepository) {
        this.dotRepository = dotRepository;
    }

    @Override
    public List<Dot> getAll() {
        return dotRepository.findAll();
    }

    @Override
    public void add(Dot dot) {
        dotRepository.save(dot);
    }

    public List<Dot> getByUsername (long id_user) {
        return dotRepository.findAllByUser_id(id_user);
    }
    public List<Dot> delByUser(long id_user) {
        try {
            dotRepository.deleteAllById_user(id_user);
        } catch (Exception e) {
            System.out.println("");
        }

        return new ArrayList<Dot>();
    }
}
