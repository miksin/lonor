package jp.co.calace.lonor.models;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeriousReplyEngine extends ReplyEngine {

    private final Pattern equationPattern = Pattern.compile("^(\\d+)\\s*([+\\-*/])\\s*(\\d+)$");

    public SeriousReplyEngine() {
        super(new ArrayList<ConversationSet>());
        this.addConversationSet(new String[]{"hello", "hi", "こんにちは", "おはよう"},
                new String[]{"こんにちは、今日もいい天気ですね", "こんにちは、最近はどうですか？", "こんにちは、お仕事は大変ですか？"});
        this.addConversationSet(new String[]{"bye", "さよなら", "バイバイ", "じゃあね"},
                new String[]{"また明日", "バイバイ", "今日楽しいですね"});
        this.addConversationSet(new String[]{"ありがとう", "thank"},
                new String[]{"こちらこそ", "ありがとうございます"});
        this.addConversationSet(new String[]{"病気", "風邪"},
                new String[]{"大丈夫ですか", "お大事に", "ゆっくり休んでください"});
        this.addConversationSet(new String[]{"dog", "犬", "いぬ", "イヌ"},
                new String[]{"私も犬が好きですね"});
        this.addConversationSet(new String[]{"cat", "猫", "ねこ", "ネコ"},
                new String[]{"私も猫が好きですね", "次は猫カフェに行かない？"});
        this.addConversationSet(new String[]{"テレビ", "番組", "ドラマ", "TV", "drama"},
                new String[]{"テレビ見た？", "私、ドラマが好きだよ"});
        this.addConversationSet(new String[]{"アニメ", "漫画", "anime", "マンガ", "コミック"},
                new String[]{"「君の名は」が好きよ", "今期何見た？", "ジャンプ見る？"});
        this.addConversationSet(new String[]{"音楽", "music"},
                new String[]{"どのジャンルが好き？", "私も音楽が好きよ", "いつもクラッシックを聞いていますよ"});
        this.addConversationSet(new String[]{"movie", "映画"},
                new String[]{"どのジャンルが好き？", "私も映画が好きよ"});
        this.addConversationSet(new String[]{"game", "ゲーム"},
                new String[]{"P&Dをやっていますよ"});
        this.addConversationSet(new String[]{"date", "デート"},
                new String[]{"デート？次行こうね"});
        this.addConversationSet(new String[]{"職業", "仕事"},
                new String[]{"今は大学生ですよ"});
    }

    @Override
    public String defaultReply() {
        String[] defaults = {"どうですか", "すみません、よくわからないです"};
        return defaults[this.random.nextInt(defaults.length)];
    }

    @Override
    public List<String> reply(List<String> refMsgList, Character character) {
        List<String> resultList = super.reply(refMsgList, character);

        for (String refMsg : refMsgList) {
            if (refMsg.toLowerCase().contains(character.getName())) {
                resultList.add(character.getName() + "です、呼びますか？");
            }

            if (refMsg.toLowerCase().equals("クラウド") ||
                    refMsg.toLowerCase().equals("ボッブ") ||
                    refMsg.toLowerCase().equals("ダーレン")) {
                resultList.add(refMsg + "さん？");
            }

            if (refMsg.toLowerCase().contains("何歳")) {
                resultList.add(character.getOld() + "です");
            }

            Matcher eqMatcher = equationPattern.matcher(refMsg.toLowerCase());
            while (eqMatcher.find()) {
                int a = Integer.parseInt(eqMatcher.group(1));
                int b = Integer.parseInt(eqMatcher.group(3));
                String operator = eqMatcher.group(2);
                int result = 0;
                if (operator.equals("+")) {
                    result = a + b;
                } else if (operator.equals("-")) {
                    result = a - b;
                } else if (operator.equals("*")) {
                    result = a * b;
                } else if (operator.equals("/")) {
                    result = a / b;
                }


                resultList.add(eqMatcher.group() + " = " + result);
            }
        }

        if (resultList.size() < 1 && this.random.nextDouble() < 0.5) {
            resultList.add(defaultReply());
        }

        return resultList;
    }
}
