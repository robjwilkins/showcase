package com.wilkins.showcase.service;

import java.util.List;

public interface WordChainService {
    List<String> chainWords(String sourceWord, String targetWord);
}
