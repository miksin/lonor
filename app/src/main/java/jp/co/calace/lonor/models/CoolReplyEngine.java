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
        this.addConversationSet(new String[]{"ありがとう", "thank"},
                new String[]{"ま"});
        this.addConversationSet(new String[]{"dog", "犬", "いぬ", "イヌ"},
                new String[]{"嫌い"});
        this.addConversationSet(new String[]{"cat", "猫", "ねこ", "ネコ"},
                new String[]{"猫…す…嫌い"});
        this.addConversationSet(new String[]{"テレビ", "番組", "ドラマ", "TV", "drama"},
                new String[]{"そういうのあまり好きじゃない"});
        this.addConversationSet(new String[]{"アニメ", "漫画", "anime", "マンガ", "コミック"},
                new String[]{"そういうのあまり好きじゃない"});
        this.addConversationSet(new String[]{"音楽", "music"},
                new String[]{"ま、ロックなら..."});
        this.addConversationSet(new String[]{"movie", "映画"},
                new String[]{"そういうのあまり好きじゃない"});
        this.addConversationSet(new String[]{"game", "ゲーム"},
                new String[]{"そういうのあまり好きじゃない"});
        this.addConversationSet(new String[]{"date", "デート"},
                new String[]{"ま、いいけど"});
        this.addConversationSet(new String[]{"職業", "仕事"},
                new String[]{"自分自身を信じてみるだけでいい", "きっと、生きる道が見えてくる", "人生の目標を教えてくれるのは直感だけ"});
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
