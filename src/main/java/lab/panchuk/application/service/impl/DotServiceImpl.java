package lab.panchuk.application.service.impl;

import lab.panchuk.application.entity.Dot;
import lab.panchuk.application.repository.DotRepository;
import lab.panchuk.application.service.DotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
