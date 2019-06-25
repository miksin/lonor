package jp.co.calace.lonor.models;

import java.util.ArrayList;
import java.util.List;

public class BadBoyReplyEngine extends ReplyEngine {

    public BadBoyReplyEngine() {
        super(new ArrayList<ConversationSet>());
        this.addConversationSet(new String[]{"hello", "hi", "こんにちは", "おはよう"},
                new String[]{"見てんじゃねぇよっ！", "ぶっ殺すぞ！", "オメーどこ中や？(´Д`)ｧ-", "じろじろ見ないでよ！"});
        this.addConversationSet(new String[]{"bye", "さよなら", "バイバイ", "じゃあね"},
                new String[]{"とっとと 行きやがる！", "どいた、どいた！"});
        this.addConversationSet(new String[]{"ありがとう", "thank"},
                new String[]{"う…お…おう"});
        this.addConversationSet(new String[]{"喧嘩", "貴様"},
                new String[]{"オメーどこ中や？", "てぇめ！", "喧嘩上等だ！"});
        this.addConversationSet(new String[]{"dog", "犬", "いぬ", "イヌ"},
                new String[]{"おめぇが犬だろう！"});
        this.addConversationSet(new String[]{"cat", "猫", "ねこ", "ネコ"},
                new String[]{"猫…すきじゃねぇぞ！"});
        this.addConversationSet(new String[]{"テレビ", "番組", "ドラマ", "TV", "drama"},
                new String[]{"あ？そなもの見てねぇよ"});
        this.addConversationSet(new String[]{"アニメ", "漫画", "anime", "マンガ", "コミック"},
                new String[]{"あ？そなもの見てねぇよ"});
        this.addConversationSet(new String[]{"音楽", "music"},
                new String[]{"は？当然メタルぜ！"});
        this.addConversationSet(new String[]{"movie", "映画"},
                new String[]{"あ？そなもの見てねぇよ"});
        this.addConversationSet(new String[]{"game", "ゲーム"},
                new String[]{"ゲーセン行こうぜ！"});
        this.addConversationSet(new String[]{"date", "デート"},
                new String[]{"誰かてみぇと一緒かよ"});
        this.addConversationSet(new String[]{"職業", "仕事"},
                new String[]{"は？俺様の母かよ", "天上天下唯我独尊だ！"});
    }

    @Override
    public String defaultReply() {
        String[] defaults = {"わかねぇよそんなもの", "何ぶつぶつ言ってんの"};
        return defaults[this.random.nextInt(defaults.length)];
    }

    @Override
    public List<String> reply(List<String> refMsgList, Character character) {
        List<String> resultList = super.reply(refMsgList, character);

        for (String refMsg : refMsgList) {
            if (refMsg.toLowerCase().contains(character.getName())) {
                resultList.add("俺様は" + character.getName() + "だ、よく覚えとけ");
            }

            if (refMsg.toLowerCase().contains("何歳")) {
                resultList.add("貴様に関係ねぇよ！");
                resultList.add("ちっ、" + character.getOld() + "だ");
            }
        }

        if (resultList.size() < 1 && this.random.nextDouble() < 0.5) {
            resultList.add(defaultReply());
        }

        return resultList;
    }
}
