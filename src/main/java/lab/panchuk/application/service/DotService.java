package lab.panchuk.application.service;

import lab.panchuk.application.entity.Dot;

import java.util.List;

public interface DotService {
    List<Dot> getAll();
    void add(Dot dot);
}
