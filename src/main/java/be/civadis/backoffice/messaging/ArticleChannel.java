package be.civadis.backoffice.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ArticleChannel {

    //nom du channel à utiliser dans la config pour le binding avec topic kafka
    String OUTPUT_CHANNEL = "articleOutputChannel";
    String INPUT_CHANNEL = "articleInputChannel";

    @Output(value = ArticleChannel.OUTPUT_CHANNEL)
    MessageChannel messageChannel();

    @Input(value = ArticleChannel.INPUT_CHANNEL)
    SubscribableChannel subscribableChannel();

}
