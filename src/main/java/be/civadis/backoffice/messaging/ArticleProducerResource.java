package be.civadis.backoffice.messaging;

import be.civadis.backoffice.domain.Article;
import com.codahale.metrics.annotation.Timed;
import org.mapstruct.ap.internal.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ArticleProducerResource{

    @Autowired
    private ArticleChannel articleChannel;

    @GetMapping("/articles/produces")
    @Timed
    public void produce() {

        List<Article> articleList = Collections.newArrayList(
            new Article(1L, "111", "Article 111"),
            new Article(2L, "222", "Article 222"),
            new Article(3L, "333", "Article 333")
        );

        articleList.stream().forEach(art -> {
            boolean sended = articleChannel.messageChannel().send(MessageBuilder
                .withPayload(art)
                .setHeader(KafkaHeaders.MESSAGE_KEY, art.getId())
                .build());
            System.out.println(art.getId() + " " + sended);
        });

    }

}
