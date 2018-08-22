package be.civadis.backoffice.messaging;

import be.civadis.backoffice.BackofficeApp;
import be.civadis.backoffice.domain.Article;
import com.codahale.metrics.annotation.Timed;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.ap.internal.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackofficeApp.class)
@Transactional
public class ProduceArticleTest {

    @Autowired
    private ArticleChannel articleChannel;

    @Test
    public void produce() {

        List<Article> articleList = Collections.newArrayList(
            new Article(1L, "111", "Article 111"),
            new Article(2L, "222", "Article 222"),
            new Article(3L, "333", "Article 333")
        );

        articleList.stream().forEach(art -> {
            articleChannel.output().send(MessageBuilder
                .withPayload(art)
                .setHeader(KafkaHeaders.MESSAGE_KEY, art.getId())
                .build());
        });

    }

}
