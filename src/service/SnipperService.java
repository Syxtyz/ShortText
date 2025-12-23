/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import repository.SnippetRepository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Admin
 */
public class SnipperService {
    private final Map<String, String> cache = new ConcurrentHashMap<>();
    public SnipperService() {
        cache.putAll(new SnippetRepository().loadAll());
    }
    
    public String get(String trigger) {
        return cache.get(trigger);
    }
    
    public void refresh() {
        cache.clear();
        cache.putAll(new SnippetRepository().loadAll());
    }
}
