package core.ics.mstravelai.services.impl;

import core.ics.mstravelai.services.TravelServices;
import org.springframework.stereotype.Service;

@Service
public class TravelServiceImpl implements TravelServices {

    @Override
    public String itinerary(String destination, String startDate, String endDate) {
        return "Viajando para "+destination+" nos dias "+startDate+" até "+endDate+" sugira um roteiro de viagem";
    }

    @Override
    public String weather(String startDate, String destination) {
        return "Viajo para "+destination+" no dia "+startDate+" baseado em dados históricos como é o clima nesse mês?";
    }

    @Override
    public String violence(String destination) {
        return "Estou viajando para "+destination+", como é a segurança desta cidade, devo me preocupar?";
    }

    @Override
    public String bestWay(String origin, String destination) {
        return "Eu moro em "+origin+" qual a forma mais eficiente de chegar em "+destination;
    }
}
