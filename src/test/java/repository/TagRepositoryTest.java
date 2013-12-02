package repository;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.itjenny.common.CommonTest;
import com.itjenny.domain.Tag;
import com.itjenny.repository.TagRepository;

public class TagRepositoryTest extends CommonTest {
    @Autowired
    TagRepository tagRepository;

    @Test
    public void setInit() {
        // Given
        Tag tag = new Tag();
        tag.setTag("basic");
        tag.setArticle("markdown");
        tagRepository.save(tag);
        tag.setArticle("mac");
        tagRepository.save(tag);

        tag.setTag("server");
        tag.setArticle("spring");
        tagRepository.save(tag);
        tag.setArticle("yobi");
        tagRepository.save(tag);
        tag.setArticle("readme");
        tagRepository.save(tag);
    }
    
    @Test
    public void getSome() {
        List<String> tags = Lists.newArrayList("server", "basic");
        List<String> findSomeByTag = tagRepository.findSomeByTag(tags);
        System.out.println(findSomeByTag);
    }
}