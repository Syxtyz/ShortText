package service;

import repository.SnippetRepository;
import model.Snippet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SnippetService {

    private final Map<String, String> cache = new ConcurrentHashMap<>();
    private final SnippetRepository repo = new SnippetRepository();

    public SnippetService() {
        refresh();
    }

    public String get(String trigger) {
        return cache.get(trigger);
    }

    public void refresh() {
        cache.clear();
        for (Snippet s : repo.findAll()) {
            cache.put(s.getTrigger(), s.getContent());
        }
    }
}
