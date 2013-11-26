package repository;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.itjenny.common.CommonTest;
import com.itjenny.common.ResourceFile;
import com.itjenny.domain.Article;
import com.itjenny.repository.ArticleRepository;

public class ArticleRepositoryTest extends CommonTest {
    @Rule
    public ResourceFile css1 = new ResourceFile("/default.css");

    @Autowired
    ArticleRepository articleRepository;

    @Rule
    public ResourceFile mdSpring = new ResourceFile("/sample/spring.md");

    @Rule
    public ResourceFile mdMarkdown = new ResourceFile("/sample/markdown.md");

    @Rule
    public ResourceFile mdYobi= new ResourceFile("/sample/yobi.md");

    private PegDownProcessor pegDownProcessor = new PegDownProcessor(
            Extensions.ALL);

    private static final String USERID1 = "itjenny";
    
    @Test
    public void initMd() throws IOException {
        Article article = new Article();
        article.setTitle("spring");
        article.setContent(mdSpring.getContent());
        article.setPublished(true);
        article.setUserId(USERID1);
        article.setCss(css1.getContent());
        articleRepository.save(article);

        article.setTitle("yobi");
        article.setContent(mdYobi.getContent());
        article.setPublished(true);
        article.setUserId(USERID1);
        article.setCss(css1.getContent());
        articleRepository.save(article);

        article.setTitle("markdown");
        article.setContent(mdMarkdown.getContent());
        article.setPublished(true);
        article.setUserId(USERID1);
        article.setCss(css1.getContent());
        articleRepository.save(article);
    }
}