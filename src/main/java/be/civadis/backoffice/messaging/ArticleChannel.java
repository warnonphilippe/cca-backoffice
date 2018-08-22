package be.civadis.backoffice.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ArticleChannel {

    //nom du channel à utiliser dans la config pour le binding avec topic kafka
    String OUTPUT_CHANNEL = "articleOutputChannel";

    @Output(ArticleChannel.OUTPUT_CHANNEL)
    MessageChannel output();

}
