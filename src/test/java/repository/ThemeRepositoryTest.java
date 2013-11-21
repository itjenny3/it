package repository;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itjenny.common.CommonTest;
import com.itjenny.common.ResourceFile;
import com.itjenny.domain.Theme;
import com.itjenny.repository.ThemeRepository;

public class ThemeRepositoryTest extends CommonTest {
    @Rule
    public ResourceFile css1 = new ResourceFile("/default.css");

    @Autowired
    ThemeRepository themeRepository;

    private static final String TITLE1 = "default";
    private static final String USERID = "itjenny";

    @Test
    public void setInit() throws IOException {
        // Given
        Theme theme = new Theme();
        theme.setTitle(TITLE1);
        theme.setCss(css1.getContent());
        theme.setUserId(USERID);
        themeRepository.save(theme);
    }
}