package ch.bbw.jh.benutzerverwaltung;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * The type Login attempt service.
 */
@Service
public class LoginAttemptService {

    private final int MAX_ATTEMPT = 10;
    private LoadingCache<String, Integer> attemptsCache;

    /**
     * Instantiates a new Login attempt service.
     */
    public LoginAttemptService() {
        super();
        attemptsCache = CacheBuilder.newBuilder().
                expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }

    /**
     * Login succeeded.
     *
     * @param key the key
     */
    public void loginSucceeded(String key) {
        attemptsCache.invalidate(key);
    }

    /**
     * Login failed.
     *
     * @param key the key
     */
    public void loginFailed(String key) {
        int attempts = 0;
        try {
            attempts = attemptsCache.get(key);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(key, attempts);
    }

    /**
     * Is blocked boolean.
     *
     * @param key the key
     * @return the boolean
     */
    public boolean isBlocked(String key) {
        try {
            return attemptsCache.get(key) >= MAX_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }
}
