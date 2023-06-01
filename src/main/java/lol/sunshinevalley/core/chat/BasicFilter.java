package lol.sunshinevalley.core.chat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// Thanks Mineplex

public class BasicFilter
{
    private static final List<String> INAPPROPRIATE_WORDS = Arrays.asList(
            "nigger", "honkey", "beaner", "chink", "anus", "anal", "ballsack",
            "nutsack", "bitch", "bastard", "cum", "jizz", "orgasm", "faggot",
            "fag", "faqq", "fuck", "fqck", "niqq", "masturbate", "wank",
            "penis", "dick", "cock", "porn", "rape", "queer", "dyke", "dildo",
            "shit", "tits", "vagina", "pussy", "twat", "cunt", "whore",
            "prostitute", "thot", "slut", "succ", "niger", "hypixel"
    );

    private static String getFilterStars(String word)
    {
        StringBuilder stars = new StringBuilder();

        for (int i = 0; i < word.length(); i++)
        {
            stars.append("*");
        }

        return stars.toString();
    }

    public static String filterMessage(String message)
    {

        List<Boolean> starPositions = new ArrayList<>(Collections.nCopies(message.length(), false));

        //Bukkit.broadcastMessage(starPositions.stream().map(v -> v ? "*" : "_").collect(Collectors.joining(",")));

        for (String inappropriateWord : INAPPROPRIATE_WORDS)
        {
            String starred = message.replaceAll("(?i)" + inappropriateWord, getFilterStars(inappropriateWord));

            for (int i = 0; i < starPositions.size(); i++)
            {
                if (starPositions.get(i))
                {
                    continue;
                }

                if (starred.charAt(i) == '*')
                {
                    starPositions.set(i, true);
                }
            }
        }

        StringBuilder builder = new StringBuilder(message);

        for (int i = 0; i < starPositions.size(); i++)
        {
            if (starPositions.get(i))
            {
                builder.setCharAt(i, '*');
            }
        }

        return builder.toString();
    }

    public static List<String> filterMessages(String... messages)
    {
        return Arrays.stream(messages).map(BasicFilter::filterMessage).collect(Collectors.toList());
    }
}