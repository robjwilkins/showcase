package com.wilkins.showcase.controller;

import com.wilkins.showcase.service.WordChainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/words/chains")
public class WordChainController {

    private static final Logger log = LoggerFactory.getLogger(WordChainController.class);
    private final WordChainService wordChainService;

    public WordChainController(WordChainService wordChainService) {
        this.wordChainService = wordChainService;
    }

    @GetMapping
    public ResponseEntity<JsonWordChain> getWordChain(@RequestParam(name = "source", required = false, defaultValue = "cat") String source,
                                                     @RequestParam(name = "target", required = false, defaultValue = "dog") String target) {
        if (source.length() != target.length()) {
            return badRequest().build();
        }
        log.info("source: {}, target: {}", source, target);
        return ok(JsonWordChain.of(List.copyOf(wordChainService.chainWords(source, target))));
    }
}
