import spock.lang.Specification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import com.example.webnovel.novel.controller.NovelController
import com.example.webnovel.WebNovelApplication


@SpringBootTest(classes = [WebNovelApplication.class])
class LoadContextTest extends Specification {

    @Autowired
    private NovelController novelController

    def "when context is loaded then all expected beans are created"() {
        expect: "the NoveController is created"
        novelController != null
    }
}