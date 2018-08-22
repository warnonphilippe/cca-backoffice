package be.civadis.backoffice.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ArticleChannel {

    //nom du channel à utiliser dans la config pour le binding avec topic kafka
    String OUTPUT_CHANNEL = "articleOutputChannel";
    //String OUTPUT_CHANNEL = "messageChannel";

    @Output(value = ArticleChannel.OUTPUT_CHANNEL)
    MessageChannel messageChannel();

}
