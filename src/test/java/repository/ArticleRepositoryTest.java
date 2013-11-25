package repository;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
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

    private static final String TITLE1 = "QA";
    private static final String TITLE2 = "private";
    private static final String TITLE3 = "table";
    private static final String CONTENT1 = "Skill Set0\n---------\n - Spring : JAVA Framework\n - Spring : JAVA Framework23\n\nSkill Set\n---------\n - Spring : Java Framework Java2 Framework\n\nquiz\n--------\nquestion\n> answer\n\nSkill Set2\n---------\n - Spring : Java Framework\n\nquiz\n--------\nquestion\n> answer2\n\nSkill Set3\n---------\n - Spring : Java Framework\n\nquiz\n--------\nquestion\n> answer3\n\nSkill Set4\n---------\n - Spring : Java Framework\n\nquiz\n--------\nquestion\n> answer4\n\nSkill Set5\n---------\n - Spring : Java Framework\n\nquiz\n--------\nquestion\n> answer5\n\n";
    private static final String CONTENT2 = "private";
    private static final String CONTENT3 = "| Tables        | Are           | Cool  | Test |\n| ------------- |:------------- |:-------------:| -----:|\n| col 3 is      | left-aligned | right-aligned | $1600 |\n| col 2 is      | left | centered      |   $12 |\n| zebra stripes | test | are neat      |    $1 |";
    private static final String USERID1 = "itjenny";

    @Test
    public void setInit() throws IOException {
        // Given
        Article article = new Article();
        article.setTitle(TITLE1);
        article.setContent(CONTENT1);
        article.setPublished(true);
        article.setUserId(USERID1);
        article.setCss(css1.getContent());
        articleRepository.save(article);

        article.setTitle(TITLE2);
        article.setContent(CONTENT2);
        article.setPublished(false);
        article.setUserId(USERID1);
        article.setCss(css1.getContent());
        articleRepository.save(article);

        article.setTitle(TITLE3);
        article.setContent(CONTENT3);
        article.setPublished(true);
        article.setUserId(USERID1);
        article.setCss(css1.getContent());
        articleRepository.save(article);
    }
}