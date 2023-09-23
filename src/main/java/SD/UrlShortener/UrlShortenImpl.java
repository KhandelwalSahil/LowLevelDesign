package SD.UrlShortener;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/urlShortener")
public class UrlShortenImpl implements UrlShorten {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String getUrl(String url) {
        String longUrl = redisTemplate.opsForValue().get(url);
        System.out.println("URL found: " + longUrl);

        if (StringUtils.isEmpty(longUrl)) {
            throw new RuntimeException("no long url found for given url: " + url);
        }

        return longUrl;
    }

    @Override
    public String shortenUrl(String url) {
        String[] schemes = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(schemes);

        if (urlValidator.isValid(url)) {
            /*  murmur3_32 -> Guava library by Google
                32-bit
             */
            String shortUrl = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
            System.out.println("Short url generated: " + shortUrl);
            // For local testing purpose, HashMap can be used
            redisTemplate.opsForValue().set(shortUrl, url);
            return shortUrl;
        }
        throw  new RuntimeException("Url invalid: " + url);
    }
}
