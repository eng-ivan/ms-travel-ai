package core.ics.mstravelai.rest;


import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import core.ics.mstravelai.config.SetupConfig;
import core.ics.mstravelai.domain.*;
import core.ics.mstravelai.services.impl.TravelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TravelRestController {

    @Autowired
    TravelServiceImpl travelService;

    @GetMapping(path = "/itinerary")
    public ResponseEntity<List<CompletionChoice>> itinerary(@RequestBody Travel travel){
        OpenAiService service = new OpenAiService(SetupConfig.OPENAI_API_KEY);

        Itinerary itinerary = Itinerary
                .builder()
                .startDate(travel.getStartDate())
                .endDate(travel.getEndDate())
                .destination(travel.getDestination())
                .build();

        CompletionRequest request = CompletionRequest.builder()
                .model(SetupConfig.MODEL)
                .prompt(travelService.itinerary(itinerary.getDestination(), itinerary.getStartDate(), itinerary.getEndDate()))
                .maxTokens(SetupConfig.MAX_TOKENS)
                .temperature(SetupConfig.TEMPERATURE)
                .topP(SetupConfig.TOP_P)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(service.createCompletion(request).getChoices());
    }

    @GetMapping(path = "/weather")
    public ResponseEntity<List<CompletionChoice>> weather(@RequestBody Travel travel){
        OpenAiService service = new OpenAiService(SetupConfig.OPENAI_API_KEY);

        Weather weather = Weather.builder()
                .startDate(travel.getStartDate())
                .destination(travel.getDestination())
                .build();

        CompletionRequest request = CompletionRequest.builder()
                .model(SetupConfig.MODEL)
                .prompt(travelService.weather(weather.getStartDate(), weather.getDestination()))
                .maxTokens(SetupConfig.MAX_TOKENS)
                .temperature(SetupConfig.TEMPERATURE)
                .topP(SetupConfig.TOP_P)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(service.createCompletion(request).getChoices());
    }

    @GetMapping(path = "/violence")
    public ResponseEntity<List<CompletionChoice>> violence(@RequestBody Travel travel){
        OpenAiService service = new OpenAiService(SetupConfig.OPENAI_API_KEY);

        Violence violence = Violence.builder().destination(travel.getDestination()).build();

        CompletionRequest request = CompletionRequest.builder()
                .model(SetupConfig.MODEL)
                .prompt(travelService.violence(violence.getDestination()))
                .maxTokens(SetupConfig.MAX_TOKENS)
                .temperature(SetupConfig.TEMPERATURE)
                .topP(SetupConfig.TOP_P)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(service.createCompletion(request).getChoices());
    }

    @GetMapping(path = "/bestway")
    public ResponseEntity<List<CompletionChoice>> bestWay(@RequestBody Travel travel){
        OpenAiService service = new OpenAiService(SetupConfig.OPENAI_API_KEY);

        BestWay bestWay = BestWay.builder().origin(travel.getOrigin()).destination(travel.getDestination()).build();
        CompletionRequest request = CompletionRequest.builder()
                .model(SetupConfig.MODEL)
                .prompt(travelService.bestWay(bestWay.getOrigin(), bestWay.getDestination()))
                .maxTokens(SetupConfig.MAX_TOKENS)
                .temperature(SetupConfig.TEMPERATURE)
                .topP(SetupConfig.TOP_P)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(service.createCompletion(request).getChoices());
    }
}
