package be.civadis.backoffice.messaging;

import be.civadis.backoffice.domain.Article;
import com.codahale.metrics.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/test")
public class ArticleProducerResource{

    @Autowired
    private ArticleChannel articleChannel;

    @Autowired
    private BinderAwareChannelResolver resolver;

    /**
     * génère des articles sur le topic associé l'articleChannel (topic défini dans la config)
     * utile si on connait le topic avant l'exécution
     */
    @GetMapping("/articles/produces")
    @Timed
    public void produce() {

        List<Article> articleList = Arrays.asList(
            new Article(1L, "111", "Article 111"),
            new Article(2L, "222", "Article 222"),
            new Article(3L, "333", "Article 333")
        ) ;

        articleList.stream().forEach(art ->
            articleChannel.messageChannel().send(MessageBuilder
                .withPayload(art)
                .setHeader(KafkaHeaders.MESSAGE_KEY, art.getId().toString().getBytes(StandardCharsets.UTF_8))
                .build())
        );

    }

    /**
     * génère des articles sur un topic défini au runtime
     * utile dans un contexte multitenant lorsque l'on ne connait le tenant qu'au runtime
     * @param tenant
     */
    @GetMapping("/articles/produces/{tenant}")
    @Timed
    public void produce(@PathVariable(value = "tenant") String tenant) {

        List<Article> articleList = Arrays.asList(
            new Article(1L, "111", "Article 111"),
            new Article(2L, "222", "Article 222"),
            new Article(3L, "333", "Article 333")
        ) ;

        articleList.stream().forEach(art ->
                resolver.resolveDestination("article_" + tenant.trim()).send(MessageBuilder
                .withPayload(art)
                .setHeader(KafkaHeaders.MESSAGE_KEY, art.getId().toString().getBytes(StandardCharsets.UTF_8))
                .build())
        );

    }

}
