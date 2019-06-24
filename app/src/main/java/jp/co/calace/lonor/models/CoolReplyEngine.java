package jp.co.calace.lonor.models;

import java.util.ArrayList;
import java.util.List;

public class CoolReplyEngine extends ReplyEngine {

    public CoolReplyEngine() {
        super(new ArrayList<ConversationSet>());
        this.addConversationSet(new String[]{"hello", "hi", "こんにちは", "おはよう"},
                new String[]{"...ふ", "ま", "話掛けないで"});
        this.addConversationSet(new String[]{"bye", "さよなら", "バイバイ", "じゃあね"},
                new String[]{"じゃあ", "ま"});
    }

    @Override
    public List<String> reply(List<String> refMsgList, Character character) {
        List<String> resultList = super.reply(refMsgList, character);

        for (String refMsg : refMsgList) {
            if (refMsg.toLowerCase().contains(character.getName())) {
                resultList.add("なんだ");
            }

            if (refMsg.toLowerCase().contains("何歳")) {
                resultList.add("教えない");
            }
        }

        return resultList;
    }
}
