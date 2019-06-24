package jp.co.calace.lonor.models;

import java.util.ArrayList;
import java.util.List;

public class YandereReplyEngine extends ReplyEngine {

    public YandereReplyEngine() {
        super(new ArrayList<ConversationSet>());
        this.addConversationSet(new String[]{"hello", "hi", "こんにちは", "おはよう"},
                new String[]{"..."});
        this.addConversationSet(new String[]{"bye", "さよなら", "バイバイ", "じゃあね"},
                new String[]{"...", "...く"});
        this.addConversationSet(new String[]{"病気", "風邪"},
                new String[]{"...すぐ行く"});
    }

    @Override
    public List<String> reply(List<String> refMsgList, Character character) {
        List<String> resultList = super.reply(refMsgList, character);

        for (String refMsg : refMsgList) {
            if (refMsg.toLowerCase().contains(character.getName())) {
                resultList.add("...！ここだよ");
            }

            if (refMsg.toLowerCase().contains("何歳")) {
                resultList.add("..." + character.getOld());
            }

            if (refMsg.toLowerCase().contains("アリス")) {
                resultList.add("...なぜアリスさん呼ぶの？");
                for (int i = 0; i < 10; i++) {
                    resultList.add("なぜ");
                }
                resultList.add("なぜ何も言わない？");
                resultList.add(".........家に行く");
            }
        }

        return resultList;
    }
}
