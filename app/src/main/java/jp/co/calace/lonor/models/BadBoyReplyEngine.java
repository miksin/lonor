package jp.co.calace.lonor.models;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BadBoyReplyEngine extends ReplyEngine {

    private final Pattern equationPattern = Pattern.compile("^(\\d+)\\s*([+\\-*/])\\s*(\\d+)$");

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
        this.addConversationSet(new String[]{"職業", "仕事", "job", "career"},
                new String[]{"は？俺様の母かよ", "天上天下唯我独尊だ！"});
        this.addConversationSet(new String[]{"fuck"},
                new String[]{"は？！てぇめ！殺すぞ！"});
        this.addConversationSet(new String[]{"はは", "haha", "ふふ", "ハハ"},
                new String[]{"何がおかしい！殺すぞ！", "笑うな！死にたい？"});
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

            Matcher eqMatcher = equationPattern.matcher(refMsg.toLowerCase());
            while (eqMatcher.find()) {
                resultList.add("なめんなよ！これくらいできるさ");
                resultList.add("答えは　" + this.random.nextInt(30) + " だろ？");
            }
        }

        if (resultList.size() < 1 && this.random.nextDouble() < 0.5) {
            resultList.add(defaultReply());
        }

        return resultList;
    }
}
