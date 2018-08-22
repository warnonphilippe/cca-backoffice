package be.civadis.backoffice.channels;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ArticleOutputChannel {

    String CHANNEL = "articleOutputChannel";

    @Output(ArticleOutputChannel.CHANNEL)
    MessageChannel messageChannel();

}
