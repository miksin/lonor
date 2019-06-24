package jp.co.calace.lonor.models;

import java.util.ArrayList;
import java.util.List;

public class SeriousReplyEngine extends ReplyEngine {

    public SeriousReplyEngine() {
        super(new ArrayList<ConversationSet>());
        this.addConversationSet(new String[]{"hello", "hi", "こんにちは", "おはよう"},
                new String[]{"こんにちは、今日もいい天気ですね", "こんにちは、最近はどうですか？", "こんにちは、お仕事は大変ですか？"});
        this.addConversationSet(new String[]{"bye", "さよなら", "バイバイ", "じゃあね"},
                new String[]{"また明日", "バイバイ", "今日楽しいですね"});
        this.addConversationSet(new String[]{"病気", "風邪"},
                new String[]{"大丈夫ですか", "お大事に", "ゆっくり休んでください"});
    }

    @Override
    public List<String> reply(List<String> refMsgList, Character character) {
        List<String> resultList = super.reply(refMsgList, character);

        for (String refMsg : refMsgList) {
            if (refMsg.toLowerCase().contains(character.getName())) {
                resultList.add(character.getName() + "です、呼びますか？");
            }

            if (refMsg.toLowerCase().contains("何歳")) {
                resultList.add(character.getOld() + "です");
            }
        }

        return resultList;
    }
}
