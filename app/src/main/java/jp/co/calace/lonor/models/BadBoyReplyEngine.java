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
        this.addConversationSet(new String[]{"喧嘩", "貴様"},
                new String[]{"オメーどこ中や？", "てぇめ！", "喧嘩上等だ！"});
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
            }
        }

        return resultList;
    }
}
