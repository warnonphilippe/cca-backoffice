package be.civadis.backoffice.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ArticleOutputChannel {

    //nom du channel à utiliser dans la config pour le binding avec topic kafka
    String CHANNEL = "articleOutputChannel";

    @Output(ArticleOutputChannel.CHANNEL)
    MessageChannel messageChannel();

}
