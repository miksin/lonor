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
        this.addConversationSet(new String[]{"ありがとう", "thank"},
                new String[]{"うん"});
        this.addConversationSet(new String[]{"病気", "風邪"},
                new String[]{"...すぐ行く"});
        this.addConversationSet(new String[]{"dog", "犬", "いぬ", "イヌ"},
                new String[]{"…ま、嫌いじゃない"});
        this.addConversationSet(new String[]{"cat", "猫", "ねこ", "ネコ"},
                new String[]{"…ま、嫌いじゃない"});
        this.addConversationSet(new String[]{"テレビ", "番組", "ドラマ", "TV", "drama"},
                new String[]{"それは…言いたくない"});
        this.addConversationSet(new String[]{"アニメ", "漫画", "anime", "マンガ", "コミック"},
                new String[]{"一応、嫌いじゃないけど"});
        this.addConversationSet(new String[]{"音楽", "music"},
                new String[]{"…アイドルとか"});
        this.addConversationSet(new String[]{"movie", "映画"},
                new String[]{"それは…言いたくない"});
        this.addConversationSet(new String[]{"date", "デート"},
                new String[]{"！誘ってる？行く行く！今行く"});
        this.addConversationSet(new String[]{"職業", "仕事"},
                new String[]{"...ニート", "一応、高校生"});
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

            if (refMsg.toLowerCase().contains("game") || refMsg.toLowerCase().contains("ゲーム")) {
                resultList.add("ま、一応やるけど…");
                resultList.add("スマホゲームなら");
                resultList.add("GBF");
                resultList.add("FGO");
                resultList.add("バンドリ");
                resultList.add("プリコネ");
                resultList.add("…など、もちろんpokemon GOもやっている");
                resultList.add("テレビゲームもモンハン");
                resultList.add("アサシンクリード");
                resultList.add("ペルソナなどもやっている");
                resultList.add("パソコンはもち…あ…何でもない、忘れて");
            }
        }

        return resultList;
    }
}
