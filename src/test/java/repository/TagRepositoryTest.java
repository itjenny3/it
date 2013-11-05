package repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itjenny.common.CommonTest;
import com.itjenny.domain.Tag;
import com.itjenny.repository.TagRepository;

public class TagRepositoryTest extends CommonTest {
    @Autowired
    TagRepository tagRepository;

    private static final String TAG1 = "tag1";
    private static final String TAG2 = "tag2";
    private static final String ARTICLE1 = "QA";
    private static final String ARTICLE2 = "private";

    @Test
    public void setInit() {
	// Given
	Tag tag = new Tag();
	tag.setTag(TAG1);
	tag.setArticle(ARTICLE1);
	tagRepository.save(tag);
	tag.setTag(TAG2);
	tag.setArticle(ARTICLE1);
	tagRepository.save(tag);
	tag.setTag(TAG2);
	tag.setArticle(ARTICLE2);
	tagRepository.save(tag);
    }
}