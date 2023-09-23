package SD.UrlShortener;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public interface UrlShorten {

    @GetMapping("/{url}")
    public String getUrl(String url);

    @PostMapping("/shortUrl")
    public String shortenUrl(@RequestBody String url);

}
